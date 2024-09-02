package com.example.demo.Repository;

import com.example.demo.DTOs.GetProductDTO.ProductRequestDTO;
import com.example.demo.Models.Category;
import com.example.demo.Models.Product;
import com.example.demo.Repository.Projection.ProductProjection;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product product);

    Product findById(long id);

    List<Product> findAll();

    List<Product> findAllByCategory_TitleLike(String category);

    List<Product> findAllByCategoryTitle(String category);

    @Query("select p from Product p where p.category.title = :title and p.id = :id")
    Product findProductWithName(@Param("title") String name,@Param("id") Long id);

    @Query("select p.name as name, p.id as id from Product p where p.category.title =:category")
    List<Product> getTitlesOfProductOfGivenCategory(@Param("category") String category);

    //@Query("select * from product p where p.id =:id, p.name =:title")
    //List<ProductProjection> getProductByIdTitle(@Param("id") Long id,@Param("title") String title);
}
