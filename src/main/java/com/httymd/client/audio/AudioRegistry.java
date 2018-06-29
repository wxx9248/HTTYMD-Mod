package com.httymd.client.audio;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

/**
 * Suppose to be registry for sound
 *
 * @author George Albany
 *
 * @deprecated Not Usable
 */
@Deprecated
public class AudioRegistry {

	private static final List<SoundEntry> music = new ArrayList<>();


	public static void update() {
		for (SoundEntry e : music)
			if (e.playNow)
				Minecraft.getMinecraft().getSoundHandler().playSound(e.entry);
	}

}
