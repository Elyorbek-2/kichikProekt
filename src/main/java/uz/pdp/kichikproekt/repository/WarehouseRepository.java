package uz.pdp.kichikproekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.kichikproekt.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
}
