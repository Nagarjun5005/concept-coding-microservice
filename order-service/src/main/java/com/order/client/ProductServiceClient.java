package com.order.client;


import com.order.dto.ProductDTO;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClient {

    @Autowired
    private ProductClient productClient;

    @RateLimiter(name = "productService",fallbackMethod = "productServiceFallback")
    public ProductDTO getProductById(Long id){
        return productClient.getProductById(id);
    }


    public ProductDTO productServiceFallback(Long id, Throwable throwable){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(id);
        productDTO.setName("fall back product");
        productDTO.setPrice(0.0);
        System.out.println("rate limit exceeded "+throwable.getMessage());
        return productDTO;
    }
}
