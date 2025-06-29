package git.inmutable.awrewards.rewards;

import git.inmutable.awrewards.Main;
import git.inmutable.awrewards.rewards.adapter.RewardConfigAdapter;
import git.inmutable.awrewards.rewards.calendar.CalendarReward;
import git.inmutable.awrewards.rewards.playtime.PlayTimeReward;
import git.inmutable.awrewards.util.gson.JsonConfig;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.*;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 10, 13:34
 */
@Getter
@Setter
public class RewardConfig extends JsonConfig<RewardConfig> {

    private @NonNull Set<CalendarReward> calendarRewards;
    private @NonNull Set<PlayTimeReward> playTimeRewards;

    public RewardConfig() {
        super(Main.getInstance() != null ? Main.getInstance().getDataFolder() : null, new RewardConfigAdapter());
    }

}
