package kr.hahaha98757.zombiesaddon.mixins;

import com.github.stachelbeere1248.zombiesutils.utils.LanguageSupport;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Unique;

import java.util.Arrays;
import java.util.regex.Pattern;

@SuppressWarnings("SpellCheckingInspection")
@Mixin(value = LanguageSupport.class, remap = false)
public class MixinLanguageSupport {
    @Unique
    private static final String[] NEW_LANGUAGES = { "EN", "FR", "DE", "KO" };

    /**
     * @author hahaha98757
     * @reason 한국어 추가.
     */
    @Overwrite
    public static boolean isLoss(@NotNull String input) {
        final String[] words = {
                "§cGame Over!",
                "§cPartie terminée!",
                "§cDas Spiel ist vorbei!",
                "§c게임 끝!"
        };
        return Arrays.asList(words).contains(input);
    }

    /**
     * @author hahaha98757
     * @reason 한국어 추가.
     */
    @Overwrite
    public static boolean isWin(@NotNull String input) {
        final String[] words = {
                "§aYou Win!",
                "§aVous avez gagné!",
                "§aDu gewinnst!",
                "§a승리했습니다!"
        };
        return Arrays.asList(words).contains(input);
    }

    /**
     * @author hahaha98757
     * @reason 한국어 추가.
     */
    @Overwrite
    public static boolean containsHard(@NotNull String input) {
        final String[] words = {
                "Hard Difficulty",
                "Difficulté Hard",
                "Hard Schwierigkeitsgrad",
                "Hard 난이도",
                "困难",
                "困難"
        };
        return Arrays.stream(words).anyMatch(input::contains);
    }

    /**
     * @author hahaha98757
     * @reason 한국어 추가.
     */
    @Overwrite
    public static boolean containsRIP(@NotNull String input) {
        final String[] words = {
                "RIP Difficulty",
                "Difficulté RIP",
                "RIP Schwierigkeitsgrad",
                "RIP 난이도",
                "安息"
        };
        return Arrays.stream(words).anyMatch(input::contains);
    }

    /**
     * @author hahaha98757
     * @reason 한국어 추가.
     */
    @Overwrite
    public static @NotNull Pattern roundPattern(@NotNull String language) {
        switch (language) {
            case "EN":
                return Pattern.compile("Round ([0-9]{1,3})");
            case "FR":
                return Pattern.compile("Manche ([0-9]{1,3})");
            case "DE":
                return Pattern.compile("Runde ([0-9]{1,3})");
            case "KO":
                return Pattern.compile("라운드 ([0-9]{1,3})");
            default:
                throw new IllegalStateException("Unexpected value: " + language);
        }
    }

    /**
     * @author hahaha98757
     * @reason 한국어 추가.
     */
    @Overwrite
    public static String[] getLanguages() {
        return NEW_LANGUAGES;
    }
}