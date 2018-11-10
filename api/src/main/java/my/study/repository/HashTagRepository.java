package my.study.repository;

import my.study.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by leesangpil on 2018. 11. 8..
 */
public interface HashTagRepository extends JpaRepository<HashTag, String> {
    Optional<HashTag> findByName(String name);
}
