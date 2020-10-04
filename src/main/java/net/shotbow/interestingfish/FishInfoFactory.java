package net.shotbow.interestingfish;

import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.shotbow.interestingfish.config.InterestingConfig;
import net.shotbow.interestingfish.objects.Breed;
import net.shotbow.interestingfish.objects.Descriptor;
import net.shotbow.interestingfish.objects.FishInfo;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * User: Austin
 * Date: 5/18/13
 * Time: 1:21 AM
 * (c) lazertester
 */
public class FishInfoFactory
{

    private List<String> weightedDescriptors = new ArrayList<String>();
    private HashMap<String, Descriptor> descriptorIndex = new HashMap<String, Descriptor>();

    private List<String> weightedBreeds = new ArrayList<String>();
    private HashMap<String, Breed> breedIndex = new HashMap<String, Breed>();

    private Random random = new Random();
    private InterestingConfig config;

    private final MiniMessage miniMessage = MiniMessage.get();
    private final LegacyComponentSerializer legacyHexSerializer = LegacyComponentSerializer.builder().hexColors().useUnusualXRepeatedCharacterHexFormat().build();

    public FishInfoFactory(InterestingConfig config)
    {
        this.config = config;
        for (Descriptor descriptor : config.getDescriptors())
        {
            descriptorIndex.put(descriptor.getText(), descriptor);
            for (int i = 0; i <= descriptor.getRollWeight(); i++)
                weightedDescriptors.add(descriptor.getText());
        }
        for (Breed breed : config.getBreeds())
        {
            breedIndex.put(breed.getText(), breed);
            for (int i = 0; i <= breed.getRollWeight(); i++)
                weightedBreeds.add(breed.getText());
        }
        InterestingFish.log(descriptorIndex.size() + " Descriptors & " + breedIndex.size() + " Breeds loaded");

    }

    public FishInfo makeNewFishInfo()
    {
        StringBuilder nameBuilder = new StringBuilder();
        double weight = config.baseWeight;
        if (random.nextInt(100) < config.percentDescriptorChance)
        {
            Descriptor descriptor = descriptorIndex.get(weightedDescriptors.get(random.nextInt(weightedDescriptors.size())));
            nameBuilder.append(parseColors(descriptor.getText())).append(ChatColor.RESET).append(" ");
            weight += descriptor.getMinWeightModifier() + random.nextDouble() * descriptor.getMaxWeightModifier() - descriptor.getMinWeightModifier();
        }
        Breed breed = breedIndex.get(weightedBreeds.get(random.nextInt(weightedBreeds.size())));
        weight += breed.getMinWeightModifier() + random.nextDouble() * breed.getMaxWeightModifier() - breed.getMinWeightModifier();
        nameBuilder.append(parseColors(breed.getText()));
        if (weight < config.minWeight)
            weight = config.minWeight;
        return new FishInfo(nameBuilder.toString(), weight);
    }

    public String parseColors(String text)
    {
        return legacyHexSerializer.serialize(miniMessage.parse(text.replace('&', ChatColor.COLOR_CHAR)));
    }

}
