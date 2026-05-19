package com.thewandererraven.ravenbrewslib;


import com.thewandererraven.ravenbrewslib.brewing.base.BrewBaseReloadListener;
import com.thewandererraven.ravenbrewslib.brewing.ingredient.BrewIngredientReloadListener;
import com.thewandererraven.ravenbrewslib.brewing.variant.BrewVariantReloadListener;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

@Mod(Constants.MOD_ID)
public class RavenBrewsLibNeoForge {

    public RavenBrewsLibNeoForge(IEventBus eventBus) {

        //eventBus.addListener(this::addCreative);

        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        Constants.LOG.info("Hello NeoForge world!");
        RavenBrewsLibCommon.init();
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            //event.accept(CommonItems.TEST_ITEM);
        }
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
    public static class ClientNeoForgeEvents {
        @SubscribeEvent
        public static void registerReloadListeners(AddServerReloadListenersEvent event) {
            event.addListener(BrewIngredientReloadListener.getReloadListenerId(), new BrewIngredientReloadListener());
            event.addListener(BrewBaseReloadListener.getReloadListenerId(), new BrewBaseReloadListener());
            event.addListener(BrewVariantReloadListener.getReloadListenerId(), new BrewVariantReloadListener());
        }
    }
}