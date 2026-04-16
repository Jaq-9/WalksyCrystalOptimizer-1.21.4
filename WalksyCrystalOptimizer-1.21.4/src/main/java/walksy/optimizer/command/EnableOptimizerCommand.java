package walksy.optimizer.command;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class EnableOptimizerCommand {

    public static boolean fastCrystal = false;

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(
            literal("crystaloptimizer")
                .executes(context -> {
                    fastCrystal = !fastCrystal;
                    context.getSource().sendFeedback(
                        Text.literal("Crystal Optimizer: " + (fastCrystal ? "§aEnabled" : "§cDisabled"))
                    );
                    return 1;
                })
        );
    }
}
