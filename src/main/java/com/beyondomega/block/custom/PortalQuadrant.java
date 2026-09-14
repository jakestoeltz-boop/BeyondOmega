package com.beyondomega.block.custom;

import net.minecraft.util.StringRepresentable;

public enum PortalQuadrant implements StringRepresentable {
    NW("nw"),
    NE("ne"),
    SW("sw"),
    SE("se");

    private final String name;

    PortalQuadrant(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
