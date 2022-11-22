package net.matan.tutorialmod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EightBallItem extends Item {

    public EightBallItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if(!level.isClientSide() && hand == InteractionHand.MAIN_HAND){
            // output a random number
                outputRandomNumber(player);
                player.getCooldowns().addCooldown(this, 20);
            // set a cooldown
        }
        return super.use(level, player, hand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown()){
            pTooltipComponents.add(Component.literal("Right click to get random number!").withStyle(
                    ChatFormatting.AQUA, ChatFormatting.BOLD));
        }else{
            pTooltipComponents.add(Component.literal("Press SHIFT for more info").withStyle(
                    ChatFormatting.YELLOW, ChatFormatting.BOLD));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputRandomNumber(Player player){
        player.sendSystemMessage(Component.literal("Your Number is " + getRandomNumber()));
    }

    private int getRandomNumber(){
        return RandomSource.createNewThreadLocalInstance().nextInt(10);
    }
}
