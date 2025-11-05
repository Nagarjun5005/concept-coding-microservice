package com.order.client;


import com.order.dto.ProductDTO;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceClient {

    @Autowired
    private ProductClient productClient;


    @CircuitBreaker(name = "productService", fallbackMethod = "productServiceFallback")
    @Bulkhead(name = "productService",fallbackMethod = "productServiceFallback",type = Bulkhead.Type.SEMAPHORE)
    @RateLimiter(name = "productService",fallbackMethod = "productServiceFallback")
    @Retry(name = "productService",fallbackMethod = "productServiceFallback")
    public ProductDTO getProductById(Long id){
        return productClient.getProductById(id);
    }

    public ProductDTO productServiceFallback(Long id, Throwable throwable) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(id);
        productDTO.setName("Fallback Product");
        productDTO.setPrice(0.0);
        System.out.println("⚠️ Fallback triggered due to: " + throwable.getMessage());
        return productDTO;
    }
}
