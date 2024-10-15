package kr.hahaha98757.zombiesaddon.events;

import kr.hahaha98757.zombiesaddon.utils.Utils;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TitleEvent extends Event {
	private final String title;

	public TitleEvent(String title) {
		this.title = title;
	}

	public String getTitle() {
		return Utils.getTextWithoutColors(this.title);
	}
}