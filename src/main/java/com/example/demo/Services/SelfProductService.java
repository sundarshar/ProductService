package com.example.demo.Services;

import com.example.demo.DTOs.GetProductDTO.FakeStoreProductDTO;
import com.example.demo.DTOs.GetProductDTO.ProductRequestDTO;
import com.example.demo.DTOs.GetProductDTO.PutRequestDTO;
import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Repository.CategoryRepository;
import com.example.demo.Repository.ProductRepository;
import com.example.demo.Repository.Projection.ProductProjection;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfProductServices")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProduct(long id) {
        return productRepository.findById(id);
    }

    @Override
    public FakeStoreProductDTO createProduct(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setImageURL(productRequestDTO.getImageURL());
        product.setDescription(productRequestDTO.getDescription());

        Category category = categoryRepository.findByTitle(productRequestDTO.getCategory());

        if(category == null){
            Category category1 = new Category();
            category1.setTitle(productRequestDTO.getCategory());
            categoryRepository.save(category1);
            category = category1;
        }

        product.setCategory(category);
        productRepository.save(product);
        return new FakeStoreProductDTO();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void delete(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public FakeStoreProductDTO update(long id, ProductRequestDTO productRequestDTO) {
        return null;
    }

    @Override
    public boolean putRequestDTO(long id, PutRequestDTO putRequestDTO) {
        return false;
    }

    @Override
    public List<Product> productsBasedOnCategory(String title) {
        return productRepository.findAllByCategory_TitleLike(title);
    }

    @Override
    public List<Product> allProductBasedOnCategory(String title) {
        return productRepository.findAllByCategoryTitle(title);
    }

    @Override
    public Product findProductWithNameAndId(String name, Long id) {
        return productRepository.findProductWithName(name, id);
    }

    @Override
    public List<Product> findTitleOfProductByCategory(String category) {
        return productRepository.getTitlesOfProductOfGivenCategory(category);
    }
}
