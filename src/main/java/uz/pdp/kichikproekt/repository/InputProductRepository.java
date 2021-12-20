package uz.pdp.kichikproekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.kichikproekt.entity.InputProduct;
import uz.pdp.kichikproekt.entity.Product;
import uz.pdp.kichikproekt.service.InputProductService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
    List<InputProduct> findAllByInputId_KunOrderByPrice(Timestamp inputId_kun);
    List<InputProduct> findAllExpireDate();
}
