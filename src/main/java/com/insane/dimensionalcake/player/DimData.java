package com.insane.dimensionalcake.player;

public class DimData implements IDimData {

	int dimension, x, y, z = 0;

	@Override
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void setZ(int z) {
		this.z = z;
	}

}
