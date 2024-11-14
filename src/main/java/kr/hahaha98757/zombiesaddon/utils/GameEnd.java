package kr.hahaha98757.zombiesaddon.utils;

public class GameEnd extends RuntimeException {
	public GameEnd() {
		super("Unknown Error.");
	}

	public GameEnd(String message) {
		super(message);
	}
}