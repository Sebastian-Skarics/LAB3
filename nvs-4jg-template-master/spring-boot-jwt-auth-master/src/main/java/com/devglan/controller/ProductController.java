package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.Product;
import com.devglan.model.ProductDto;
import com.devglan.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ApiResponse<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        return new ApiResponse<>(HttpStatus.OK.value(), "Product saved successfully.", productService.saveProduct(productDto));
    }

    @GetMapping
    public ApiResponse<List<ProductDto>> listProducts(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Product list fetched successfully.", productService.findActiveProducts());
    }

    @GetMapping("/{name}")
    public ApiResponse<ProductDto> getWithNames(@PathVariable String name){
        return new ApiResponse<>(HttpStatus.OK.value(), "Product list successfully.", productService.findActiveProductsByNameLike(name));
    }

    @GetMapping("/id/{id}")
    public ApiResponse<ProductDto> getOne(@PathVariable long id){
        return new ApiResponse<>(HttpStatus.OK.value(), "Product fetched successfully.", productService.findById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDto> update(@RequestBody ProductDto productDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Product updated successfully.", productService.updateProduct(productDto));
    }

    @PutMapping("/deactivate/{id}")
    public ApiResponse<ProductDto> deactivate(@RequestBody long id) {
        productService.deactivate(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Product deactivated successfully.", null);
    }
}
