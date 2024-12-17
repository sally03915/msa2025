package com.company.orderservice.controller;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.orderservice.dto.OrderDto;
import com.company.orderservice.jpa.OrderEntity;
import com.company.orderservice.service.OrderService;
import com.company.orderservice.vo.RequestOrder;
import com.company.orderservice.vo.ResponseOrder;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/order-service")
@Slf4j
public class OrderController {
    Environment env;
    OrderService orderService; 

    @Autowired public OrderController(Environment env, OrderService orderService ) {
        this.env = env;
        this.orderService = orderService; 
    }
    @GetMapping("/health_check") public String status() {
        return String.format("Order Service on PORT %s", env.getProperty("local.server.port"));
    }
    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId") String userId,
                                                     @RequestBody RequestOrder orderDetails) {
        log.info("..... " + userId);
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(orderDetails, OrderDto.class);
        orderDto.setUserId(userId);
        /* jpa */
        OrderDto createdOrder = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

        log.info("............. userId orders CRATE DONE AFTER");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId) throws Exception {
        log.info("..... " + userId);
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseOrder.class));
        });

        log.info("............. userId orders  SEARCH  AFTER");

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
