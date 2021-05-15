package io.github.fallOut015.battleaxe.enchantment;

import io.github.fallOut015.battleaxe.item.BattleaxeItem;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.item.Item;

public class EnchantmentTypeBattleaxe {
	public static final EnchantmentType BATTLEAXE = EnchantmentType.create("battleaxe", EnchantmentTypeBattleaxe::isBattleaxe);

	public static boolean isBattleaxe(Item item) {
		return item instanceof BattleaxeItem;
	}
}