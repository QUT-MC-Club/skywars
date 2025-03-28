package us.potatoboy.skywars.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import us.potatoboy.skywars.SkyWars;
import xyz.nucleoid.plasmid.api.game.GameSpaceManager;
import xyz.nucleoid.stimuli.event.EventResult;

@Mixin(ExplosionBehavior.class)
public abstract class ExplosionMixin {


    @ModifyConstant(method = "calculateDamage", constant = @Constant(doubleValue = 7.0))
    private double reduceDamage(double original, Explosion explosion, Entity entity) {
        var gameSpace = GameSpaceManager.get().byWorld(entity.getWorld());

        if (gameSpace != null && gameSpace.getBehavior().testRule(SkyWars.REDUCED_EXPLOSION_DAMAGE) == EventResult.ALLOW) {
            return 4.0D;
        }
        return original;
    }
}
