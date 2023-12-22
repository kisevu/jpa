package com.ameda.kevin.data.repository;
import com.ameda.kevin.data.DTO.IProductNameAndReviewsDTO;
import com.ameda.kevin.data.DTO.ProductAddressOrdersDTO;
import com.ameda.kevin.data.DTO.ProductName;
import com.ameda.kevin.data.DTO.ProductNameAndOrders;
import com.ameda.kevin.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByProductNameStartingWith(String productName);
//    findByNameStartingWith(String prefix)
//    findByNameEndingWith(String suffix);
//    findByNameContaining(String words)
//    findByNameLike(String likePattern)
//    findByAgeLessThan(int age)
//    findByAgeGreaterThanEqual(int age)
//    findByAgeIn(Collection<Integer>ages)
//    findByNameOrderByName(String name)
    //without nativeQuery flag set to true, it is treated as a jpql query
    @Query("select p FROM Product p")
    List<Product> getAllProducts();

    @Query("select p From Product p WHERE p.productName =:product_name and p.address=:address")
    List<Product> getProdByProductName(@Param("product_name") String productName,
                                       @Param("address") String address);

    @Query(value = "select * FROM product", nativeQuery = true)
    List<Product> getAll();
    @Query(value = "SELECT * FROM product WHERE address = ?1", nativeQuery = true)
    List<Product> getProductsByAddress(String address);
    //Parameter binding in @Query()
    //We have two types:
    //a) Positional parameters which are indexed starting from 1>>
    //@Query("SELECT p FROM Product p WHERE p.address= ?1 ")
    //List<Product> getProductByAddress(String address);

    //b) Named parameters are more readable and recommended for queries with multiple parameters and use colon  by parameter name
    //@Query("SELECT p FROM Product p  WHERE p.address = :address AND p.category = :category")
    //List<Product> findByCategoryAndAddress(@Param("address") String address, @Param("category") String category);

    //@Query() modifies data or allow usage of insert, update and delete given @Modifying annotations is added on it
    //make sure to use @Transactional on the service layer for it to take effect...

    @Modifying
    @Query("UPDATE Product p SET p.address= :address WHERE p.productId = :productId")
    void updateProduct(@Param("productId")  String productId, @Param("address") String address);
    @Query("select p FROM Product p JOIN FETCH p.orders WHERE p.productId= ?1")
    Optional<Product> findByIdProduct(Long productId);

//    @Query("SELECT u FROM User u JOIN FETCH u.profile JOIN FETCH u.orders WHERE u.id = ?1")
//    Optional<User> findByIdWithProfileAndOrders(Long id);

    //@a: interfaces as projections...
    //Below implemented interface projection is working perfectly fine...
    ProductAddressOrdersDTO findByProductId(Long productId);

    //@b: Class-based projections...
    //Below is an implementation of that...
    //suitable for complex transformations, DTO projections.
    //These are classes with a set of fields , a constructor that matches jpql  query and getters
    @Query("SELECT new com.ameda.kevin.data.DTO.ProductNameAndOrders(p.productName,p.orders) FROM Product p WHERE p.productId = ?1")
    ProductNameAndOrders findWithProductId(Long productId);

//    @Query("select new com.ameda.kevin.data.DTO.ProductName(p.productName) from product p where p.productId = ?1")
//    ProductName getProductName(Long productId);

    //Above query is working with class projections.


    //we have dynamic projections, which is a very powerful concept for that matter.
    //Here spring Data JPA projections, one can let the client of the repository decide
    // which projection type should be used during runtime.
    //<> List<T> findByName(String name, Class<T> type); example... will expand on this later

    //Leveraging Native Queries... (are plain sql queries bypassing jpql queries)
    //They are within @Query and have nativeQuery flag, which should be set to true always...

//    @Query(value = "select * from product where reviews= ?1",nativeQuery = true)
//    Product findByReview(int review);
    //above is positional parameters which are indexed starting from 1 >>

    //using named parameters...
    @Query(value = "select * from product where reviews= :review",nativeQuery = true)
    Product findByReview(@Param("review") int review);
    //Return projections with NativeQueries...
    @Query(value = "select product_name,reviews from product where product_name = :productName",nativeQuery = true)
    IProductNameAndReviewsDTO getProductNameAndReviews(@Param("productName") String productName);
   Page<Product> findByProductNameContaining(String productName, Pageable pageable);

}

