package com.buuz135.catjammies;

import net.minecraft.block.JukeboxBlock;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public interface IJammyDetector {

	List<IJammyDetector> DETECTORS = new ArrayList<>();
	IJammyDetector MUSIC = (world, entity) -> {
		int jamDistance = 5;
		int jamAmount = 0;
		AxisAlignedBB axisAlignedBB = new AxisAlignedBB(entity.getX() - (double) jamDistance, entity.getY() - (double) jamDistance, entity.getZ() - (double) jamDistance, entity.getX() + (double) jamDistance, entity.getY() + (double) jamDistance, entity.getZ() + (double) jamDistance);

		for (BlockPos blockPos : getBlockPosInAABB(axisAlignedBB)) {
			if (entity.level.getBlockState(blockPos).getBlock() instanceof JukeboxBlock && entity.level.getBlockState(blockPos).getValue(JukeboxBlock.HAS_RECORD)) {
				++jamAmount;
			}
		}

		return jamAmount;
	};
	IJammyDetector ME = (world, entity) -> {
		int jamDistance = 10;
		AxisAlignedBB axisAlignedBB = new AxisAlignedBB(entity.getX() - (double) jamDistance, entity.getY() - (double) jamDistance, entity.getZ() - (double) jamDistance, entity.getX() + (double) jamDistance, entity.getY() + (double) jamDistance, entity.getZ() + (double) jamDistance);
		return world.getEntitiesOfClass(PlayerEntity.class, axisAlignedBB, (playerEntity) -> playerEntity.getDisplayName().getString().equals("Buuz135")).size();
	};

	int detect(World world, CatEntity catEntity);

	static List<BlockPos> getBlockPosInAABB(AxisAlignedBB axisAlignedBB) {
		List<BlockPos> blocks = new ArrayList<>();

		for (double y = axisAlignedBB.minY; y < axisAlignedBB.maxY; ++y) {
			for (double x = axisAlignedBB.minX; x < axisAlignedBB.maxX; ++x) {
				for (double z = axisAlignedBB.minZ; z < axisAlignedBB.maxZ; ++z) {
					blocks.add(new BlockPos(x, y, z));
				}
			}
		}

		return blocks;
	}
}
