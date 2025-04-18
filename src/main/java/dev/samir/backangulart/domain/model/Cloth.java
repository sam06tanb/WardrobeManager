package dev.samir.backangulart.domain.model;

import dev.samir.backangulart.domain.EnumCloth;

public class Cloth {

    private String name;
    private EnumCloth size;
    private String color;

    public Cloth(String name, EnumCloth size, String color) {
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public Cloth() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EnumCloth getSize() {
        return size;
    }

    public void setSize(EnumCloth size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
