package my.study.dto;

import lombok.Data;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Data
public class DocumentDTO {
    private Long id;

    private Long authorId;

    private String body;
}
