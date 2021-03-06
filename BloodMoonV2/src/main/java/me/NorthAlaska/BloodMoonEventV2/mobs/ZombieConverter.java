package me.NorthAlaska.BloodMoonEventV2.mobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.NorthAlaska.BloodMoonEventV2.Main;
import me.NorthAlaska.BloodMoonEventV2.utils.Utils;

public class ZombieConverter 
{
	private Zombie zombie;
	private Main plugin;
	
	
	public ZombieConverter(Zombie zombie, Main plugin)
	{
		this.zombie = zombie;
		this.plugin = plugin;
		int health = Utils.nextInt(plugin.getConfig().getInt("zombieMinHealth"), plugin.getConfig().getInt("zombieMaxHealth"));
		zombie.setCustomName(Utils.chat("&4&lBlood Zombie"));
		zombie.setCustomNameVisible(true);
		zombie.setMaxHealth(health);
		zombie.setHealth(health);
		generateLoot(zombie);
	}
	
	
	private void generateLoot(Zombie z)
	{
		createEffects(z);
		createArmor(z);
		createWeapon(z);
		z.getEquipment().setBootsDropChance((float) plugin.getConfig().getDouble("bootsDropChance"));
		z.getEquipment().setHelmetDropChance((float) plugin.getConfig().getDouble("helmetDropChance"));
		z.getEquipment().setChestplateDropChance((float) plugin.getConfig().getDouble("chestplateDropChance"));
		z.getEquipment().setLeggingsDropChance((float) plugin.getConfig().getDouble("leggingDropChance"));
		z.getEquipment().setItemInMainHandDropChance((float) plugin.getConfig().getDouble("weaponDropChance"));
	}
	private void createWeapon(Zombie z)
	{
		Material[] axes = {Material.NETHERITE_AXE, Material.DIAMOND_AXE, Material.IRON_AXE}; 
		Material[] swords = {Material.NETHERITE_SWORD, Material.DIAMOND_SWORD, Material.IRON_SWORD};
		int hasWeapon = (int)(Math.random() * 100);
		int isAxe = (int)(Math.random() * 3);
		int type = (int)(Math.random() * 3);
		if (hasWeapon <= plugin.getConfig().getInt("weaponPercentage"))
		{
			if (isAxe == 1)
			{
				z.getEquipment().setItemInMainHand(weapon(axes[type]));
			}
			else if(isAxe == 0)
			{
				z.getEquipment().setItemInMainHand(weapon(swords[type]));
			}
		}
	}
	private void createArmor(Zombie z) 
	{
		int chestplate = (int)(Math.random() * 5);
		if (chestplate == 0)
		{
			z.getEquipment().setChestplate(chestplate(Material.NETHERITE_CHESTPLATE));
		}
		else if (chestplate == 2)
		{
			z.getEquipment().setChestplate(chestplate(Material.DIAMOND_CHESTPLATE));
		}
		else if (chestplate == 4)
		{
			z.getEquipment().setChestplate(chestplate(Material.IRON_CHESTPLATE));
		}
		
		int leggings = (int)(Math.random() * 10);
		if (leggings == 0)
			z.getEquipment().setLeggings(chestplate(Material.NETHERITE_LEGGINGS));
		else if (leggings == 4)
			z.getEquipment().setLeggings(chestplate(Material.DIAMOND_LEGGINGS));
		else if (leggings == 8)
			z.getEquipment().setLeggings(chestplate(Material.IRON_LEGGINGS));
		
		int boots = (int)(Math.random() * 10);
		
		if (boots == 0)
			z.getEquipment().setBoots(chestplate(Material.NETHERITE_BOOTS));
		if (boots == 4)
			z.getEquipment().setBoots(chestplate(Material.DIAMOND_BOOTS));
		if (boots == 8)
			z.getEquipment().setBoots(chestplate(Material.IRON_BOOTS));
		
			
	}
	
	private ItemStack weapon(Material m)
	{
		int hasSharpness = (int)(Math.random() + 100);
		int hasFireAspect = (int)(Math.random() + 100);
		int sharpLevel = Utils.nextInt(1, 15);
		
		ItemStack weapon = new ItemStack(m, 1);
		if (hasSharpness <= plugin.getConfig().getInt("sharpnessPercentage"))
		{
			if (hasFireAspect <= plugin.getConfig().getInt("fireAspectPercentage"))
				weapon.addEnchantment(Enchantment.FIRE_ASPECT, 2);
			weapon.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, sharpLevel);
		}
		return weapon;
	}
	
	private ItemStack chestplate(Material m)
	{
		int enchant = (int)(Math.random() * 10);
		ItemStack helmet1 = new ItemStack(m, 1);
		if (enchant != 0 && enchant < 5)
			helmet1.addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, enchant);
		if (enchant == 2)
			helmet1.addEnchantment(Enchantment.THORNS, 3);
		return helmet1;
	}

	private void createEffects(Zombie z)
	{
		int strength = Utils.nextInt(plugin.getConfig().getInt("minStrength"), plugin.getConfig().getInt("maxStrength"));
		int random = (int)(Math.random() * 5);
		if (random == 0)
		{
			PotionEffect e = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5000, strength);
			PotionEffect e1 = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 5000, 1);
			z.addPotionEffect(e);
			z.addPotionEffect(e1);
		}
		else if(random == 1)
		{
			PotionEffect e = new PotionEffect(PotionEffectType.SPEED, 5000, 3);
			z.addPotionEffect(e);
		}
		else if(random == 2)
		{
			PotionEffect e1 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5000, strength);
			z.addPotionEffect(e1);
		}
		else if(random == 3)
		{
			PotionEffect e = new PotionEffect(PotionEffectType.SPEED, 5000, 3);
			PotionEffect e1 = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5000, strength);
			z.addPotionEffect(e);
			z.addPotionEffect(e1);
		}
		else if(random == 4)
		{
			PotionEffect e = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 5000, strength);
			z.addPotionEffect(e);
		}
	}
	
}
