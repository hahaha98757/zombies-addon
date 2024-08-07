//Code in NOTLAST by tahmid-23

package kr.hahaha98757.zombiesaddon.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class TitleEvent extends Event {
	private final String title;

	public TitleEvent(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
}