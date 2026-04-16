package walksy.optimizer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import walksy.optimizer.command.EnableOptimizerCommand;

public class WalksyCrystalOptimizerMod implements ClientModInitializer {

    public static MinecraftClient mc = MinecraftClient.getInstance();
    public static int hitCount = 0;

    public static int limitPackets() {
        return 2;
    }

    @Override
    public void onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            EnableOptimizerCommand.register(dispatcher);
        });
    }

    public static boolean canPlaceCrystal(BlockPos pos, World world) {
        BlockPos abovePos = pos.up();
        BlockState stateAt = world.getBlockState(pos);
        BlockState stateAbove = world.getBlockState(abovePos);

        boolean isValidBase = stateAt.isOf(Blocks.BEDROCK) || stateAt.isOf(Blocks.OBSIDIAN);
        boolean isAirAbove = stateAbove.isAir();

        if (!isValidBase || !isAirAbove) return false;

        // Check no crystal already at pos
        return world.getEntitiesByClass(
            EndCrystalEntity.class,
            net.minecraft.util.math.Box.of(Vec3d.ofCenter(abovePos), 2, 2, 2),
            e -> true
        ).isEmpty();
    }

    public static void placeCrystal(BlockPos pos) {
        if (mc.player == null || mc.world == null) return;
        if (!mc.player.getMainHandStack().isOf(Items.END_CRYSTAL)) return;

        BlockHitResult hitResult = new BlockHitResult(
            Vec3d.ofCenter(pos),
            Direction.UP,
            pos,
            false
        );
        mc.player.networkHandler.sendPacket(
            new PlayerInteractBlockC2SPacket(Hand.MAIN_HAND, hitResult, 0)
        );
    }
}
