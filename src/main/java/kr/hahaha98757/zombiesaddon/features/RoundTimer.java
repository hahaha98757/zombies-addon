//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.features;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RoundTimer {
    public static final RoundTimer instance = new RoundTimer();

    private final ReentrantLock lock = new ReentrantLock();

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    private Future<?> future = null;

    private static long millis = 0L;

    private static boolean stopTimer;

    public void run() {
        lock.lock();
        try {
            if (future == null) {
                future = executor.scheduleAtFixedRate(() -> {
                    if (stopTimer) {
                        return;
                    }
                    lock.lock();
                    try {
                        millis += 10L;
                    } finally {
                        lock.unlock();
                    }
                }, 0L, 10L, TimeUnit.MILLISECONDS);
            } else {
                stopTimer = false;
                millis = 0L;
            }
        } finally {
            lock.unlock();
        }
    }

    public void stop() {
        lock.lock();
        try {
            stopTimer = true;
        } finally {
            lock.unlock();
        }
    }

    public long getMillis() {
        return millis;
    }

    public int getTicks() {
        return (int) (millis / 50);
    }
}