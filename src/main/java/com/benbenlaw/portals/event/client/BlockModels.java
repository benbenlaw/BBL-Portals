package com.benbenlaw.portals.event.client;

import com.benbenlaw.portals.Portals;
import com.benbenlaw.portals.block.PortalsBlocks;
import com.benbenlaw.portals.util.CustomPortalApiRegistry;
import com.benbenlaw.portals.util.CustomPortalHelper;
import com.benbenlaw.portals.util.PortalLink;
import com.google.common.eventbus.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterBlockModelsEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.List;

@EventBusSubscriber(modid = Portals.MOD_ID, value = Dist.CLIENT)
public class BlockModels {

    @SubscribeEvent
    private static void registerBlockTintSources(RegisterColorHandlersEvent.BlockTintSources event) {
        BlockTintSource portalTint = new BlockTintSource() {
            @Override
            public int color(BlockState state) {
                return 0xFFFFFFFF;
            }

            @Override
            public int colorInWorld(BlockState state, BlockAndTintGetter level, BlockPos pos) {
                Block block = CustomPortalHelper.getPortalBase(Minecraft.getInstance().level, pos);
                PortalLink link = CustomPortalApiRegistry.getPortalLinkFromBase(block);
                if (link != null) return 0xFF000000 | link.colorID;
                return 0xFFFFFFFF;
            }
        };

        event.register(List.of(portalTint), PortalsBlocks.CUSTOM_PORTAL.get());
    }
}