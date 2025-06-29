package git.inmutable.awrewards.registry;

import git.inmutable.awrewards.util.menu.Menu;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 19, 00:10
 */
public class MenuRegistry {
 /**
  * The menus registered in the registry
  */
  public Map<UUID, Menu> menus = new HashMap<>();
}