package my.study.dto.document;

import lombok.Data;
import my.study.entity.HashTag;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Data
public class HashTagDTO {
    private Long id;

    private String name;

    public HashTagDTO(HashTag hashTag) {
        this.id = hashTag.getId();
        this.name = hashTag.getName();
    }

    public HashTagDTO(String name) {
        this.name = name;
    }

    public HashTagDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
