package my.study.dto.document;

import lombok.Data;
import my.study.entity.Attachment;
import my.study.entity.AttachmentShoppingTagMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Data
public class AttachmentDTO {
    private Long id;

    private Integer seq;

    private String type;

    private List<ShoppingTagDTO> shoppingTagDTOS;

    public AttachmentDTO(Attachment attachment) {
        this.seq = attachment.getSeq();
        this.id = attachment.getId();
        this.type = attachment.getType();
        this.shoppingTagDTOS = attachment.getAttachmentShoppingTagMappings().stream().map(AttachmentShoppingTagMapping::getShoppingTag).map(ShoppingTagDTO::new).collect(toList());
    }

    // for create
    public AttachmentDTO(String type, List<ShoppingTagDTO> shoppingTagDTOS) {
        this.type = type;
        this.shoppingTagDTOS = shoppingTagDTOS;
    }

    // for update
    public AttachmentDTO(Long id, String type, List<ShoppingTagDTO> shoppingTagDTOS) {
        this.id = id;
        this.type = type;
        this.shoppingTagDTOS = shoppingTagDTOS;
    }
}
