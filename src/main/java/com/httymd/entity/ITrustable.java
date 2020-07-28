package com.httymd.entity;

import net.minecraft.entity.EntityLivingBase;

import java.util.List;

public interface ITrustable
{

    List<EntityLivingBase> getEnemyEntities();

    int getEnemyThreshold();

    /**
     * Retrieves the level of trust from this entity for specific entities, some actions are able to increase this while
     * others decrease it, determines how it reacts to you
     *
     * @param entityLiving The entity to trust
     */
    int getTrustLevelFor(EntityLivingBase entityLiving);

}
