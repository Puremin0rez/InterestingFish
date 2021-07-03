package net.shotbow.interestingfish.config;

import net.shotbow.interestingfish.InterestingFish;
import net.shotbow.interestingfish.objects.Breed;
import net.shotbow.interestingfish.objects.Descriptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:10 AM
 * (c) lazertester
 */
public class InterestingConfig extends ConfigObject {

    public double baseWeight = .5;
    public double minWeight = .01;
    public double percentDescriptorChance = 75.0;
    public double percentBreedsChance = 100.0;
    public boolean requireLuckEnchant = false;
    public boolean excludeLuckEnchant = false;
    public boolean showItemFrameInfo = true;
    public String weightLabel = "<aqua>Weight: <white>";
    public String weightUnit = "lbs";
    public String caughtByLabel = "<gold>Caught by: <white>";
    public boolean caughtByNickname = false;
    public String dateLabel = "<gold>";
    public String dateFormat = "M/d/yy h:mm aa";
    public List<HashMap<String, Object>> descriptorList = new ArrayList<HashMap<String, Object>>() {{
        add(new HashMap<String, Object>() {{
            put("minWeightModifier", 5.0);
            put("maxWeightModifier", 20.0);
            put("rollWeight", 1);
            put("text", "<green><bold>Big");
        }});
        add(new HashMap<String, Object>() {{
            put("minWeightModifier", -20.0);
            put("maxWeightModifier", 0.0);
            put("rollWeight", 1);
            put("text", "<green><italic>Tiny");
        }});
        add(new HashMap<String, Object>() {{
            put("minWeightModifier", 1.0);
            put("maxWeightModifier", 100.0);
            put("rollWeight", 1);
            put("text", "<color:#4B0082>Kyori");
        }});
        add(new HashMap<String, Object>() {{
            put("minWeightModifier", 1.0);
            put("maxWeightModifier", 100.0);
            put("rollWeight", 1);
            put("text", "<rainbow>Rainbow");
        }});
        add(new HashMap<String, Object>() {{
            put("minWeightModifier", 1.0);
            put("maxWeightModifier", 100.0);
            put("rollWeight", 1);
            put("text", "<gradient:light_purple:blue><underlined>Adventurous");
        }});
    }};
    public List<HashMap<String, Object>> breedsList = new ArrayList<HashMap<String, Object>>() {{
        add(new HashMap<String, Object>() {{
            put("minWeightModifier", 1.0);
            put("maxWeightModifier", 20.0);
            put("rollWeight", 1);
            put("text", "<yellow>Trout");
        }});
        add(new HashMap<String, Object>() {{
            put("minWeightModifier", 20.0);
            put("maxWeightModifier", 50.0);
            put("rollWeight", 1);
            put("text", "<aqua>Tuna");
        }});
    }};

    public InterestingConfig(String path) {
        super(path, "config.yml");
    }

    public void initialize() {
        super.initialize();
        InterestingFish.log("Initialized with " + descriptorList.size() + " descriptors");
    }

    public Iterable<Descriptor> getDescriptors() {
        List<Descriptor> descriptors = new ArrayList<>();
        for (HashMap<String, Object> map : descriptorList) {
            Descriptor descriptor = new Descriptor();
            descriptor.setText((String) map.get("text"));
            descriptor.setMinWeightModifier((Double) map.get("minWeightModifier"));
            descriptor.setMaxWeightModifier((Double) map.get("maxWeightModifier"));
            descriptor.setRollWeight((Integer) map.get("rollWeight"));
            descriptors.add(descriptor);
        }
        return descriptors;
    }

    public Iterable<Breed> getBreeds() {
        List<Breed> breeds = new ArrayList<>();
        for (HashMap<String, Object> map : breedsList) {
            Breed breed = new Breed();
            breed.setText((String) map.get("text"));
            breed.setMinWeightModifier((Double) map.get("minWeightModifier"));
            breed.setMaxWeightModifier((Double) map.get("maxWeightModifier"));
            breed.setRollWeight((Integer) map.get("rollWeight"));
            breeds.add(breed);
        }
        return breeds;
    }
}
