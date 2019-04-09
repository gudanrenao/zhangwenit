## Thread

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