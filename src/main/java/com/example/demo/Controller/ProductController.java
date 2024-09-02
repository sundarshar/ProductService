package com.example.demo.Controller;

import com.example.demo.DTOs.GetProductDTO.FakeStoreProductDTO;
import com.example.demo.DTOs.GetProductDTO.ProductRequestDTO;
import com.example.demo.DTOs.GetProductDTO.PutRequestDTO;
import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Repository.Projection.ProductProjection;
import com.example.demo.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    public ProductService productService;
    public ProductController(@Qualifier("SelfProductServices") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @PostMapping("/products/")
    public FakeStoreProductDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return productService.createProduct(productRequestDTO);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") long id){
        productService.delete(id);
    }

    @PatchMapping("/{id}")
    public FakeStoreProductDTO update(@PathVariable("id") long id ,@RequestBody ProductRequestDTO productRequestDTO){
        return productService.update(id, productRequestDTO);
    }

    @PutMapping("/{id}")
    public boolean putUpdate(@PathVariable("id") long id, @RequestBody PutRequestDTO putRequestDTO){
        return productService.putRequestDTO(id,putRequestDTO);
    }

    @GetMapping("/category/{name}")
    public List<Product> productsBasedOnCategory(@PathVariable("name") String name){
        return productService.productsBasedOnCategory(name);
    }

    @GetMapping("/allProducts/category/{name}")
    public List<Product> allProductsBasedOnCategory(@PathVariable("name") String category){
        return productService.allProductBasedOnCategory(category);
    }

    @GetMapping("/withName/{name}/{id}")
    public Product findProductWithNameAndId(@PathVariable("name") String Name,@PathVariable("id") Long id){
        return productService.findProductWithNameAndId(Name,id);
    }

    @GetMapping("/category/findTitle/{name}")
    public List<Product> findTitleOfProductByCategory(@PathVariable("name") String name){
        return productService.findTitleOfProductByCategory(name);
    }

}
