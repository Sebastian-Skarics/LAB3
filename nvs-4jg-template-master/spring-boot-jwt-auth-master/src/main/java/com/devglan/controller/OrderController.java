package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.OrderDto;
import com.devglan.model.User;
import com.devglan.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{canceled}/{finished}")
    public ApiResponse<User> getOne(@PathVariable Boolean canceled, @PathVariable Boolean finished){
        return new ApiResponse<>(HttpStatus.OK.value(), "Orders fetched successfully.",orderService.getOrders(canceled,finished));
    }

    @PostMapping
    public ApiResponse<OrderDto> saveOrder(@RequestBody OrderDto orderDto){
        return new ApiResponse<>(HttpStatus.OK.value(), "Order saved successfully.", orderService.save(orderDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<OrderDto> getOne(@PathVariable long id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Order fetched successfully.", orderService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<OrderDto> update(@RequestBody OrderDto orderDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Order updated successfully.", orderService.update(orderDto));
    }

    @PutMapping("/finished/{id}")
    public ApiResponse<OrderDto> setFinished(@RequestBody long id) {
        orderService.setFinished(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Order deactivated successfully.", null);
    }

    @PutMapping("/cancel/{id}")
    public ApiResponse<OrderDto> setCanceled(@RequestBody long id) {
        orderService.setCanceled(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Order deactivated successfully.", null);
    }

}
