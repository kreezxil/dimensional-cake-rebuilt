package com.kreezcraft.dimensionalcake.blocks;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.DimensionManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

import java.awt.TextComponent;
import java.util.List;
import java.util.Random;

import com.kreezcraft.dimensionalcake.DimCake;
import com.kreezcraft.dimensionalcake.client.IHasModel;
import com.kreezcraft.dimensionalcake.items.InitItems;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.potion.Potion;
import net.minecraft.init.Blocks;
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
import net.minecraft.world.WorldProvider;
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
		// this works need to save the overworld coords so the player can get back
		if (!worldIn.isRemote) {
			if (player instanceof EntityPlayerMP) {
				MinecraftServer minecraftserver = player.getServer();
				if (worldIn.provider.getDimension() == 0) {
					// player in OVERWORLD, moving to THE END
					player.changeDimension(1);
				}
				if (worldIn.provider.getDimension() == 1) {
					// player in THE END, moving to the OVERWORLD
					WorldServer worldserver0 = minecraftserver.getWorld(0);
					BlockPos blockpos = worldserver0.getTopSolidOrLiquidBlock(worldserver0.getSpawnPoint());
					teleportToDimension(player, 0, blockpos.getX(), blockpos.getY(), blockpos.getZ());
					player.respawnPlayer();
				}
			}
		}
		return retval;
	}

	public static void teleportToDimension(EntityPlayer player, int dimension, double x, double y, double z) {
		int oldDimension = player.getEntityWorld().provider.getDimension();
		EntityPlayerMP entityPlayerMP = (EntityPlayerMP) player;
		MinecraftServer server = player.getEntityWorld().getMinecraftServer();
		WorldServer worldServer = server.getWorld(dimension);
		player.addExperienceLevel(0);

		worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, dimension,
				new RfToolsTeleporter(worldServer, x, y, z));
		player.setPositionAndUpdate(x, y, z);
		if (oldDimension == 1) {
			// For some reason teleporting out of the end does weird things.
			player.setPositionAndUpdate(x, y, z);
			worldServer.spawnEntity(player);
			worldServer.updateEntityWithOptionalForce(player, false);
		}
	}

	@Override
	public void registerModels() {
		DimCake.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");

	}
}
