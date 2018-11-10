package my.study.repository;

import my.study.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
