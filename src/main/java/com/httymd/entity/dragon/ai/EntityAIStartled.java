package com.httymd.entity.dragon.ai;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.math.Vec3d;

import com.httymd.entity.EntityDragon;

public class EntityAIStartled extends EntityAIBase {

	protected final EntityDragon dragon;
	protected final Class<? extends EntityLivingBase> avoidClass;
	protected final float reactDist;
	protected final double startledSpeed;
	protected final float attackChance;

	protected boolean willRun;
	protected EntityLivingBase closestLiving;
	protected Path entityPathEntity;

	public EntityAIStartled(EntityDragon entity, Class<? extends EntityLivingBase> avoid, float runDist,
			double nearSpeed, float attackChance) {
		this.dragon = entity;
		this.avoidClass = avoid;
		this.reactDist = runDist;
		this.startledSpeed = nearSpeed;
		this.attackChance = attackChance;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldContinueExecuting() {
		return this.dragon.isStartled() && !this.dragon.getNavigator().noPath();
	}

	@Override
	public void resetTask() {
		this.closestLiving = null;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	@Override
	public boolean shouldExecute() {
		if (!this.dragon.isStartled() || this.dragon.isTamed())
			return false;

		if (this.avoidClass == EntityPlayer.class && (this.closestLiving = this.dragon.world
				.getClosestPlayerToEntity(this.dragon, this.reactDist)) == null)
			return false;
		else {
			List list = this.dragon.world.getEntitiesWithinAABB(this.avoidClass,
					this.dragon.getEntityBoundingBox().expand(this.reactDist, this.reactDist * 0.4, this.reactDist));
			list = (List) list.stream().filter(new Predicate() {
				@Override
				public boolean test(Object ei) {
					Entity e = (Entity) ei;
					return e.isEntityAlive() && EntityAIStartled.this.dragon.getEntitySenses().canSee(e);
				}
			}).collect(Collectors.toList());

			if (list.isEmpty())
				return false;

			this.closestLiving = (EntityLivingBase) list.get(0);
		}

		if (!(this.willRun = this.dragon.getRNG().nextFloat() <= this.attackChance))
			return true;

		Vec3d vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.dragon, 16, 7,
				new Vec3d(this.closestLiving.getPosition().up()));

		if (vec3 == null || this.closestLiving.getDistanceSq(vec3.x, vec3.y, vec3.z) < this.closestLiving
				.getDistanceSq(this.dragon))
			return false;
		else {
			this.entityPathEntity = this.dragon.getNavigator().getPathToXYZ(vec3.x, vec3.y, vec3.z);
			return this.entityPathEntity == null ? false : this.entityPathEntity.getCurrentPos().equals(vec3);
		}
	}

	@Override
	public void startExecuting() {
		if (this.willRun)
			this.dragon.getNavigator().setPath(this.entityPathEntity, this.startledSpeed);
	}

	@Override
	public void updateTask() {
		if (!this.willRun)
			this.dragon.setAttackTarget(this.closestLiving);
	}

}
