package net.shotbow.interestingfish.config;

import net.shotbow.interestingfish.InterestingFish;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

// Shamelessly stolen and adapted from md_5's BungeeCord project,
// https://github.com/ElasticPortalSuite/BungeeCord
// cause he is a badass.
public abstract class ConfigObject
{
    private transient File file;
    private transient Yaml yaml;
    private transient Map<String, Object> config;

    protected ConfigObject(String path, String fileName)
    {
        new File(path).mkdir();
        file = new File(path, fileName);
    }

    public void initialize()
    {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yaml = new Yaml(options);

        try
        {
            InputStream is = new FileInputStream(file);
            config = yaml.load(is);
        }
        catch (IOException ignored)
        {
        }

        if (config == null)
        {
            config = new LinkedHashMap<String, Object>();
        }
        for (Field field : getClass().getDeclaredFields())
        {
            if (!Modifier.isTransient(field.getModifiers()))
            {
                String name = field.getName();
                try
                {
                    Object def = field.get(this);
                    Object value = get(name, def);
                    setField(field, value);

                }
                catch (IllegalAccessException ex)
                {
                    InterestingFish.log(getClass().getSimpleName() + ": Unable to initialize");
                }
            }
        }
    }

    protected void setField(Field field, Object value)
    {
        try
        {
            field.set(this, value);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    private <T> Object get(String path, T def)
    {
        if (!config.containsKey(path))
        {
            save();
        }
        return config.get(path);
    }

    public void save()
    {

        for (Field field : getClass().getDeclaredFields())
        {
            if (!Modifier.isTransient(field.getModifiers()))
            {
                String name = field.getName();
                try
                {
                    config.put(name, field.get(this));
                }
                catch (IllegalAccessException ex)
                {
                }
            }
        }
        try
        {
            FileWriter wr = new FileWriter(file);
            yaml.dump(config, wr);
        }
        catch (IOException ex)
        {
        }
    }

    public void delete()
    {
        file.delete();
    }
}
