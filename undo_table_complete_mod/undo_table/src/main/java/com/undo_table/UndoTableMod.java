
package com.undo_table;

import com.undo_table.block.UndoTableBlock;
import com.undo_table.entity.UndoTableBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.core.Registry;

public class UndoTableMod implements ModInitializer {
    public static final String MOD_ID = "undo_table";
    public static Block UNDO_TABLE_BLOCK;
    public static BlockEntityType<UndoTableBlockEntity> UNDO_TABLE_BE;

    @Override
    public void onInitialize() {
        UNDO_TABLE_BLOCK = Registry.register(Registries.BLOCK, new ResourceLocation(MOD_ID, "undo_table"),
                new UndoTableBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(2.5f)));

        Registry.register(Registries.ITEM, new ResourceLocation(MOD_ID, "undo_table"),
                new BlockItem(UNDO_TABLE_BLOCK, new Item.Properties()));

        UNDO_TABLE_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, "undo_table_be"),
                FabricBlockEntityTypeBuilder.create(UndoTableBlockEntity::new, UNDO_TABLE_BLOCK).build(null));
    }
}
