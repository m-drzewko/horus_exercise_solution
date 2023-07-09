package com.mdrzewek.model;

import com.mdrzewek.implementation.BlockImpl;
import com.mdrzewek.implementation.CompositeBlockImpl;
import com.mdrzewek.interfaces.Block;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {

    private final Block block1 = new BlockImpl("black", "metal");
    private final Block block2 = new BlockImpl("white", "wood");
    private final Block block3 = new CompositeBlockImpl(Arrays.asList(
            new BlockImpl("red", "metal"),
            new BlockImpl("black", "wood")));

    private final Block block4 = new CompositeBlockImpl(Arrays.asList(
            new BlockImpl("white", "metal"),
            new CompositeBlockImpl(Arrays.asList(
                    new BlockImpl("red", "wood"),
                    new BlockImpl("red", "plastic")
            ))
    ));

    private final Wall wall = new Wall(Arrays.asList(block1, block2, block3, block4));


    @Test
    void shouldReturnOptionalEmptyForColorGreen() {
        assertTrue(wall.findBlockByColor("green").isEmpty());
    }

    @Test
    void shouldReturnOptionalNotEmptyForColorRed() {
        assertTrue(wall.findBlockByColor("red").isPresent());
    }

    @Test
    void shouldReturnEmptyListIfMaterialNotPresent() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("clay");
        assertEquals(0, foundBlocks.size());
    }

    @Test
    void shouldReturnListOfThreeForMaterialWood() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("wood");
        assertEquals(3, foundBlocks.size());
    }

    @Test
    void shouldReturnListOfOneForMaterialPlastic() {
        List<Block> foundBlocks = wall.findBlocksByMaterial("plastic");
        assertEquals(1, foundBlocks.size());
    }

    @Test
    void countShouldReturnZeroForAListOfNulls() {
        Wall wallOfNulls = new Wall(Arrays.asList(null,
                new CompositeBlockImpl(Arrays.asList(null, null))));

        assertEquals(0, wallOfNulls.count());
    }

    @Test
    void countShouldReturnSevenForThePredefinedWall() {
        assertEquals(7, wall.count());
    }

    @Test
    void shouldReturnEmptyListIfBlocksIsNull() {
        Wall nullWall = new Wall(null);
        List<Block> emptyList = nullWall.flattenBlocks(nullWall.getBlocks());
        assertTrue(emptyList.size() == 0);
    }

    @Test
    void shouldReturnEmptyListIfAllBlocksNull() {
        Wall wallOfNulls = new Wall(Arrays.asList(null, null, null));
        List<Block> emptyList = wallOfNulls.flattenBlocks(wallOfNulls.getBlocks());
        assertTrue(emptyList.size() == 0);
    }

    @Test
    void shouldReturnFlatListOfBlocks() {
        List<Block> flatList;
        flatList = wall.flattenBlocks(wall.getBlocks());
        flatList.forEach(block -> {
            assertFalse(wall.isComposite(block));
        });
    }

    @Test
    void shouldReturnFalseIfBlockIsNull() {
        assertFalse(wall.isComposite(null));
    }

    @Test
    void shouldReturnFalseIfIsNotComposite() {
        assertFalse(wall.isComposite(block2));
    }

    @Test
    void shouldReturnTrueIfIsComposite() {
        assertTrue(wall.isComposite(block3));
    }
}