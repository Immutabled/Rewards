package git.inmutable.awrewards.util.gson.repository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import git.inmutable.awrewards.util.gson.JsonAdapter;
import lombok.AllArgsConstructor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 06, 05:44
 */
@AllArgsConstructor
public abstract class FileRepository<T> {

    protected final JsonAdapter<T> adapter;

    protected final File container;

    protected abstract File getFile(T type);

    public List<T> findAll() {
        File[] files = container.listFiles();
        List<T> result = new ArrayList<>();
        if (files == null) return result;

        for (File file : files) {
            if (file == null) continue;

            try (FileReader reader = new FileReader(file)) {
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                T obj = adapter.fromJson(jsonObject);
                if (obj != null) {
                    result.add(obj);
                }
            } catch (IOException | IllegalStateException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public void updateById(T type) {
        File file = getFile(type);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(Objects.requireNonNull(adapter.toJson(type)).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(T type) {
        File file = getFile(type);
        if (!file.exists()) return;
        file.delete();
    }
}
