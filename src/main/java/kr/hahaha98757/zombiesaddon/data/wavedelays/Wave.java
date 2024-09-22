//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.data.wavedelays;

public class Wave {
    private final short ticks;
    private final Prefix[] prefixes;

    public Wave(short ticks, Prefix[] prefixes) {
        this.ticks = ticks;
        this.prefixes = prefixes;
    }
    public Wave(short ticks) {
        this.ticks = ticks;
        this.prefixes = new Prefix[]{Prefix.WINDOW};
    }

    public short getTime() {
        return ticks;
    }

    public Prefix[] getPrefixes() {
        return prefixes;
    }
}