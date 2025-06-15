package dev.samir.backangulart.domain.model;

import dev.samir.backangulart.domain.EnumCloth;

public class Cloth {

    private Long id;
    private String name;
    private EnumCloth size;
    private String color;

    public Cloth(Long id, String name, EnumCloth size, String color) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
    }

    public Cloth() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
