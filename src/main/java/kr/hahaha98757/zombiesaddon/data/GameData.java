//Code in Zombies Utils by Stachelbeere1248

package kr.hahaha98757.zombiesaddon.data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import kr.hahaha98757.zombiesaddon.enums.GameMode;
import kr.hahaha98757.zombiesaddon.utils.ResourceLoader;
import kr.hahaha98757.zombiesaddon.data.wavedelays.Round;

public class GameData {
    private static final Round[][] roundData;

    static {
        roundData = new Round[10][];
        roundData[0] = readFromFile("data/rounds/DEN_WAVES.json");
        roundData[1] = readFromFile("data/rounds/DEH_WAVES.json");
        roundData[2] = readFromFile("data/rounds/DER_WAVES.json");
        roundData[3] = readFromFile("data/rounds/BBN_WAVES.json");
        roundData[4] = readFromFile("data/rounds/BBH_WAVES.json");
        roundData[5] = readFromFile("data/rounds/BBR_WAVES.json");
        roundData[6] = readFromFile("data/rounds/AA_WAVES.json");
        roundData[7] = readFromFile("data/rounds/PRN_WAVES.json");
        roundData[8] = readFromFile("data/rounds/PRH_WAVES.json");
        roundData[9] = readFromFile("data/rounds/PRR_WAVES.json");
    }

    public static Round getRound(GameMode gameMode, int round) {
        if (gameMode == null) return null;
        if (round == 0) return null;

        try {
            switch (gameMode) {
                case DEAD_END_NORMAL:
                    return roundData[0][round-1];
                case DEAD_END_HARD:
                    return roundData[1][round-1];
                case DEAD_END_RIP:
                    return roundData[2][round-1];
                case BAD_BLOOD_NORMAL:
                    return roundData[3][round-1];
                case BAD_BLOOD_HARD:
                    return roundData[4][round-1];
                case BAD_BLOOD_RIP:
                    return roundData[5][round-1];
                case ALIEN_ARCADIUM:
                    return roundData[6][round-1];
                case PRISON_NORMAL:
                    return roundData[7][round-1];
                case PRISON_HARD:
                    return roundData[8][round-1];
                case PRISON_RIP:
                    return roundData[9][round-1];
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private static Round[] readFromFile(String resourcePath) {
        final JsonElement roundsJsonElement = ResourceLoader.readJsonResource(resourcePath).orElseThrow(RuntimeException::new);
        return new Gson().fromJson(roundsJsonElement, Round[].class);
    }
}