/*
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/*
 *
 *
 *
 *
 *
 * Written by Doug Lea with assistance from members of JCP JSR-166
 * Expert Group and released to the public domain, as explained at
 * http://creativecommons.org/publicdomain/zero/1.0/
 */

package com.zhangwen.learn.zhangwenit.concurrent.executor.ScheduledThreadPoolExecutor;

import com.zhangwen.learn.zhangwenit.concurrent.executor.MyRejectedExecutionHandler;
import com.zhangwen.learn.zhangwenit.concurrent.executor.ThreadPoolExecutor.MyThreadPoolExecutor;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * ScheduledThreadPoolExecutor 过程注释
 */
public class MyScheduledThreadPoolExecutor
        extends MyThreadPoolExecutor
        implements ScheduledExecutorService {

    /**
     * False if should cancel/suppress periodic tasks on shutdown.
     */
    private volatile boolean continueExistingPeriodicTasksAfterShutdown;

    /**
     * False if should cancel non-periodic not-yet-expired tasks on shutdown.
     */
    private volatile boolean executeExistingDelayedTasksAfterShutdown = true;

    /**
     * True if ScheduledFutureTask.cancel should remove from queue.
     */
    volatile boolean removeOnCancel;

    /**
     * Sequence number to break scheduling ties, and in turn to
     * guarantee FIFO order among tied entries.
     */
    private static final AtomicLong sequencer = new AtomicLong();

    /**
     * 任务包装类，实现了Delayed、Runnable、Future接口
     * {@link Delayed#getDelay(TimeUnit)} 返回任务当前延时时间，如果<=0，那么将该任务从DelayedWorkQueue取出执行
     *
     * @param <V>
     */
    private class ScheduledFutureTask<V>
            extends FutureTask<V> implements RunnableScheduledFuture<V> {

        /**
         * Sequence number to break ties FIFO
         * 序列号
         */
        private final long sequenceNumber;

        /**
         * The nanoTime-based time when the task is enabled to execute
         * 本次任务执行开始时间
         */
        private volatile long time;

        /**
         * Period for repeating tasks, in nanoseconds.
         * A positive value indicates fixed-rate execution.
         * A negative value indicates fixed-delay execution.
         * A value of 0 indicates a non-repeating (one-shot) task.
         * 用于重复任务的周期，以纳秒为单位。
         * 正值表示固定速度执行。即当前任务开始和下一个任务开始的时间间隔为period
         * 负值表示固定延迟执行。即当前任务结束和下一个任务开始的时间间隔为period
         * 值0表示不重复(一次)任务。
         */
        private final long period;

        /**
         * The actual task to be re-enqueued by reExecutePeriodic
         */
        RunnableScheduledFuture<V> outerTask = this;

        /**
         * Index into delay queue, to support faster cancellation.
         * 任务在任务数组{@link DelayedWorkQueue#queue}中的索引，为了支持更快的任务取消
         */
        int heapIndex;

        /**
         * Creates a one-shot action with given nanoTime-based trigger time.
         */
        ScheduledFutureTask(Runnable r, V result, long triggerTime,
                            long sequenceNumber) {
            super(r, result);
            this.time = triggerTime;
            this.period = 0;
            this.sequenceNumber = sequenceNumber;
        }

        /**
         * Creates a periodic action with given nanoTime-based initial
         * trigger time and period.
         */
        ScheduledFutureTask(Runnable r, V result, long triggerTime,
                            long period, long sequenceNumber) {
            super(r, result);
            this.time = triggerTime;
            this.period = period;
            this.sequenceNumber = sequenceNumber;
        }

        /**
         * Creates a one-shot action with given nanoTime-based trigger time.
         */
        ScheduledFutureTask(Callable<V> callable, long triggerTime,
                            long sequenceNumber) {
            super(callable);
            this.time = triggerTime;
            this.period = 0;
            this.sequenceNumber = sequenceNumber;
        }

        public long getDelay(TimeUnit unit) {
            return unit.convert(time - System.nanoTime(), NANOSECONDS);
        }

        public int compareTo(Delayed other) {
            if (other == this) // compare zero if same object
                return 0;
            if (other instanceof ScheduledFutureTask) {
                ScheduledFutureTask<?> x = (ScheduledFutureTask<?>) other;
                long diff = time - x.time;
                if (diff < 0)
                    return -1;
                else if (diff > 0)
                    return 1;
                else if (sequenceNumber < x.sequenceNumber)
                    return -1;
                else
                    return 1;
            }
            long diff = getDelay(NANOSECONDS) - other.getDelay(NANOSECONDS);
            return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
        }

        /**
         * Returns {@code true} if this is a periodic (not a one-shot) action.
         *
         * @return {@code true} if periodic
         */
        public boolean isPeriodic() {
            return period != 0;
        }

        /**
         * Sets the next time to run for a periodic task.
         */
        private void setNextRunTime() {
            long p = period;
            if (p > 0)
                time += p;
            else
                time = triggerTime(-p);
        }

        public boolean cancel(boolean mayInterruptIfRunning) {
            // The racy read of heapIndex below is benign:
            // if heapIndex < 0, then OOTA guarantees that we have surely
            // been removed; else we recheck under lock in remove()
            boolean cancelled = super.cancel(mayInterruptIfRunning);
            if (cancelled && removeOnCancel && heapIndex >= 0)
                remove(this);
            return cancelled;
        }

        /**
         * Overrides FutureTask version so as to reset/requeue if periodic.
         */
        public void run() {
            if (!canRunInCurrentRunState(this))
                cancel(false);
            else if (!isPeriodic())
                super.run();
            else if (super.runAndReset()) {
                setNextRunTime();
                reExecutePeriodic(outerTask);
            }
        }
    }

    /**
     * Returns true if can run a task given current run state and
     * run-after-shutdown parameters.
     */
    boolean canRunInCurrentRunState(RunnableScheduledFuture<?> task) {
        if (!isShutdown())
            return true;
        if (isStopped())
            return false;
        return task.isPeriodic()
                ? continueExistingPeriodicTasksAfterShutdown
                : (executeExistingDelayedTasksAfterShutdown
                || task.getDelay(NANOSECONDS) <= 0);
    }

    /**
     * 将任务添加到工作队列 DelayedWorkQueue 中
     */
    private void delayedExecute(RunnableScheduledFuture<?> task) {
        //父类线程池方法，判断当前线程池是否被终止
        if (isShutdown())
            //父类方法，如果线程池被终止，调用配置的处理策略 RejectedExecutionHandler，处理当前任务
            reject(task);
        else {
            //将任务添加到工作队列 DelayedWorkQueue 中,添加的时候，会根据 task 的比较策略，
            //使用堆排序算法对队列进行排序
            super.getQueue().add(task);
            if (!canRunInCurrentRunState(task) && remove(task))
                //如果该任务不可运行(线程池被关闭、停止等)，且从任务队列中移除成功，那么取消当前任务
                task.cancel(false);
            else
                //确保添加任务后，有工作线程已启动
                ensurePrestart();
        }
    }

    /**
     */
    void reExecutePeriodic(RunnableScheduledFuture<?> task) {
        if (canRunInCurrentRunState(task)) {
            super.getQueue().add(task);
            if (canRunInCurrentRunState(task) || !remove(task)) {
                ensurePrestart();
                return;
            }
        }
        task.cancel(false);
    }

    /**
     */
    @Override
    public void onShutdown() {
        BlockingQueue<Runnable> q = super.getQueue();
        boolean keepDelayed =
                getExecuteExistingDelayedTasksAfterShutdownPolicy();
        boolean keepPeriodic =
                getContinueExistingPeriodicTasksAfterShutdownPolicy();
        // Traverse snapshot to avoid iterator exceptions
        // TODO: implement and use efficient removeIf
        // super.getQueue().removeIf(...);
        for (Object e : q.toArray()) {
            if (e instanceof RunnableScheduledFuture) {
                RunnableScheduledFuture<?> t = (RunnableScheduledFuture<?>) e;
                if ((t.isPeriodic()
                        ? !keepPeriodic
                        : (!keepDelayed && t.getDelay(NANOSECONDS) > 0))
                        || t.isCancelled()) { // also remove if already cancelled
                    if (q.remove(t))
                        t.cancel(false);
                }
            }
        }
        tryTerminate();
    }

    /**
     *
     */
    protected <V> RunnableScheduledFuture<V> decorateTask(
            Runnable runnable, RunnableScheduledFuture<V> task) {
        return task;
    }

    /**
     *
     */
    protected <V> RunnableScheduledFuture<V> decorateTask(
            Callable<V> callable, RunnableScheduledFuture<V> task) {
        return task;
    }

    /**
     *
     */
    private static final long DEFAULT_KEEPALIVE_MILLIS = 10L;

    /**
     *
     */
    public MyScheduledThreadPoolExecutor(int corePoolSize) {
        super(corePoolSize, Integer.MAX_VALUE,
                DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
                new DelayedWorkQueue());
    }

    /**
     *
     */
    public MyScheduledThreadPoolExecutor(int corePoolSize,
                                         ThreadFactory threadFactory) {
        super(corePoolSize, Integer.MAX_VALUE,
                DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
                new DelayedWorkQueue(), threadFactory);
    }

    /**
     *
     */
    public MyScheduledThreadPoolExecutor(int corePoolSize,
                                         MyRejectedExecutionHandler handler) {
        super(corePoolSize, Integer.MAX_VALUE,
                DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
                new DelayedWorkQueue(), handler);
    }

    /**
     *
     */
    public MyScheduledThreadPoolExecutor(int corePoolSize,
                                         ThreadFactory threadFactory,
                                         MyRejectedExecutionHandler handler) {
        super(corePoolSize, Integer.MAX_VALUE,
                DEFAULT_KEEPALIVE_MILLIS, MILLISECONDS,
                new DelayedWorkQueue(), threadFactory, handler);
    }

    /**
     * Returns the nanoTime-based trigger time of a delayed action.
     */
    private long triggerTime(long delay, TimeUnit unit) {
        return triggerTime(unit.toNanos((delay < 0) ? 0 : delay));
    }

    /**
     * Returns the nanoTime-based trigger time of a delayed action.
     */
    long triggerTime(long delay) {
        return System.nanoTime() +
                ((delay < (Long.MAX_VALUE >> 1)) ? delay : overflowFree(delay));
    }

    /**
     *
     */
    private long overflowFree(long delay) {
        Delayed head = (Delayed) super.getQueue().peek();
        if (head != null) {
            long headDelay = head.getDelay(NANOSECONDS);
            if (headDelay < 0 && (delay - headDelay < 0))
                delay = Long.MAX_VALUE + headDelay;
        }
        return delay;
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public ScheduledFuture<?> schedule(Runnable command,
                                       long delay,
                                       TimeUnit unit) {
        if (command == null || unit == null)
            throw new NullPointerException();
        RunnableScheduledFuture<Void> t = decorateTask(command,
                new ScheduledFutureTask<Void>(command, null,
                        triggerTime(delay, unit),
                        sequencer.getAndIncrement()));
        delayedExecute(t);
        return t;
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public <V> ScheduledFuture<V> schedule(Callable<V> callable,
                                           long delay,
                                           TimeUnit unit) {
        if (callable == null || unit == null)
            throw new NullPointerException();
        RunnableScheduledFuture<V> t = decorateTask(callable,
                new ScheduledFutureTask<V>(callable,
                        triggerTime(delay, unit),
                        sequencer.getAndIncrement()));
        delayedExecute(t);
        return t;
    }

    /**
     *
     */
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit) {
        if (command == null || unit == null)
            throw new NullPointerException();
        if (period <= 0L)
            throw new IllegalArgumentException();
        ScheduledFutureTask<Void> sft =
                new ScheduledFutureTask<Void>(command,
                        null,
                        triggerTime(initialDelay, unit),
                        unit.toNanos(period),
                        sequencer.getAndIncrement());
        RunnableScheduledFuture<Void> t = decorateTask(command, sft);
        sft.outerTask = t;
        delayedExecute(t);
        return t;
    }

    /**
     * 延迟initialDelay单位时间后，执行第一次任务，之后每隔delay单位时间后，循环执行一次
     * 循环：执行成功本次任务后，将任务恢复状态重新添加到 DelayedWorkQueue 中
     */
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                     long initialDelay,
                                                     long delay,
                                                     TimeUnit unit) {
        if (command == null || unit == null)
            throw new NullPointerException();
        if (delay <= 0L)
            throw new IllegalArgumentException();
        //构建任务类，设置好本次执行时间，执行策略(是否重复执行以及重复执行间隔)
        ScheduledFutureTask<Void> sft =
                new ScheduledFutureTask<Void>(command,
                        null,
                        triggerTime(initialDelay, unit),
                        -unit.toNanos(delay),
                        sequencer.getAndIncrement());
        //只是将子类实例使用父类引用，个人感觉是方便以后的扩展
        RunnableScheduledFuture<Void> t = decorateTask(command, sft);
        //实际任务,也就是自身，todo:不太明白？
        sft.outerTask = t;
        //将任务添加到工作队列 DelayedWorkQueue 中
        delayedExecute(t);
        //返回任务引用，当调用t.get()时，会阻塞获取任务执行结果
        return t;
    }

    /**
     *
     */
    public void execute(Runnable command) {
        schedule(command, 0, NANOSECONDS);
    }

    // Override AbstractExecutorService methods

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public Future<?> submit(Runnable task) {
        return schedule(task, 0, NANOSECONDS);
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public <T> Future<T> submit(Runnable task, T result) {
        return schedule(Executors.callable(task, result), 0, NANOSECONDS);
    }

    /**
     * @throws RejectedExecutionException {@inheritDoc}
     * @throws NullPointerException       {@inheritDoc}
     */
    public <T> Future<T> submit(Callable<T> task) {
        return schedule(task, 0, NANOSECONDS);
    }

    /**
     *
     */
    public void setContinueExistingPeriodicTasksAfterShutdownPolicy(boolean value) {
        continueExistingPeriodicTasksAfterShutdown = value;
        if (!value && isShutdown())
            onShutdown();
    }

    /**
     *
     */
    public boolean getContinueExistingPeriodicTasksAfterShutdownPolicy() {
        return continueExistingPeriodicTasksAfterShutdown;
    }

    /**
     *
     */
    public void setExecuteExistingDelayedTasksAfterShutdownPolicy(boolean value) {
        executeExistingDelayedTasksAfterShutdown = value;
        if (!value && isShutdown())
            onShutdown();
    }

    /**
     *
     */
    public boolean getExecuteExistingDelayedTasksAfterShutdownPolicy() {
        return executeExistingDelayedTasksAfterShutdown;
    }

    /**
     *
     */
    public void setRemoveOnCancelPolicy(boolean value) {
        removeOnCancel = value;
    }

    /**
     *
     */
    public boolean getRemoveOnCancelPolicy() {
        return removeOnCancel;
    }

    /**
     *
     */
    public void shutdown() {
        super.shutdown();
    }

    /**
     *
     */
    public List<Runnable> shutdownNow() {
        return super.shutdownNow();
    }

    /**
     *
     */
    public BlockingQueue<Runnable> getQueue() {
        return super.getQueue();
    }

    /**
     * 内部实现的阻塞延迟排序队列作为任务队列
     * 1.使用 ScheduledFutureTask 来承载任务，实现了Delay接口
     * 2.内部操作使用 ReentrantLock 来保证操作安全性
     * 3.每次添加和取出，会使用堆排序，根据 ScheduledFutureTask.compare() 结果排序后，存取任务
     */
    static class DelayedWorkQueue extends AbstractQueue<Runnable>
            implements BlockingQueue<Runnable> {

        private static final int INITIAL_CAPACITY = 16;
        private RunnableScheduledFuture<?>[] queue =
                new RunnableScheduledFuture<?>[INITIAL_CAPACITY];
        private final ReentrantLock lock = new ReentrantLock();
        private int size;

        /**
         *
         */
        private Thread leader;

        /**
         * Condition signalled when a newer task becomes available at the
         * head of the queue or a new thread may need to become leader.
         */
        private final Condition available = lock.newCondition();

        /**
         * Sets f's heapIndex if it is a ScheduledFutureTask.
         */
        private static void setIndex(RunnableScheduledFuture<?> f, int idx) {
            if (f instanceof ScheduledFutureTask)
                ((ScheduledFutureTask) f).heapIndex = idx;
        }

        /**
         * Sifts element added at bottom up to its heap-ordered spot.
         * Call only when holding lock.
         */
        private void siftUp(int k, RunnableScheduledFuture<?> key) {
            while (k > 0) {
                int parent = (k - 1) >>> 1;
                RunnableScheduledFuture<?> e = queue[parent];
                if (key.compareTo(e) >= 0)
                    break;
                queue[k] = e;
                setIndex(e, k);
                k = parent;
            }
            queue[k] = key;
            setIndex(key, k);
        }

        /**
         * Sifts element added at top down to its heap-ordered spot.
         * Call only when holding lock.
         */
        private void siftDown(int k, RunnableScheduledFuture<?> key) {
            int half = size >>> 1;
            while (k < half) {
                int child = (k << 1) + 1;
                RunnableScheduledFuture<?> c = queue[child];
                int right = child + 1;
                if (right < size && c.compareTo(queue[right]) > 0)
                    c = queue[child = right];
                if (key.compareTo(c) <= 0)
                    break;
                queue[k] = c;
                setIndex(c, k);
                k = child;
            }
            queue[k] = key;
            setIndex(key, k);
        }

        /**
         * Resizes the heap array.  Call only when holding lock.
         */
        private void grow() {
            int oldCapacity = queue.length;
            //任务数组初始大小为16，每次扩容后的容量=1.5倍的扩容前容量
            int newCapacity = oldCapacity + (oldCapacity >> 1); // grow 50%
            if (newCapacity < 0) // overflow
                newCapacity = Integer.MAX_VALUE;
            //拷贝，对于应用类型，这种方式属于浅拷贝
            queue = Arrays.copyOf(queue, newCapacity);
        }

        /**
         * Finds index of given object, or -1 if absent.
         */
        private int indexOf(Object x) {
            if (x != null) {
                if (x instanceof ScheduledFutureTask) {
                    int i = ((ScheduledFutureTask) x).heapIndex;
                    // Sanity check; x could conceivably be a
                    // ScheduledFutureTask from some other pool.
                    if (i >= 0 && i < size && queue[i] == x)
                        return i;
                } else {
                    for (int i = 0; i < size; i++)
                        if (x.equals(queue[i]))
                            return i;
                }
            }
            return -1;
        }

        public boolean contains(Object x) {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                return indexOf(x) != -1;
            } finally {
                lock.unlock();
            }
        }

        public boolean remove(Object x) {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                int i = indexOf(x);
                if (i < 0)
                    return false;

                setIndex(queue[i], -1);
                int s = --size;
                RunnableScheduledFuture<?> replacement = queue[s];
                queue[s] = null;
                if (s != i) {
                    siftDown(i, replacement);
                    if (queue[i] == replacement)
                        siftUp(i, replacement);
                }
                return true;
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                return size;
            } finally {
                lock.unlock();
            }
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        public int remainingCapacity() {
            return Integer.MAX_VALUE;
        }

        public RunnableScheduledFuture<?> peek() {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                return queue[0];
            } finally {
                lock.unlock();
            }
        }

        /**
         * 添加任务 堆排序
         *
         * @param x
         * @return
         */
        public boolean offer(Runnable x) {
            if (x == null)
                throw new NullPointerException();
            RunnableScheduledFuture<?> e = (RunnableScheduledFuture<?>) x;
            //添加操作加锁，默认是非公平锁
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                int i = size;
                if (i >= queue.length)
                    //如果排队的任务数量>=任务数组的长度，那么对数组进行扩容
                    grow();
                //将当前任务数量+1
                size = i + 1;
                if (i == 0) {
                    //如果本次添加的任务是任务数组中的第一个任务，那么直接设置为queue[0]
                    queue[0] = e;
                    //设置 scheduledFutureTask.heapIndex 为了更快的取消任务
                    setIndex(e, 0);
                } else {
                    //如果数组不为空，排序
                    siftUp(i, e);
                }
                if (queue[0] == e) {
                    //如果本次任务为数组中第一个任务，todo:为什么设置leader
                    leader = null;
                    //有可能会有线程等待在该 Condition 上，唤醒它
                    available.signal();
                }
            } finally {
                lock.unlock();
            }
            //任务数组最大会扩容到 Integer.MAX_VALUE,所以可认为是无界队列，永远返回true
            return true;
        }

        public void put(Runnable e) {
            offer(e);
        }

        public boolean add(Runnable e) {
            return offer(e);
        }

        public boolean offer(Runnable e, long timeout, TimeUnit unit) {
            return offer(e);
        }

        /**
         */
        private RunnableScheduledFuture<?> finishPoll(RunnableScheduledFuture<?> f) {
            int s = --size;
            RunnableScheduledFuture<?> x = queue[s];
            queue[s] = null;
            if (s != 0)
                siftDown(0, x);
            setIndex(f, -1);
            return f;
        }

        public RunnableScheduledFuture<?> poll() {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                RunnableScheduledFuture<?> first = queue[0];
                return (first == null || first.getDelay(NANOSECONDS) > 0)
                        ? null
                        : finishPoll(first);
            } finally {
                lock.unlock();
            }
        }

        public RunnableScheduledFuture<?> take() throws InterruptedException {
            final ReentrantLock lock = this.lock;
            lock.lockInterruptibly();
            try {
                for (; ; ) {
                    RunnableScheduledFuture<?> first = queue[0];
                    if (first == null)
                        available.await();
                    else {
                        long delay = first.getDelay(NANOSECONDS);
                        if (delay <= 0L)
                            return finishPoll(first);
                        first = null; // don't retain ref while waiting
                        if (leader != null)
                            available.await();
                        else {
                            Thread thisThread = Thread.currentThread();
                            leader = thisThread;
                            try {
                                available.awaitNanos(delay);
                            } finally {
                                if (leader == thisThread)
                                    leader = null;
                            }
                        }
                    }
                }
            } finally {
                if (leader == null && queue[0] != null)
                    available.signal();
                lock.unlock();
            }
        }

        public RunnableScheduledFuture<?> poll(long timeout, TimeUnit unit)
                throws InterruptedException {
            long nanos = unit.toNanos(timeout);
            final ReentrantLock lock = this.lock;
            lock.lockInterruptibly();
            try {
                for (; ; ) {
                    RunnableScheduledFuture<?> first = queue[0];
                    if (first == null) {
                        if (nanos <= 0L)
                            return null;
                        else
                            nanos = available.awaitNanos(nanos);
                    } else {
                        long delay = first.getDelay(NANOSECONDS);
                        if (delay <= 0L)
                            return finishPoll(first);
                        if (nanos <= 0L)
                            return null;
                        first = null; // don't retain ref while waiting
                        if (nanos < delay || leader != null)
                            nanos = available.awaitNanos(nanos);
                        else {
                            Thread thisThread = Thread.currentThread();
                            leader = thisThread;
                            try {
                                long timeLeft = available.awaitNanos(delay);
                                nanos -= delay - timeLeft;
                            } finally {
                                if (leader == thisThread)
                                    leader = null;
                            }
                        }
                    }
                }
            } finally {
                if (leader == null && queue[0] != null)
                    available.signal();
                lock.unlock();
            }
        }

        public void clear() {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                for (int i = 0; i < size; i++) {
                    RunnableScheduledFuture<?> t = queue[i];
                    if (t != null) {
                        queue[i] = null;
                        setIndex(t, -1);
                    }
                }
                size = 0;
            } finally {
                lock.unlock();
            }
        }

        public int drainTo(Collection<? super Runnable> c) {
            return drainTo(c, Integer.MAX_VALUE);
        }

        public int drainTo(Collection<? super Runnable> c, int maxElements) {
            Objects.requireNonNull(c);
            if (c == this)
                throw new IllegalArgumentException();
            if (maxElements <= 0)
                return 0;
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                int n = 0;
                for (RunnableScheduledFuture<?> first;
                     n < maxElements
                             && (first = queue[0]) != null
                             && first.getDelay(NANOSECONDS) <= 0; ) {
                    c.add(first);   // In this order, in case add() throws.
                    finishPoll(first);
                    ++n;
                }
                return n;
            } finally {
                lock.unlock();
            }
        }

        public Object[] toArray() {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                return Arrays.copyOf(queue, size, Object[].class);
            } finally {
                lock.unlock();
            }
        }

        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a) {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                if (a.length < size)
                    return (T[]) Arrays.copyOf(queue, size, a.getClass());
                System.arraycopy(queue, 0, a, 0, size);
                if (a.length > size)
                    a[size] = null;
                return a;
            } finally {
                lock.unlock();
            }
        }

        public Iterator<Runnable> iterator() {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                return new Itr(Arrays.copyOf(queue, size));
            } finally {
                lock.unlock();
            }
        }

        /**
         * Snapshot iterator that works off copy of underlying q array.
         */
        private class Itr implements Iterator<Runnable> {
            final RunnableScheduledFuture<?>[] array;
            int cursor;        // index of next element to return; initially 0
            int lastRet = -1;  // index of last element returned; -1 if no such

            Itr(RunnableScheduledFuture<?>[] array) {
                this.array = array;
            }

            public boolean hasNext() {
                return cursor < array.length;
            }

            public Runnable next() {
                if (cursor >= array.length)
                    throw new NoSuchElementException();
                return array[lastRet = cursor++];
            }

            public void remove() {
                if (lastRet < 0)
                    throw new IllegalStateException();
                DelayedWorkQueue.this.remove(array[lastRet]);
                lastRet = -1;
            }
        }
    }
}
