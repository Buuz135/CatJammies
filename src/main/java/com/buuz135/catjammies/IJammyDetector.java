package com.buuz135.catjammies;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.phys.AABB;

import java.util.ArrayList;
import java.util.List;

public interface IJammyDetector {

	List<IJammyDetector> DETECTORS = new ArrayList<>();
	IJammyDetector MUSIC = (level, entity) -> {
		int jamDistance = 5;
		int jamAmount = 0;
		AABB aabb = new AABB(entity.getX() - (double) jamDistance, entity.getY() - (double) jamDistance, entity.getZ() - (double) jamDistance, entity.getX() + (double) jamDistance, entity.getY() + (double) jamDistance, entity.getZ() + (double) jamDistance);

		for (BlockPos blockPos : getBlockPosInAABB(aabb)) {
			if (entity.level.getBlockState(blockPos).getBlock() instanceof JukeboxBlock && entity.level.getBlockState(blockPos).getValue(JukeboxBlock.HAS_RECORD)) {
				++jamAmount;
			}
		}

		return jamAmount;
	};
	IJammyDetector ME = (level, entity) -> {
		int jamDistance = 10;
		AABB aabb = new AABB(entity.getX() - (double) jamDistance, entity.getY() - (double) jamDistance, entity.getZ() - (double) jamDistance, entity.getX() + (double) jamDistance, entity.getY() + (double) jamDistance, entity.getZ() + (double) jamDistance);
		return level.getEntitiesOfClass(Player.class, aabb, (playerEntity) -> playerEntity.getDisplayName().getString().equals("Buuz135")).size();
	};

	int detect(Level level, Cat cat);

	static List<BlockPos> getBlockPosInAABB(AABB aabb) {
		List<BlockPos> blocks = new ArrayList<>();

		for (double y = aabb.minY; y < aabb.maxY; ++y) {
			for (double x = aabb.minX; x < aabb.maxX; ++x) {
				for (double z = aabb.minZ; z < aabb.maxZ; ++z) {
					blocks.add(new BlockPos(x, y, z));
				}
			}
		}

		return blocks;
	}
}
