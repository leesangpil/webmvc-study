package my.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.study.dto.document.AttachmentDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer seq;

    private String type;

    @OneToMany(mappedBy = "attachment", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttachmentShoppingTagMapping> attachmentShoppingTagMappings = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    public Attachment(AttachmentDTO attachmentDTO, List<ShoppingTag> shoppingTags) {
        this.seq = attachmentDTO.getSeq();
        this.type = attachmentDTO.getType();

        // attachment <-> shopping-tag 관계 생성
        addShoppingTags(shoppingTags);
    }

    // attachment <-> shopping-tag 의 관계를 새로 형성한다
    private void addShoppingTags(List<ShoppingTag> shoppingTags) {
        // 기존 관계 제거 (전체 관계 제거)
        this.getAttachmentShoppingTagMappings().clear();
        // 새 ShoppingTag와 기존 Attachment 관계 형성
        shoppingTags.forEach(shoppingTag -> new AttachmentShoppingTagMapping(this, shoppingTag));
    }
}
