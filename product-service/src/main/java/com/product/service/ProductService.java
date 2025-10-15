package com.product.service;


import com.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDTO findProductById(Long productId);

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();
}
