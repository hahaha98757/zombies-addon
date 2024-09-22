//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.data.wavedelays;

public enum Prefix {
    BOSS(0xCC5555, "B"),
    BLAZES(0xEFB61F, "BL"),
    SLIME(0x88FF88,"S"),
    HBM(0x2A415F, "HBM"),
    WITHER_SKELETON(0x888888, "WS"),
    OLD_ONE(0x55AA55, "O1"),
    GIANT(0x00FFFF,"G"),
    POLICE(0x16537E,"P"),
    CELL(0xFF8234,"C"),
    WINDOW(0xAAAAAA,"W");

    private final int color;
    private final String prefix;

    Prefix(int color, String prefix) {
        this.color = color;
        this.prefix = prefix;
    }

    public int getColor() {
        return color;
    }

    public String getPrefix() {
        return prefix;
    }
}