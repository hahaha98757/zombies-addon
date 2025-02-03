//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.features;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RoundTimer {
    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    private static Future<?> future;

    private static long millis;

    private static boolean stopTimer;

    public static void run() {
        LOCK.lock();
        try {
            if (future == null) future = EXECUTOR.scheduleAtFixedRate(() -> {
                if (stopTimer) return;
                LOCK.lock();
                try {
                    millis += 10L;
                } finally {
                    LOCK.unlock();
                }
            }, 0L, 10L, TimeUnit.MILLISECONDS);
            else {
                stopTimer = false;
                millis = 0L;
            }
        } finally {
            LOCK.unlock();
        }
    }

    public static void stop() {
        LOCK.lock();
        try {
            stopTimer = true;
        } finally {
            LOCK.unlock();
        }
    }

    public static long getMillis() {
        return millis;
    }

    public static int getTicks() {
        return (int) (millis / 50);
    }
}