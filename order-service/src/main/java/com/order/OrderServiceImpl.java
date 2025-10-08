package com.order;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

     @Autowired
     private OrderRepository orderRepository;


     @Autowired
     private ModelMapper modelMapper;

     @Autowired
     private ProductClient productClient;

    @Override
    public OrderDTO createOrder(Long productId, Integer quantity) {
        //create the order
        ProductDTO productById = productClient.getProductById(productId);
        Double totalPrice = productById.getPrice() * quantity;
        Order order=new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        Order saved = orderRepository.save(order);
       return modelMapper.map(saved, OrderDTO.class);
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundExcpetion("Order Not found with orderId " + orderId));

        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return orderDTO;

    }

    @Override
    public List<OrderDTO> getAllOrders() {
        //from db get all the orders using find all
        List<Order> allOrders = orderRepository.findAll();

        //convert all each order in the allOrders list to orderDto
        List<OrderDTO> orderDTOList = allOrders.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
        return orderDTOList;
    }


}
