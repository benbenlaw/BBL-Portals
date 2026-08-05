package com.benbenlaw.portals.client;

import com.benbenlaw.portals.event.PortalSoundEvent;
import com.benbenlaw.portals.util.CustomPortalApiRegistry;
import com.benbenlaw.portals.util.PortalLink;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class ClientPortalSounds {


    public static void playAmbience(Level world, BlockPos pos, Player player, Block portalBase) {
        if (player != Minecraft.getInstance().player || world.getRandom().nextInt(100) != 0) {
            return;
        }

        PortalLink link = CustomPortalApiRegistry.getPortalLinkFromBase(portalBase);
        if (link != null) {
            PortalSoundEvent sound = link.getInPortalAmbienceEvent().execute(player);
            if (sound != null) {
                Minecraft.getInstance().getSoundManager().play(sound.getInstance());
            }
        }
    }

}
