package com.example.demo.DTOs.GetProductDTO;

import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTO {
    int id;
    String title;
    double price;
    String description;
    String category;
    String image;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setName(title);
        product.setPrice(price);
        product.setImageURL(image);

        Category category1 = new Category();
        category1.setTitle(category);

        product.setCategory(category1);
        return product;
    }
}
