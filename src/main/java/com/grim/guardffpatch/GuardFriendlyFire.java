package com.grim.guardffpatch;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = GuardFFPatch.MODID)
public class GuardFriendlyFire {
    private static final ResourceLocation GUARD =
            ResourceLocation.fromNamespaceAndPath("guardvillagers", "guard");

    @SubscribeEvent
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        DamageSource src = event.getSource();
        if (!(src.getDirectEntity() instanceof Projectile)) return;

        Entity shooter = src.getEntity();
        if (shooter == null || !GUARD.equals(BuiltInRegistries.ENTITY_TYPE.getKey(shooter.getType()))) return;

        // Let intentional shots through (e.g. reputation-based aggro)
        if (shooter instanceof Mob mob && mob.getTarget() == player) return;

        event.setCanceled(true);
    }
}
