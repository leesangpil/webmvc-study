package my.study.repository;

import my.study.entity.DocumentHashTagMapping;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
public interface DocumentHashTagMappingRepository extends JpaRepository<DocumentHashTagMapping, Long> {
}
