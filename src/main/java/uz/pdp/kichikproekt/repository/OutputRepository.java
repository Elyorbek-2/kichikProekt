package uz.pdp.kichikproekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.kichikproekt.entity.Output;
import uz.pdp.kichikproekt.entity.Warehouse;

import java.util.Optional;

public interface OutputRepository extends JpaRepository<Output,Integer> {
}
