//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.data.wavedelays;

public class Round {
    private final Wave[] waves;

    public Round(Wave[] waves) {
        this.waves = waves;
    }

    public Wave[] getWaves() {
        return waves;
    }

    public short[] getWaveTimes() {
        short[] ret = new short[waves.length];
        for (int i = 0; i < waves.length; i++) {
            ret[i] = waves[i].getTime();
        }
        return ret;
    }
}