package my.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class AttachmentShoppingTagMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shoppingTag_id")
    private ShoppingTag shoppingTag;

    private Long documentId;

    private Long authorId;

    public AttachmentShoppingTagMapping(Attachment attachment, ShoppingTag shoppingTag) {
        this.attachment = attachment;
        this.shoppingTag = shoppingTag;
        add();
    }

    public void add() {
        this.attachment.getAttachmentShoppingTagMappings().add(this);
    }
}
