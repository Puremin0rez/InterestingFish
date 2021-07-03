package net.shotbow.interestingfish.config;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

// Shamelessly stolen and adapted from md_5's BungeeCord project,
// https://github.com/ElasticPortalSuite/BungeeCord
// cause he is a badass.
public abstract class ConfigObject {
    private final transient File file;
    private transient Yaml yaml;
    private transient Map<String, Object> config;

    protected ConfigObject(String path, String fileName) {
        new File(path).mkdir();
        file = new File(path, fileName);
    }

    public void initialize() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yaml = new Yaml(options);

        try {
            InputStream is = new FileInputStream(file);
            config = yaml.load(is);
        } catch (IOException ignored) {
        }

        if (config == null) {
            config = new LinkedHashMap<>();
        }
        for (Field field : getClass().getDeclaredFields()) {
            if (!Modifier.isTransient(field.getModifiers())) {
                String name = field.getName();
                Object value = get(name);
                setField(field, value);
            }
        }
    }

    protected void setField(Field field, Object value) {
        try {
            field.set(this, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private Object get(String path) {
        if (!config.containsKey(path)) {
            save();
        }
        return config.get(path);
    }

    public void save() {

        for (Field field : getClass().getDeclaredFields()) {
            if (!Modifier.isTransient(field.getModifiers())) {
                String name = field.getName();
                try {
                    config.put(name, field.get(this));
                } catch (IllegalAccessException ignored) {
                }
            }
        }
        try {
            FileWriter wr = new FileWriter(file);
            yaml.dump(config, wr);
        } catch (IOException ignored) {
        }
    }

}
