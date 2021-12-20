package uz.pdp.kichikproekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.kichikproekt.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {
}
