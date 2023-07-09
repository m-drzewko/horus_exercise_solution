package com.mdrzewek.implementation;

import com.mdrzewek.interfaces.Block;
import com.mdrzewek.interfaces.CompositeBlock;

import java.util.List;

public class CompositeBlockImpl extends BlockImpl implements CompositeBlock {

    private List<Block> blocks;

    public CompositeBlockImpl() {
    }

    public CompositeBlockImpl(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}
