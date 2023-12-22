package com.ameda.kevin.data.service;

import com.ameda.kevin.data.DTO.IProductNameAndReviewsDTO;
import com.ameda.kevin.data.DTO.ProductAddressOrdersDTO;
import com.ameda.kevin.data.DTO.ProductName;
import com.ameda.kevin.data.DTO.ProductNameAndOrders;
import com.ameda.kevin.data.DTO.request.ProductCreateRequest;
import com.ameda.kevin.data.entity.Order;
import com.ameda.kevin.data.entity.Product;
import com.ameda.kevin.data.repository.OrderRepository;
import com.ameda.kevin.data.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService {
    private Integer count = 0;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    public Product uploadProduct(ProductCreateRequest request) {
        Product product = Product.builder()
                .productName(request.getProductName())
                .address(request.getAddress())
                .Category(request.getCategory())
                .reviews(count++)
                .orders(List.of())
                .build();
        return productRepository.save(product);
    }

    public Product getProduct(String productName) {
        return productRepository.findByProductNameStartingWith(productName);
    }

    public List<Product> products(String productName,String address){
        List<Product> productLists  = productRepository.getProdByProductName(productName,address);
       productLists.forEach(System.out::println);
        return productLists;
    }
    public List<Product>  getAllProducts(){
        return productRepository.getAll();
    }
    public List<Product> getProductByAddress(String address){
        return productRepository.getProductsByAddress(address);
    }

    public String updateProduct(String productId, String address){
        productRepository.updateProduct(productId,address);
        return "successful";
    }
    public Order placeOrder(Long productId){
        Product product = productRepository.findById(productId).orElseThrow();
        if(product!=null){
            Order order = Order.builder()
                    .placingTime(LocalDateTime.now())
                    .products(List.of(product))
                    .build();
            return  orderRepository.save(order);
        }else{
            return null;
        }
    }
    public Product joinedProduct(Long productId) {
        return productRepository.findByIdProduct(productId).orElseThrow();
    }
    public Order getOrders(Long oId) {
        return orderRepository.findOrders(oId).orElseThrow();
    }
    public ProductAddressOrdersDTO projection(Long productId) {
        return productRepository.findByProductId(productId);
    }
    public ProductNameAndOrders projectionClass(Long productId) {
        return productRepository.findWithProductId(productId);
    }
    public Product getByProductReview(int review){
        return productRepository.findByReview(review);
    }

    public IProductNameAndReviewsDTO getProductNameAndReviewsInterface(String productName){
        IProductNameAndReviewsDTO result = productRepository.getProductNameAndReviews(productName);
        System.out.println(result.getProductName()+" "+result.getReviews());
        return  result;
    }

    public Page<Product> productList(String productName){
        Pageable pageable = PageRequest.of(0,2, Sort.by(productName));
        Page<Product> pages = productRepository.findByProductNameContaining(productName,pageable);
        return pages;
    }
//    public ProductName getProductName(Long productId){
//        return productRepository.getProductName(productId);
//    }
}
