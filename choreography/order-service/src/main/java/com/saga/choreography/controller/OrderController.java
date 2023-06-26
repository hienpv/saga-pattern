package com.saga.choreography.controller;

import com.saga.choreography.dto.OrderDto;
import com.saga.choreography.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping()
    public ResponseEntity<OrderDto> insertCustomer(@RequestBody OrderDto request) {
        OrderDto orderDto = orderService.save(request);
        return ResponseEntity.ok(orderDto);
    }
}
