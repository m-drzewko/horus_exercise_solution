package com.mdrzewek.implementation;

import com.mdrzewek.interfaces.Block;

import java.util.Objects;

public class BlockImpl implements Block {

    private String color;
    private String material;

    public BlockImpl() {
    }

    public BlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlockImpl block = (BlockImpl) o;

        if (!Objects.equals(color, block.color)) return false;
        return Objects.equals(material, block.material);
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (material != null ? material.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BlockImpl{" +
                "color='" + color + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}