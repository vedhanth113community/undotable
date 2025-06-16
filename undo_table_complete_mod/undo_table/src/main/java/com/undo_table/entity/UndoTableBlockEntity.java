
package com.undo_table.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import com.undo_table.UndoTableMod;

public class UndoTableBlockEntity extends BlockEntity {
    public UndoTableBlockEntity(BlockPos pos, BlockState state) {
        super(UndoTableMod.UNDO_TABLE_BE, pos, state);
    }

    public boolean undoItem(ServerPlayer player) {
        ItemStack held = player.getMainHandItem();
        if (held.isEmpty()) return false;

        SimpleContainer container = new SimpleContainer(1);
        container.setItem(0, held.copy());

        RecipeManager manager = player.server.getRecipeManager();
        var recipe = manager.getRecipeFor(RecipeType.CRAFTING, container, player.level()).orElse(null);
        if (recipe == null) return false;

        // Remove 1 crafted item
        held.shrink(1);

        for (var ingredient : recipe.getIngredients()) {
            for (ItemStack stack : ingredient.getItems()) {
                player.getInventory().add(stack.copy());
                break; // Add only one of each ingredient
            }
        }
        return true;
    }
}
