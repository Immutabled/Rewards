package git.inmutable.awrewards.account;

import java.util.UUID;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 01, 22:11
 */
public record AccountInfo(
        UUID id,
        String name,
        Long firstLogin,
        Long playtime,
        Long lastLogin,
        Long lastQuit
) {
}
