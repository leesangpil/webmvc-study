package my.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.study.dto.document.AttachmentDTO;
import org.springframework.util.CollectionUtils;

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

    @OneToMany(mappedBy = "attachment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AttachmentShoppingTagMapping> attachmentShoppingTagMappings;

    @ManyToOne
    @JoinColumn(name = "document_id")
    private Document document;

    public Attachment(Document document, AttachmentDTO attachmentDTO, List<ShoppingTag> shoppingTags) {
        this.seq = attachmentDTO.getSeq();
        this.type = attachmentDTO.getType();
        this.document = document;
        shoppingTagMapping(shoppingTags);
    }

    public void update(AttachmentDTO attachmentDTO, List<ShoppingTag> shoppingTags) {
        this.seq = attachmentDTO.getSeq();
        this.type = attachmentDTO.getType();

        // attachment <-> shopping-tag 관계 생성
        shoppingMappingForUpdate(shoppingTags);
    }

    // attachment <-> shopping-tag 의 관계를 업데이트 한다
    private void shoppingMappingForUpdate(List<ShoppingTag> shoppingTags) {
        List<AttachmentShoppingTagMapping> mappings = new ArrayList<>();
        shoppingTags.stream().forEach(shoppingTag -> {
            if (CollectionUtils.isEmpty(shoppingTag.getAttachmentShoppingTagMappings())) {
                // 새로운 쇼핑태그라면 매핑관계에 넣어줌
                mappings.add(new AttachmentShoppingTagMapping(this, shoppingTag));
            } else {
                // 기존에 관계가 있다면 이 attachment와의 관계가 있는지 확인
                shoppingTag.getAttachmentShoppingTagMappings().stream().forEach(attachmentShoppingTagMapping -> {
                    if (this.getId().equals(attachmentShoppingTagMapping.getAttachment().getId())) {
                        // 이 Attachment와 관계가 있었다면 다시 넣어줌
                        mappings.add(attachmentShoppingTagMapping);
                    }
                });
            }
        });
        // 새로운 관계 형성
        this.attachmentShoppingTagMappings = mappings;
    }

    // attachment <-> shopping-tag 의 관계를 새로 형성한다
    private void shoppingTagMapping(List<ShoppingTag> shoppingTags) {
        List<AttachmentShoppingTagMapping> mappings = new ArrayList<>();
        shoppingTags.forEach(shoppingTag -> mappings.add(new AttachmentShoppingTagMapping(this, shoppingTag)));
        // 새로운 관계 형성
        this.attachmentShoppingTagMappings = mappings;
    }
}
