package com.kreezcraft.dimensionalcake;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;

import java.util.ArrayList;
import java.util.List;

import com.kreezcraft.dimensionalcake.blocks.BlockEndCake;
import com.kreezcraft.dimensionalcake.proxy.CommonProxy;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = DimCake.MODID, name = DimCake.NAME, version = DimCake.VERSION)
public class DimCake
{
    public static final String MODID = "dimensionalcake";
    public static final String NAME = "Dimensional Cake Rebuilt";
    public static final String VERSION = "@VERSION@";
    
    @Mod.Instance(MODID)
    public static DimCake instance;
    
    @SidedProxy(clientSide = "com.kreezcraft.dimensionalcake.proxy.ClientProxy", serverSide = "com.kreezcraft.dimensionalcake.proxy.CommonProxy")
    
    public static CommonProxy proxy;
    public static Configuration config;
    public static boolean eatCakeWhenFull;
    public static BlockEndCake blockEndCake;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent a1) {
        config = new Configuration(a1.getSuggestedConfigurationFile());
        eatCakeWhenFull = config.get("general", "eatWhenFull", true).getBoolean();
        if (config.hasChanged()) {
            config.save();
        }
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent a1) {
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent a1) {
    }
}
