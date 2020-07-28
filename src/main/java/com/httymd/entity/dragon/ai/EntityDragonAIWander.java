package com.httymd.entity.dragon.ai;

import com.httymd.entity.EntityDragon;
import com.httymd.util.Utils;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

public class EntityDragonAIWander extends EntityAIBase
{

    protected final EntityDragon dragon;
    protected       Vec3d        target;

    public EntityDragonAIWander(EntityDragon creature)
    {
        this.dragon = creature;
        this.setMutexBits(3);
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        final float minTargetDist = 15f;
        this.dragon.moveRelative(1, 0, 0.25F, 1);
        return this.target != null && Utils.isTraceOpen(this.dragon.world, this.dragon.getPositionEyes(1), this.target)
               && this.dragon.getPositionEyes(1).squareDistanceTo(this.target) > minTargetDist * minTargetDist;
    }

    @Override
    public void resetTask()
    {
        this.target = null;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.dragon.getAge() >= 100 || this.dragon.getRNG().nextInt(120) != 0 || !this.dragon.isFlying())
            return false;
        else
        {
            Vec3d vec3 = RandomPositionGenerator.findRandomTarget(this.dragon, 15, 10);

            if (vec3 == null)
                return false;
            else
            {
                this.target = vec3;
                return Utils.isTraceOpen(this.dragon.world, this.dragon.getPositionEyes(1), this.target);
            }
        }
    }

    @Override
    public void startExecuting()
    {
        this.dragon.getLookHelper().setLookPosition(this.target.x, this.target.y, this.target.z,
                                                    (float) this.dragon.getFlyYaw(), (float) this.dragon.getFlyPitch());
    }

}
