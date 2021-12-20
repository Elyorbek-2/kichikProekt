package uz.pdp.kichikproekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.kichikproekt.entity.Category;
import uz.pdp.kichikproekt.payload.CategoryDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result addCategory(@RequestBody CategoryDto categoryDto){
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR','OPERATOR')")
    public List<Category> getCategory(){
        return categoryService.getCategoryList();
    }

    @PutMapping
    @PreAuthorize(value = "hasAnyRole('SUPER_ADMIN','MODERATOR')")
    public Result editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        return categoryService.editCategory(id,categoryDto);
    }

    @DeleteMapping
    @PreAuthorize(value = "hasRole('SUPER_ADMIN')")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }
}
