package net.moddingplayground.twigs.impl.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.GeneratorOptions;
import net.moddingplayground.twigs.api.Twigs;
import net.moddingplayground.twigs.api.block.SculkPassengerBlock;

import java.util.Arrays;
import java.util.OptionalLong;

import static net.minecraft.server.command.CommandManager.*;

public interface CalculateSculkPassengerBiasCommand {
    String ID = new Identifier(Twigs.MOD_ID, "calculatesculkpassengerbias").toString();

    String SEED_KEY = "seed";
    String INVALID_SEED_KEY = "command.%s.invalid_seed".formatted(ID);
    String DIRECTIONS_RESULT_KEY = "command.%s.directions_result".formatted(ID);

    DynamicCommandExceptionType SEED_INVALID_EXCEPTION = new DynamicCommandExceptionType(seed -> Text.translatable(INVALID_SEED_KEY, seed));

    static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(literal(ID)
            .executes(CalculateSculkPassengerBiasCommand::execute)
            .then(argument(SEED_KEY, StringArgumentType.greedyString())
                .executes(CalculateSculkPassengerBiasCommand::executeSeed)
            )
        );
    }

    static int execute(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        ServerWorld world = source.getWorld();
        return sendFeedback(context, world.getSeed());
    }

    static int executeSeed(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String string = StringArgumentType.getString(context, SEED_KEY);
        OptionalLong maybeSeed = GeneratorOptions.parseSeed(string);
        if (maybeSeed.isPresent()) {
            return sendFeedback(context, maybeSeed.getAsLong());
        } else throw SEED_INVALID_EXCEPTION.create(string);
    }

    static int sendFeedback(CommandContext<ServerCommandSource> context, long seed) {
        Direction[] directions = SculkPassengerBlock.BIASED_DIRECTIONS.apply(null, seed).toArray(Direction[]::new);
        context.getSource().sendFeedback(Text.translatable(DIRECTIONS_RESULT_KEY, seed, Arrays.toString(directions)), false);
        return 1;
    }
}
