package com.sarab.SpringEcom.service;


import com.sarab.SpringEcom.model.Product;
import com.sarab.SpringEcom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ProdductService {

    @Autowired
    private ProductRepo productRepo;


     public List<Product> getAllProducts(){
         return productRepo.findAll();

     }


    public Product getProductById(int id) {
         return  productRepo.findById(id).orElse(null);
    }

    public Product addorUpdateProduct(Product product, MultipartFile imageFile) throws IOException {
         product.setImageName(imageFile.getOriginalFilename());
         product.setImageType(imageFile.getContentType());
         product.setImageData(imageFile.getBytes());
         return productRepo.save(product);

    }


    public void deleteProduct(int id) {
         productRepo.deleteById(id);
    }
}
