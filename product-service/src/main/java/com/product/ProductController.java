package com.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDTO>createProduct(@RequestBody ProductDTO productDTO){
        System.out.println("Incoming DTO: " + productDTO.getName() + ", " + productDTO.getPrice());
        ProductDTO productAdded=productService.createProduct(productDTO);
        return new ResponseEntity<>(productAdded,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO>findProductById(@PathVariable Long productId){
      ProductDTO productDTO=productService.findProductById(productId);
      if(productDTO==null){
          return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(productDTO,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<ProductDTO>>getAllProducts(){
       List<ProductDTO>productDTOList= productService.getAllProducts();
       return new ResponseEntity<>(productDTOList,HttpStatus.OK);
    }



}
