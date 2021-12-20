package uz.pdp.kichikproekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.kichikproekt.entity.Users;

public interface UserRepository extends JpaRepository<Users,Integer> {
}
