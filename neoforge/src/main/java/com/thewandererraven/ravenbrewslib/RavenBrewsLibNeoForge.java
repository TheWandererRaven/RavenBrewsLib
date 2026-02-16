package com.thewandererraven.ravenbrewslib;


import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

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
}