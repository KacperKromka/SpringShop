package com.SpringShop.demo.Data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Design {
    private final int id;
    private final String name;
    private final Type type;

    public enum Type {
        BLACKWHITE, COLOR
    }
}
