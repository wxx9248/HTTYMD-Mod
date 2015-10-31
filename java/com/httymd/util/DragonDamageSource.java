package com.httymd.util;

import org.apache.commons.lang3.RandomUtils;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;

public class DragonDamageSource {

	public static EntityDamageSource getAbilityDamage(Entity attacker, Entity source, String add)
	{
		return attacker == null ?  new EntityDamageSource(Utils.getModString("dragon.ability") + add, source) : 
					new EntityDamageSourceIndirect(Utils.getModString("dragon.ability") + add, source, attacker);
	}
	
	public static EntityDamageSource getAbilityDamage(Entity attacker, Entity source)
	{
		return getAbilityDamage(attacker, source, "");
	}
	
	public static EntityDamageSource getProjectileDamage(Entity attacker, Entity source, String add)
	{
		EntityDamageSource ds = getAbilityDamage(attacker, source, ".projectile"+add);
		ds.setProjectile();
		return ds;
	}
	
	public static EntityDamageSource getProjectileDamage(Entity attacker, Entity source)
	{
		return getProjectileDamage(attacker, source, "");
	}
	
	public static EntityDamageSource getProjectileFireDamage(Entity attacker, Entity source)
	{
		EntityDamageSource ds = getProjectileDamage(attacker, source, ".fire");
		ds.setFireDamage();
		return ds;
	}

	public static EntityDamageSource getDirectDamage(Entity attacker)
	{
		return new EntityDamageSource(Utils.getModString("dragon.direct."+RandomUtils.nextInt(1, 3)), attacker);
	}
	
}
