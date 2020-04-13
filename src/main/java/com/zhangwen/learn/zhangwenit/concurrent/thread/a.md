## Thread

### Daemon守护线程
* 在构建Daemon线程时，不能依靠finally块中的内容来确保执行关闭或清理资源的逻辑。虚拟机退出时，Daemon线程的finally语句块未必会执行。

### notify()/notifyAll()/wait()注意细节：
* 使用wait()、notify()和notifyAll()时需要先对调用对象加锁。
* 调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列。
* notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或notifAll()的线程释放锁之后，等待线程才有机会从wait()返回。
* notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()方法则是将等待队列中所有的线程全部移到同步队列，被移动的线程状态由WAITING变为BLOCKED。
* 从wait()方法返回的前提是获得了调用对象的锁。
* 从上述细节中可以看到，等待/通知机制依托于同步机制，其目的就是确保等待线程从wait()方法返回时能够感知到通知线程对变量做出的修改。

### join()实现原理

```java
//加锁当前线程对象
public final synchronized void join(long millis)
    throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (millis == 0) {
            //条件不满足，继续等待
            while (isAlive()) {
                wait(0);
            }
            //条件符合，方法返回
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }
```

- 等待通知机制

  等待前驱线程结束，接受前驱线程结束通知

当线程终止时，会调用线程自身的notifyAll()方法，会通知所有等待在该线程对象的线程