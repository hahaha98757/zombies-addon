package kr.hahaha98757.zombiesaddon.data.wavedelays;

public class CustomPlaySound {
    private final String name;
    private final float pitch;
    private final int timing;
    private final byte playWave;

    public CustomPlaySound(String name, float pitch, int timing, byte playWave) {
        this.name = name;
        this.pitch = pitch;
        this.timing = timing;
        this.playWave = playWave;
    }

    public String getName() {
        return name;
    }

    public float getPitch() {
        return pitch;
    }

    public int getTiming() {
        return timing;
    }

    public byte getPlayWave() {
        return playWave;
    }
}