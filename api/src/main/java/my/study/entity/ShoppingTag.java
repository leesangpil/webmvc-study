package my.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.study.dto.document.ShoppingTagDTO;

import javax.persistence.*;
import java.util.List;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ShoppingTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String uniqueId;

    @OneToMany(mappedBy = "shoppingTag", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttachmentShoppingTagMapping> attachmentShoppingTagMappings;

    public ShoppingTag(ShoppingTagDTO shoppingTagDTO) {
        this.type = shoppingTagDTO.getType();
        this.uniqueId = shoppingTagDTO.getUniqueId();
    }
}
