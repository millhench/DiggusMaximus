package net.kyrptonaught.diggusmaximus;

import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.diggusmaximus.config.Blacklist;
import net.kyrptonaught.diggusmaximus.config.BlockCategory;
import net.kyrptonaught.diggusmaximus.config.ConfigOptions;
import net.kyrptonaught.diggusmaximus.config.ExcavatingShapes;
import net.kyrptonaught.kyrptconfig.config.ConfigManager;
import net.minecraft.util.Identifier;

public class DiggusMaximusMod implements ModInitializer {
    public static final String MOD_ID = "diggusmaximus";
    public static ConfigManager configManager = new ConfigManager.MultiConfigManager(MOD_ID);


    @Override
    public void onInitialize() {
        configManager.registerFile("config.json5", new ConfigOptions());
        configManager.registerFile("blacklist.json5", new Blacklist());
        configManager.registerFile("grouping.json5", new BlockCategory());
        configManager.registerFile("excavatingshapes.json5", new ExcavatingShapes());
        configManager.load();
        getGrouping().generateLookup();
        getBlackList().generateLookup();
        StartExcavatePacket.registerReceivePacket();
    }

    public static ConfigOptions getOptions() {
        return (ConfigOptions) configManager.getConfig("config.json5");
    }

    public static Blacklist getBlackList() {
        return (Blacklist) configManager.getConfig("blacklist.json5");
    }

    public static BlockCategory getGrouping() {
        return (BlockCategory) configManager.getConfig("grouping.json5");
    }

    public static ExcavatingShapes getExcavatingShapes() {
        return (ExcavatingShapes) configManager.getConfig("excavatingshapes.json5");
    }

    public static Identifier getIDFromConfigLookup(Identifier blockID) {
        return getGrouping().lookup.getOrDefault(blockID, blockID);
    }
}
