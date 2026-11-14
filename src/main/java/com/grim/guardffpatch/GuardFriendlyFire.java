package com.grim.guardffpatch;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = GuardFFPatch.MODID)
public class GuardFriendlyFire {
    private static final ResourceLocation GUARD =
            ResourceLocation.fromNamespaceAndPath("guardvillagers", "guard");

    /**
     * Stray guard projectiles pass through players instead of hitting them.
     * Cancelling only the damage would make vanilla deflect the arrow off the
     * player, so the shot never reaches the mob it was aimed at.
     */
    @SubscribeEvent
    public static void onProjectileImpact(ProjectileImpactEvent event) {
        if (!(event.getRayTraceResult() instanceof EntityHitResult hit)) return;
        if (!(hit.getEntity() instanceof Player player)) return;
        if (isStrayGuardShot(event.getProjectile().getOwner(), player)) {
            event.setCanceled(true);
        }
    }

    /** Fallback for projectile damage that isn't a direct impact (e.g. firework splash). */
    @SubscribeEvent
    public static void onIncomingDamage(LivingIncomingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        DamageSource src = event.getSource();
        if (!(src.getDirectEntity() instanceof Projectile)) return;

        if (isStrayGuardShot(src.getEntity(), player)) {
            event.setCanceled(true);
        }
    }

    private static boolean isStrayGuardShot(Entity shooter, Player player) {
        if (shooter == null || !GUARD.equals(BuiltInRegistries.ENTITY_TYPE.getKey(shooter.getType()))) return false;
        // Let intentional shots through (e.g. reputation-based aggro)
        return !(shooter instanceof Mob mob && mob.getTarget() == player);
    }
}
