package com.insane.dimensionalcake.player;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class DimStorage implements IStorage<IDimData> {

	@Override
	public NBTBase writeNBT(Capability<IDimData> capability, IDimData instance, EnumFacing side) {
		// TODO Auto-generated method stub
		return new NBTTagFloat();
	}

	@Override
	public void readNBT(Capability<IDimData> capability, IDimData instance, EnumFacing side, NBTBase nbt) {
		// TODO Auto-generated method stub
		
	}

}
