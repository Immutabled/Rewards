package git.inmutable.awrewards.util.gson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.io.File;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 02, 01:32
 */
@Getter
@Setter
@AllArgsConstructor
public abstract class JsonConfig<T> {

    /**
     * The file where the config is stored
     */
    private @NonNull File file;

    /**
     * The adapter used to convert the config to and from Json
     */
    private @NonNull JsonAdapter<T> adapter;
}