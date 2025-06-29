package git.inmutable.awrewards.util.gson;

import com.google.gson.JsonObject;

public interface JsonAdapter<T> {

    /**
     * Convert the instance to a JsonObject
     * @param instance the instance to convert
     * @return the JsonObject
     */
    JsonObject toJson(T instance);

    /**
     * Convert the JsonObject to an instance.
     *
     * @param json the JsonObject to convert.
     * @return the instance.
     */
    T fromJson(JsonObject json);

}
