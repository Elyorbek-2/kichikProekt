package uz.pdp.kichikproekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.kichikproekt.entity.Category;
import uz.pdp.kichikproekt.entity.InputProduct;
import uz.pdp.kichikproekt.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    boolean existsByNameAndCategory_Id(String name, Integer category_id);
//    @Query("select * from input where timestamp=now()::DATE")
//    setdate getdate
//    List<Product> getProductByName();
//    orik 5 7000
//    olma 6 2000
}
