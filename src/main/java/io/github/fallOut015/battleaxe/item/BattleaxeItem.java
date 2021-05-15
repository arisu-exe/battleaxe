package io.github.fallOut015.battleaxe.item;

import java.util.Set;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import io.github.fallOut015.battleaxe.enchantment.EnchantmentsBattleaxe;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.IVanishable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BattleaxeItem extends TieredItem implements IVanishable {
	private static final Set<Material> DIGGABLE_MATERIALS = Sets.newHashSet(Material.WOOD, Material.NETHER_WOOD, Material.PLANT, Material.REPLACEABLE_PLANT, Material.BAMBOO, Material.VEGETABLE);
	private final float attackDamage;
	private final Multimap<Attribute, AttributeModifier> attributeModifiers;

	public BattleaxeItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Item.Properties properties) {
		super(tier, properties);
		this.attackDamage = (float)attackDamageIn + tier.getAttackDamageBonus();
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)attackSpeedIn, AttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
	}

	public float getDamage() {
		return this.attackDamage;
	}
	
	@Override
	public boolean canAttackBlock(BlockState state, World worldIn, BlockPos pos, PlayerEntity player) {
		return !player.isCreative();
	}
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state) {
		Material material = state.getMaterial();
		return DIGGABLE_MATERIALS.contains(material) ? this.getTier().getSpeed() : super.getDestroySpeed(stack, state);
	}
	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker) {
		return EnchantmentHelper.getItemEnchantmentLevel(EnchantmentsBattleaxe.DISABLING.get(), stack) > 0;
	}

	/*@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		boolean attackerNotEmpty = !stack.isEmpty();
		boolean targetNotEmpty = !target.getUseItem().isEmpty();
		boolean attackStackHasDisabling = EnchantmentHelper.getItemEnchantmentLevel(EnchantmentsBattleaxe.DISABLING.get(), stack) > 0;
		boolean targetShielding = target.getUseItem().getItem() == Items.SHIELD;
		boolean targetIsPlayer = target instanceof PlayerEntity;
		
		if (attackerNotEmpty && targetNotEmpty && attackStackHasDisabling && targetShielding && targetIsPlayer) {
			((PlayerEntity) target).getCooldowns().addCooldown(Items.SHIELD, 100);
			attacker.level.broadcastEntityEvent(target, (byte)30);
		}
			
		stack.hurtAndBreak(1, attacker, player -> {
			player.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
		});
		return true;
	}*/
	
	@Override
	public boolean mineBlock(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
		if (state.getDestroySpeed(worldIn, pos) != 0.0F) {
			// TODO hurt less if wood type
			stack.hurtAndBreak(2, entityLiving, (p_220044_0_) -> {
				p_220044_0_.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
			});
		}

		return true;
	}
	@SuppressWarnings("deprecation")
	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot) {
		return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
	}
}