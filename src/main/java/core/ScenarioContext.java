package core;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static ScenarioContext instance; // Singleton instance
    private Map<String, Object> context; // Store data

    private ScenarioContext() {
        context = new HashMap<>();
    }

    public static synchronized ScenarioContext getInstance() {
        if (instance == null) {
            instance = new ScenarioContext();
        }
        return instance;
    }


    public void set(String key, Object value) {
        context.put(key, value);
    }

    public Object get(String key) {
        return context.get(key);
    }

    public boolean containsKey(String key) {
        return context.containsKey(key);
    }

    public void clear() {
        context.clear();
    }






}
