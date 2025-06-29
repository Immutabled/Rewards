package git.inmutable.awrewards.account;

import com.google.gson.JsonObject;
import git.inmutable.awrewards.util.gson.JsonAdapter;
import git.inmutable.awrewards.util.gson.repository.FileRepository;

import java.io.File;
import java.util.UUID;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 03, 23:54
 */

public class AccountRepository extends FileRepository<AccountInfo> {

    public AccountRepository(File file) {
        super(new JsonAdapter<>() {
                  /**
                   * Convert the instance to a JsonObject
                   *
                   * @param instance the instance to convert
                   * @return the JsonObject
                   */
                  @Override
                  public JsonObject toJson(AccountInfo instance) {
                      JsonObject json = new JsonObject();
                      json.addProperty("_id", instance.id().toString());
                      json.addProperty("name", instance.name());
                      json.addProperty("firstLogin", instance.firstLogin());
                      json.addProperty("playtime", instance.playtime());
                      json.addProperty("lastLogin", instance.lastLogin());
                      json.addProperty("lastQuit", instance.lastQuit());
                      return json;
                  }

                  /**
                   * Convert the JsonObject to an instance
                   *
                   * @param json the JsonObject to convert
                   * @return the instance
                   */
                  @Override
                  public AccountInfo fromJson(JsonObject json) {
                      String id = json.get("_id").getAsString();
                      if (id == null) {
                          throw new IllegalStateException("Failed to load AccountInfo: id is null");
                      }

                      String name = json.get("name").getAsString();
                      if (name == null) {
                          throw new IllegalStateException("Failed to load AccountInfo: name is null");
                      }

                      Long firstLogin = json.get("firstLogin").getAsLong();
                      Long playtime = json.get("playtime").getAsLong();
                      Long lastLogin = json.get("lastLogin").getAsLong();
                      Long lastQuit = json.get("lastQuit").getAsLong();

                      return new AccountInfo(
                              UUID.fromString(id),
                              name,
                              firstLogin,
                              playtime,
                              lastLogin,
                              lastQuit
                      );
                  }
              }, new File(file,"accounts")
        );
    }

    @Override
    protected File getFile(AccountInfo type) {
        return new File(type.name() + ".json");
    }
}
