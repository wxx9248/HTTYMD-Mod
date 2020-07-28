package com.httymd.entity.dragon;

import com.httymd.entity.EntityDragon;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityGronkle extends EntityDragon
{

    public EntityGronkle(World world)
    {
        super(world);
        this.setSize(1, 1);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(22);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5F);
    }
}