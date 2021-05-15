package io.github.fallOut015.battleaxe.item;

import io.github.fallOut015.battleaxe.Main;
import io.github.fallOut015.battleaxe.enchantment.EnchantmentTypeBattleaxe;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsBattleaxe {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
	
    public static final RegistryObject<Item> WOODEN_BATTLEAXE = ITEMS.register("wooden_battleaxe", () -> new BattleaxeItem(ItemTier.WOOD, 6, -2.4F, (new Item.Properties()).tab(ItemGroup.TAB_COMBAT)));
	public static final RegistryObject<Item> STONE_BATTLEAXE = ITEMS.register("stone_battleaxe", () -> new BattleaxeItem(ItemTier.STONE, 7, -2.4F, (new Item.Properties()).tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> GOLDEN_BATTLEAXE = ITEMS.register("golden_battleaxe", () -> new BattleaxeItem(ItemTier.GOLD, 6, -2.4F, (new Item.Properties()).tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> IRON_BATTLEAXE = ITEMS.register("iron_battleaxe", () -> new BattleaxeItem(ItemTier.IRON, 6, -2.4F, (new Item.Properties()).tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> DIAMOND_BATTLEAXE = ITEMS.register("diamond_battleaxe", () -> new BattleaxeItem(ItemTier.DIAMOND, 5, -2.4F, (new Item.Properties()).tab(ItemGroup.TAB_COMBAT)));
    public static final RegistryObject<Item> NETHERITE_BATTLEAXE = ITEMS.register("netherite_battleaxe", () -> new BattleaxeItem(ItemTier.NETHERITE, 5, -2.4f, new Item.Properties().tab(ItemGroup.TAB_COMBAT)));

	public static void register(IEventBus bus) {
		ITEMS.register(bus);
		
		ItemGroup.TAB_COMBAT.setEnchantmentCategories(EnchantmentType.VANISHABLE, EnchantmentType.ARMOR, EnchantmentType.ARMOR_FEET, EnchantmentType.ARMOR_HEAD, EnchantmentType.ARMOR_LEGS, EnchantmentType.ARMOR_CHEST, EnchantmentType.BOW, EnchantmentType.WEAPON, EnchantmentType.WEARABLE, EnchantmentType.BREAKABLE, EnchantmentType.TRIDENT, EnchantmentType.CROSSBOW, EnchantmentTypeBattleaxe.BATTLEAXE);
	}
}