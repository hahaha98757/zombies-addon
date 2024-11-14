package kr.hahaha98757.zombiesaddon.events;

import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.common.eventhandler.Event;

public class TitleEvent extends Event {
	private final String title;

	public TitleEvent(String title) {
		this.title = title;
	}

	public String getTitle() {
		return EnumChatFormatting.getTextWithoutFormattingCodes(this.title);
	}
}