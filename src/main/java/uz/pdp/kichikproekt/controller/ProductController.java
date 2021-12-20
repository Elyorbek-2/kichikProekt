package uz.pdp.kichikproekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.core.support.ReactiveRepositoryFactorySupport;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.kichikproekt.entity.Product;
import uz.pdp.kichikproekt.payload.ProductDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.service.ProductService;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }

    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    public List<Product> getProduct(){
        return productService.getProduct();
    }

    @PutMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){
        return productService.editProduct(id,productDto);
    }

    @DeleteMapping
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public Result deleteProduct(@PathVariable Integer id){
        return productService.deleteProduct(id);
    }
}
