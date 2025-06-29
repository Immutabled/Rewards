package git.inmutable.awrewards.util;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class DurationPrompt implements Prompt {

    private final Consumer<Long> callback;
    private final String text;

    public DurationPrompt(Consumer<Long> callback) {
        this.callback = callback;
        this.text = ChatColor.YELLOW + "Please type a duration for this to be added, " +
                  "or type " + ChatColor.RED + "cancel " + ChatColor.YELLOW + "to cancel.";
    }

    @Override
    public @NotNull String getPromptText(ConversationContext context) {
        return text;
    }

    /**
     * Checks to see if this prompt implementation should wait for user input
     * or immediately display the next prompt.
     *
     * @param context Context information about the conversation.
     * @return If true, the {@link Conversation} will wait for input before
     * continuing. If false, {@link #acceptInput(ConversationContext, String)} will be called immediately with {@code null} input.
     */
    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return false;
    }

    @Override
    public Prompt acceptInput(ConversationContext context, @NotNull String input) {
        Player player = (Player) context.getForWhom();

        if (input.equalsIgnoreCase("cancel")) {
            player.sendMessage(ChatColor.RED + "Cancelled.");
            return Prompt.END_OF_CONVERSATION;
        }

        try {
            TimeUtil.parseTime(input);

            return Prompt.END_OF_CONVERSATION;
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Invalid duration format. Examples: 30s, 5m, 2h, perm");
            return this;
        }
    }
}