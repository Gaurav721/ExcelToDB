package com.sahara.excel.controller;

import com.sahara.excel.entity.Product;
import com.sahara.excel.helper.ExcelHelper;
import com.sahara.excel.service.ProductService;
import org.apache.poi.ss.usermodel.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file){

        if(ExcelHelper.checkExcelFormat(file)){

            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message","File is Uploaded and Data is Saved to DB"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel file");

    }

    @GetMapping("/product")
    public List<Product> getProduct(){
        return this.productService.getAllProducts();
    }

}
