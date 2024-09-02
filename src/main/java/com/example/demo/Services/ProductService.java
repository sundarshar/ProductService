package com.example.demo.Services;

import com.example.demo.DTOs.GetProductDTO.FakeStoreProductDTO;
import com.example.demo.DTOs.GetProductDTO.ProductRequestDTO;
import com.example.demo.DTOs.GetProductDTO.PutRequestDTO;
import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Repository.Projection.ProductProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product getProduct(long id) throws ProductNotFoundException;

    FakeStoreProductDTO createProduct(ProductRequestDTO productRequestDTO);

    List<Product> getAllProducts();

    void delete(long id);

    FakeStoreProductDTO update(long id, ProductRequestDTO productRequestDTO);

    boolean putRequestDTO(long id, PutRequestDTO putRequestDTO);

    List<Product> productsBasedOnCategory(String name);

    List<Product> allProductBasedOnCategory(String name);

    Product findProductWithNameAndId(String name, Long id);

    List<Product> findTitleOfProductByCategory(String category);

}
