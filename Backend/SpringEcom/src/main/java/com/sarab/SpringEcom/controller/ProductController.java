package com.sarab.SpringEcom.controller;

import com.sarab.SpringEcom.model.Product;
import com.sarab.SpringEcom.service.ProdductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @GetMapping("product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product = productService.getProductById((productId));
        return new ResponseEntity<>(product.getImageData(),HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        // This API handles a multipart/form-data request.
// It receives:
// 1) A Product object as JSON (@RequestPart Product product)
// 2) An image file upload (@RequestPart MultipartFile imageFile)
// Used when we need to send both data + file in a single request.

        Product savedProduct = null;
        try {
            savedProduct = productService. addorUpdateProduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile ){
        Product updatedProduct = null;
        try{
            updatedProduct = productService. addorUpdateProduct(product,imageFile);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        catch (IOException e){
           throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = productService.searchProducts(keyword);
        System.out.println("Searching with"+keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }



}
