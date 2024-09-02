package com.example.demo.Services;

import com.example.demo.DTOs.GetProductDTO.FakeStoreProductDTO;
import com.example.demo.DTOs.GetProductDTO.ProductRequestDTO;
import com.example.demo.DTOs.GetProductDTO.PutRequestDTO;
import com.example.demo.Exception.ProductNotFoundException;
import com.example.demo.Models.Product;
import com.example.demo.Repository.Projection.ProductProjection;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductServices")
public class FakeStoreProductService implements ProductService{

    public RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProduct(long id) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProduct = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/"+id,
                FakeStoreProductDTO.class);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProduct.getBody();
        if(fakeStoreProductDTO == null){
            throw new ProductNotFoundException("Product with this id is not valid. Please try with valid ID.");
        }
        return fakeStoreProductDTO.toProduct();
    }

    @Override
    public FakeStoreProductDTO createProduct(ProductRequestDTO productRequestDTO) {

        ResponseEntity<FakeStoreProductDTO> response = restTemplate.postForEntity("https://fakestoreapi.com/products",
                productRequestDTO, FakeStoreProductDTO.class);

        return response.getBody();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDTO[].class);

        List<Product> product = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: response){
            product.add(fakeStoreProductDTO.toProduct());
        }
        return product;
    }

    @Override
    public void delete(long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    @Override
    public FakeStoreProductDTO update(long id, ProductRequestDTO productRequestDTO) {
        FakeStoreProductDTO response = restTemplate.patchForObject("https://fakestoreapi.com/products/"+id,
                productRequestDTO, FakeStoreProductDTO.class);
        return response;
    }

    @Override
    public boolean putRequestDTO(long id, PutRequestDTO putRequestDTO) {
        try{
            restTemplate.put("https://fakestoreapi.com/products/"+id,
                    putRequestDTO);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Product> productsBasedOnCategory(String name) {
        return List.of();
    }

    @Override
    public List<Product> allProductBasedOnCategory(String name) {
        return List.of();
    }

    @Override
    public Product findProductWithNameAndId(String name, Long id) {
        return null;
    }

    @Override
    public List<Product> findTitleOfProductByCategory(String category) {
        return List.of();
    }


}
