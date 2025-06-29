package git.inmutable.awrewards.registry;

import git.inmutable.awrewards.Main;
import git.inmutable.awrewards.account.AccountInfo;
import git.inmutable.awrewards.account.AccountRepository;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 01, 22:07
 */
@Getter
public class AccountRegistry {

    private final @Nullable AccountRepository repository;

    /**
     * The accounts loaded in the registry
     */
    private final Set<AccountInfo> accounts = new HashSet<>();
    private final Map<UUID, AccountInfo> accountsById = new HashMap<>();


    public AccountRegistry(Main main) {
        this.repository = new AccountRepository(main.getDataFolder());

        this.repository.findAll().forEach(account -> {
            this.accounts.add(account);
            this.accountsById.put(account.id(), account);
        });
    }

    public void close() {
        if (this.repository == null) {
            throw new IllegalStateException("Repository not loaded");
        }

        this.accounts.forEach(this.repository::updateById);
    }
}
