package com.httymd.util;

import com.httymd.HTTYMDMod;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.StatList;

import java.util.ArrayList;

public class NameManager
{

    private static final NameManager             INSTANCE     = new NameManager();
    private final        ArrayList<String>       randomNames  = new ArrayList<>();
    private final        ArrayList<ISpecialName> specialNames = new ArrayList<>();

    private NameManager()
    {
        this.registerRandomName("smelly");
        this.registerRandomName("angry");
        this.registerRandomName("fat");
        this.registerRandomName("big");
        this.registerRandomName("wimp");
        this.registerRandomName("dragonslayer");
        this.registerRandomName("meatslapper");
        this.registerRandomName("murderer");
        this.registerRandomName("nut");
        this.registerRandomName("jerk");
        this.registerRandomName("melon");
        this.registerRandomName("jackolatern");
        this.registerRandomName("blind");
        this.registerRandomName("crazy");
        this.registerRandomName("freak");
        this.registerRandomName("broken");
        this.registerRandomName("stupid");
        this.registerSpecialName(new ISpecialName()
        {
            @Override
            public String get(EntityLivingBase entity, String currentName)
            {
                if (!(entity instanceof EntityPlayer))
                    return null;

                String id = ((EntityPlayer) entity).getGameProfile().getId().toString();

                if ("b2848781-aafe-454b-a87d-89ceffad585f".equals(id))
                    return "s322";

                if ("5c884585-0245-4452-bcac-5005c73d3196".equals(id))
                    return "cmmr";

                return null;
            }
        });
        this.registerSpecialName(new ISpecialName()
        {
            @Override
            public String get(EntityLivingBase entity, String currentName)
            {
                if (!(entity instanceof EntityPlayerMP))
                    return null;

                EntityPlayerMP ply = (EntityPlayerMP) entity;

                if (Utils.hasPlayerGained(ply, StatList.DAMAGE_DEALT, 200000))
                {
                    return "ataryn";
                }

                if (Utils.hasPlayerGained(ply, StatListMod.distanceByDragon, 3000))
                    return "rider";

                if (Utils.hasPlayerGained(ply, StatList.PLAYER_KILLS, 100)
                    || Utils.hasPlayerGained(ply, StatList.DAMAGE_TAKEN, 9000000))
                    return "relentless";
                else if (Utils.hasPlayerGained(ply, StatList.DEATHS, 100))
                    return "slayed";
                else if (Utils.hasPlayerGained(ply, StatList.JUMP, 5000))
                    return "excited";
                else if (Utils.hasPlayerGained(ply, StatList.MOB_KILLS, 400))
                    return "champion";
                else if (Utils.hasPlayerGained(ply, StatList.BOAT_ONE_CM, 2000))
                    return "sailor";

                return null;
            }
        });
    }

    public static NameManager getInstance()
    {
        return INSTANCE;
    }

    public String getDisplayName(EntityLivingBase entity, String originalName)
    {
        String translateStr;
        String newName;

        for (ISpecialName name : this.specialNames)
        {
            newName = name.get(entity, originalName);
            if (newName != null)
            {
                translateStr = HTTYMDMod.ID + ":" + "name.special.add." + newName;
                if (I18n.hasKey(translateStr))
                    return I18n.format(translateStr, originalName);
                return originalName + " the " + newName;
            }
        }

        newName = this.randomNames.get(entity.getRNG().nextInt(this.randomNames.size()));
        return I18n.format(HTTYMDMod.ID + ":" + "name.random." + newName, originalName);
        //return newName;
    }

    public void registerRandomName(String name)
    {
        this.randomNames.add(name);
    }

    public void registerSpecialName(ISpecialName sName)
    {
        this.specialNames.add(sName);
    }

    public interface ISpecialName
    {
        String get(EntityLivingBase entity, String currentName);
    }

}
