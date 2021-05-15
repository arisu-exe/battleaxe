package io.github.fallOut015.battleaxe;

import io.github.fallOut015.battleaxe.enchantment.EnchantmentsBattleaxe;
import io.github.fallOut015.battleaxe.item.ItemsBattleaxe;
import net.minecraft.entity.monster.piglin.PiglinBruteEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// TODO 3rd party mod support
// TODO new enchantments

@Mod(Main.MODID)
public class Main {
	public static final String MODID = "battleaxe";
	
    public Main() {
    	ItemsBattleaxe.register(FMLJavaModLoadingContext.get().getModEventBus());
    	EnchantmentsBattleaxe.register(FMLJavaModLoadingContext.get().getModEventBus());
    	
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) { }
    private void doClientStuff(final FMLClientSetupEvent event) { }
    private void enqueueIMC(final InterModEnqueueEvent event) { }
    private void processIMC(final InterModProcessEvent event) { }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) { }
    
    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent event) {
    	if(event.getEntity() instanceof PiglinBruteEntity) {
    		World world = event.getWorld();
    		if(world.random.nextFloat() < 0.5f) {
    			((PiglinBruteEntity) event.getEntity()).setItemInHand(Hand.MAIN_HAND, new ItemStack(ItemsBattleaxe.GOLDEN_BATTLEAXE.get()));
    		}
    	}
    }
}