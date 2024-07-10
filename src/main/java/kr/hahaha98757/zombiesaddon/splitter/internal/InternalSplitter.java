//Code in Zombies AutoSplits by tahmid-23

package kr.hahaha98757.zombiesaddon.splitter.internal;

import kr.hahaha98757.zombiesaddon.splitter.LiveSplitSplitter;

import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class InternalSplitter implements LiveSplitSplitter {

	public static final InternalSplitter instance = new InternalSplitter(Executors.newSingleThreadScheduledExecutor());

	private final ReentrantLock lock = new ReentrantLock();

	private final ScheduledExecutorService executor;

	private Future<?> future = null;

	private static long millis = 0L;

	private static boolean stopTimer;

	public InternalSplitter(ScheduledExecutorService executor) {
		this.executor = Objects.requireNonNull(executor, "executor");
	}

	@Override
	public CompletableFuture<Void> startOrSplit() {
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

			return CompletableFuture.completedFuture(null);
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
}