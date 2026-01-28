package com.sarab.SpringEcom.controller;

import com.sarab.SpringEcom.model.Product;
import com.sarab.SpringEcom.service.ProdductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin //allow frontend to connect with backend request from other port no
public class ProductController {

    @Autowired
    private ProdductService productService;

    @GetMapping("/products")  // Response entity help us to make the server code like 202
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/product/{id}")
    public  ResponseEntity<Product> getProductById(@PathVariable int id){

        Product product = productService.getProductById(id);

        if(product != null)
            return new ResponseEntity<>(product,HttpStatus.OK);
        else
            return new ResponseEntity<>(product,HttpStatus.NOT_FOUND);


    }



}
