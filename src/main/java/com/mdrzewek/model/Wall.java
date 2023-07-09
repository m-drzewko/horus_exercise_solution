package com.mdrzewek.model;

import com.mdrzewek.interfaces.Block;
import com.mdrzewek.interfaces.CompositeBlock;
import com.mdrzewek.interfaces.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Wall implements Structure {

    /**
     * A list of {@link Block} instances that make up the Wall.
     */
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Uses {@link #flattenBlocks(List) flattenBlocks()} to return an {@link Optional}
     * of a random {@link Block} with the specified color.
     * @param color String that specifies the color to be looked for in {@link #blocks}
     * @return an Optional that may contain the Block with specified color
     */
    @Override
    public Optional<Block> findBlockByColor(String color) {
        return flattenBlocks(blocks)
                .stream()
                .filter(block -> block != null && block.getColor() != null && block.getColor().equals(color))
                .findAny();
    }

    /**
     * Uses {@link #flattenBlocks(List) flattenBlocks()} to return a list of all {@link Block}
     * instances making up a Wall that have the specified material.
     * @param material String that specifies the material to check for
     * @return a list of all Blocks with the specified material
     */
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return flattenBlocks(blocks).stream()
                .filter(block -> block != null && block.getMaterial() != null && block.getMaterial().equals(material))
                .collect(Collectors.toList());
    }

    /**
     * Uses {@link #flattenBlocks(List) flattenBlocks()} to provide
     * a total of {@link Block} instances that make up a Wall.
     * @return total amount of Blocks that make up the Wall
     */
    @Override
    public int count() {
        return flattenBlocks(blocks).size();
    }

    /**
     * Helper method to flatten a List of {@link Block} instances.
     * All children of composite blocks will be appended to the list
     * that is returned. The method functions recursively, therefore
     * it supports multi-level nesting of {@link CompositeBlock}s.
     * The method will ignore null values and will not add them to the returned list.
     * @param blocks a List of Blocks
     * @return a flat List of Blocks
     */
    public List<Block> flattenBlocks(List<Block> blocks) {
        List<Block> flatList = new ArrayList<>();

        if (blocks == null) {
            return flatList;
        }

        blocks.forEach(block -> {
            if (isComposite(block)) {
                flatList.addAll(flattenBlocks(((CompositeBlock)block).getBlocks()));
            } else if(block == null) return;
            else {
                flatList.add(block);
            }
        });
        return flatList;
    }

    /**
     * Helper method that checks if a {@link Block} is an instance of {@link CompositeBlock}.
     * @param block an instance of Block
     * @return true if a Block is a composite, false otherwise
     */
    public boolean isComposite(Block block) {
        return (block instanceof CompositeBlock);
    }

    public List<Block> getBlocks() {
        return blocks;
    }
}
