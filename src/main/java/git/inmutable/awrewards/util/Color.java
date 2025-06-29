package git.inmutable.awrewards.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.regex.Pattern;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 07, 13:31
 */
public class Color {

    private static final Pattern LEGACY_PATTERN = Pattern.compile("&[0-9a-fk-or]", Pattern.CASE_INSENSITIVE);

    public static Component parse(String input) {

        if (LEGACY_PATTERN.matcher(input).find()) {
            return LegacyComponentSerializer.legacyAmpersand().deserialize(input);
        }

        return MiniMessage.miniMessage().deserialize(input);
    }
}
