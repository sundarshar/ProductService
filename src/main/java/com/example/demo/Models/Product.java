package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String name;
    private double price;
    private String description;
    private String imageURL;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;
}
