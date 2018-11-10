package my.study.dto.document;

import lombok.Data;
import my.study.entity.ShoppingTag;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Data
public class ShoppingTagDTO {
    private Long id;

    private String type;

    private String uniqueId;

    public ShoppingTagDTO(ShoppingTag shoppingTag) {
        this.id = shoppingTag.getId();
        this.type = shoppingTag.getType();
        this.uniqueId = shoppingTag.getUniqueId();
    }

    public ShoppingTagDTO(String type, String uniqueId) {
        this.type = type;
        this.uniqueId = uniqueId;
    }

    public ShoppingTagDTO(Long id, String type, String uniqueId) {
        this.id = id;
        this.type = type;
        this.uniqueId = uniqueId;
    }
}
