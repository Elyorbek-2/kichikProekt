package uz.pdp.kichikproekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.kichikproekt.entity.OutputProduct;

import java.sql.Timestamp;
import java.util.List;

public interface OutputProductRepository extends JpaRepository<OutputProduct,Integer> {

    List<OutputProduct> findAllByOutput_Kun(Timestamp output_kun);
}
