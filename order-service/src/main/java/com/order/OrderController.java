package com.order;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    //create a order
    @PostMapping
    public ResponseEntity<OrderDTO>createOrder(@RequestParam Long productId, @RequestParam Integer quantity){
       OrderDTO orderDTO= orderService.createOrder(productId,quantity);
       return new ResponseEntity<>(orderDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO>getOrderById(@PathVariable Long orderId){
       OrderDTO orderDTO= orderService.getOrderById(orderId);
       return new ResponseEntity<>(orderDTO,HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<OrderDTO>>getAllOrders(){
       List<OrderDTO>orderDTOS= orderService.getAllOrders();
       return new ResponseEntity<>(orderDTOS,HttpStatus.OK);
    }

}
