//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.data.wavedelays;

@SuppressWarnings("unused")
public enum Prefix {
    BOSS(0xCC5555, "B", 0x7A3333),
    BLAZES(0xEFB61F, "BL", 0x8F6D0F),
    SLIME(0x88FF88, "S", 0x51A951),
    HBM(0x2A415F, "HBM", 0x193241),
    WITHER_SKELETON(0x888888, "WS", 0x515151),
    OLD_ONE(0x55AA55, "O1", 0x336633),
    GIANT(0x00FFFF, "G", 0x009999),
    POLICE(0x16537E, "P", 0x0E324D),
    CELL(0xFF8234, "C", 0x99501F),
    WINDOW(0xAAAAAA, "W", 0x666666);

    private final int color;
    private final int fadedColor;
    private final String prefix;

    Prefix(int color, String prefix, int fadedColor) {
        this.color = color;
        this.prefix = prefix;
        this.fadedColor = fadedColor;
    }

    public int getColor() {
        return color;
    }

    public String getPrefix() {
        return prefix;
    }

    public int getFadedColor() {
        return fadedColor;
    }
}