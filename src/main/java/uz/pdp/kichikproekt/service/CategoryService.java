package uz.pdp.kichikproekt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.kichikproekt.entity.Category;
import uz.pdp.kichikproekt.payload.CategoryDto;
import uz.pdp.kichikproekt.payload.Result;
import uz.pdp.kichikproekt.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new Result("Bunday category mavjud", false);
        Category category = new Category();
        category.setName(categoryDto.getName());
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getCategoryId());
        if (categoryDto.getCategoryId() != null) {
            if (!optionalCategory.isPresent())
            return new Result("Bunday id li ota category mavjud emas",false);
            else {
                category.setCategory(optionalCategory.get());
            }
        }
        categoryRepository.save(category);
        return new Result("Bajarildi", true);
    }

    public List<Category> getCategoryList() {
        return categoryRepository.findAll();
    }

    public Result editCategory(int id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new Result("Bunday id li category mavjud emas", false);
        Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getCategoryId());
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new Result("Bunday nomli category mavjud",false);
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        if (categoryDto.getCategoryId()!=null){
            if (!optionalCategory1.isPresent())
                return new Result("Bunday id li ota category mavjud emas", false);
            else
                category.setCategory(optionalCategory1.get());
        }
        categoryRepository.save(category);
        return new Result("Bajarildi", true);
    }

    public Result deleteCategory(int id) {
        try {
            categoryRepository.deleteById(id);
            return new Result("Bajarildi", true);
        } catch (Exception e) {
            return new Result("Bajarilmadi", false);
        }
    }
}
