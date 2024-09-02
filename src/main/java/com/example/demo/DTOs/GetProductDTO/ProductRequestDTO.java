package com.example.demo.DTOs.GetProductDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
    String name;
    double price;
    String description;
    String category;
    String imageURL;

}
