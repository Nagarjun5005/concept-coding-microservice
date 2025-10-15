package com.product.service;


import com.product.dto.ProductDTO;
import com.product.repository.ProductRepository;
import com.product.exception.ResourceNotFoundExcpetion;
import com.product.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO findProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()
                        -> new ResourceNotFoundExcpetion("product not found with product id " + productId));
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());

        Product savedProduct = productRepository.save(product);

        ProductDTO savedDtoProduct = new ProductDTO();
        savedDtoProduct.setId(savedProduct.getId());
        savedDtoProduct.setName(savedProduct.getName());
        savedDtoProduct.setPrice(savedProduct.getPrice());

        return savedDtoProduct;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> allProducts= productRepository.findAll();
        List<ProductDTO> productDTOList = allProducts
                .stream().map(product ->
                        modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return productDTOList;
    }
}
