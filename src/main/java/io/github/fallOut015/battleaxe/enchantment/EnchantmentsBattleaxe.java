package io.github.fallOut015.battleaxe.enchantment;

import io.github.fallOut015.battleaxe.Main;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentsBattleaxe {
	private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Main.MODID);
	
    public static final RegistryObject<Enchantment> DISABLING = ENCHANTMENTS.register("disabling", () -> new DisablingEnchantment(Rarity.COMMON, EnchantmentTypeBattleaxe.BATTLEAXE, EquipmentSlotType.MAINHAND));
    
	public static void register(IEventBus bus) {
		ENCHANTMENTS.register(bus);
	}
}