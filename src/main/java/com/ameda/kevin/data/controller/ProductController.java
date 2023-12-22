package com.ameda.kevin.data.controller;

import com.ameda.kevin.data.DTO.IFavoriteDTO;
import com.ameda.kevin.data.DTO.IProductNameAndReviewsDTO;
import com.ameda.kevin.data.DTO.request.ProductCreateRequest;
import com.ameda.kevin.data.entity.Product;
import com.ameda.kevin.data.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    @PostMapping("/upload")
    public ResponseEntity<?>  uploadProduct(@RequestBody ProductCreateRequest request) {
        Product product = productService.uploadProduct(request);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/product")
    public ResponseEntity<?> getProduct(@RequestParam("productName") String productName){
        Product obj = productService.getProduct(productName);
        if(obj!=null){
            return new ResponseEntity<>(obj,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/")
    public ResponseEntity<?> getProducts(@RequestParam("productName") String productName,
                                         @RequestParam("address") String address){
        return new ResponseEntity<>(productService.products(productName,address),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> geAll(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @GetMapping("/address")
    public ResponseEntity<?> getProdsByAddress(@RequestParam("address") String address){
        return new ResponseEntity<>(productService.getProductByAddress(address),HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@RequestParam("productId") String productId,
                                           @RequestParam("address") String address){

        if(productService.updateProduct(productId,address).equalsIgnoreCase("successful")){
            return new ResponseEntity<>("successful",HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    @GetMapping("/join")
    public ResponseEntity<?> getProduct(@RequestParam("productId") Long productId){
        return new ResponseEntity<>(productService.joinedProduct(productId),HttpStatus.OK);
    }

    //interface projection is working perfectly fine...
    @GetMapping("/projection/interface")
    public ResponseEntity<?> projectionInterface(@RequestParam("productId") Long productId){
        return new ResponseEntity<>(
                productService.projection(productId)
                ,HttpStatus.OK);
    }

    //class-based projection

    @GetMapping("/projection/class")
    public ResponseEntity<?> projectionClass(@RequestParam("productId") Long productId){
        return new ResponseEntity<>(
                productService.projectionClass(productId)
                ,HttpStatus.OK);
    }

    @GetMapping("/review")
    public ResponseEntity<?> getByReview(@RequestParam("review") int review){
        return new ResponseEntity<>(productService.getByProductReview(review),HttpStatus.OK);
    }
    @GetMapping("/native/projection")
    public ResponseEntity<?> nativeQueryProjection(@RequestParam("productName") String productName){
        IProductNameAndReviewsDTO obj = productService.getProductNameAndReviewsInterface(productName);
        if(obj!=null){
            return new ResponseEntity<>(obj,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/entire")
    public ResponseEntity<?> productsAll(@RequestParam("productName") String productName){
        return new ResponseEntity<>(productService.productList(productName),HttpStatus.OK);
    }
//    @GetMapping("/productName")
//    public ResponseEntity<?> getProductName(@RequestParam("productId") Long productId){
//        return new ResponseEntity<>(productService.getProductName(productId),HttpStatus.OK);
//    }
    //comment due to error on class-based projection implementation...
}
