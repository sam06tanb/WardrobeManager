package dev.samir.backangulart.infrastructure.entity;

import dev.samir.backangulart.domain.EnumCloth;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_cloth")
public class ClothEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EnumCloth size;
    private String color;

    public ClothEntity() {}

    public ClothEntity(Long id, String name, EnumCloth size, String color) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.color = color;
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
