package com.insane.dimensionalcake.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.awt.TextComponent;
import java.util.Random;

import com.insane.dimensionalcake.DimCake;
import com.insane.dimensionalcake.client.IHasModel;
import com.insane.dimensionalcake.items.InitItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.potion.Potion;
import net.minecraft.init.Items;
import net.minecraft.util.EnumFacing;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.BlockCake;

public class BlockEndCake extends BlockCake implements IHasModel {

	public String name;

	public BlockEndCake(String name) {
		this.name = name;
		setCreativeTab(CreativeTabs.FOOD);
		setUnlocalizedName(DimCake.MODID + "." + name);
		setRegistryName(name);
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		boolean retval = super.onBlockActivated(worldIn, pos, state, player, hand, facing, hitX, hitY, hitZ);
		//this works need to save the overworld coords so the player can get back
		if (worldIn.provider.getDimension() == 0) {
			player.changeDimension(1);
		}
		if (worldIn.provider.getDimension() == 1) {
			player.changeDimension(0);
		}
		return retval;
	}

	@Override
	public void registerModels() {
		DimCake.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");

	}
}
