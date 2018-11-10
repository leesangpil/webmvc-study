package my.study.repository;

import my.study.entity.ShoppingTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
public interface ShoppingTagRepository extends JpaRepository<ShoppingTag, Long> {
    Optional<ShoppingTag> findByTypeAndUniqueId(String type, String uniqueId);
}
