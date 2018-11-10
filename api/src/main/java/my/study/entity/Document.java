package my.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.study.dto.document.AttachmentDTO;
import my.study.dto.document.DocumentDTO;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by leesangpil on 2018. 11. 7..
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Member author;

    @Column(nullable = true)
    private String body;

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DocumentHashTagMapping> documentHashTagMappings;

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("seq asc")
    private List<Attachment> attachments;

    public Document(Member author, DocumentDTO documentDTO, List<HashTag> hashTags, Map<Integer, List<ShoppingTag>> shoppingTagMap) {
        this.author = author;
        this.body = documentDTO.getBody();
        // attachment 생성
        this.attachments = documentDTO.getAttachmentDTOS().stream()
                .map(attachmentDTO -> new Attachment(this, attachmentDTO, shoppingTagMap.get(attachmentDTO.getSeq())))
                .collect(toList());
        // hash-tag 관계 생성
        hashTagMappingForCreate(hashTags);
    }

    public void update(DocumentDTO documentDTO, List<HashTag> hashTags, Map<Integer, List<ShoppingTag>> shoppingTagMap) {
        this.body = documentDTO.getBody();
        // 기존에 없던 attachment 들을 뽑아냄
        List<AttachmentDTO> newAttachments = documentDTO.getAttachmentDTOS().stream().filter(attachmentDTO -> attachmentDTO.getId() == null).collect(toList());

        // 기존에 있던 애들은 Update
        this.attachments.stream().forEach(attachment -> documentDTO.getAttachmentDTOS().forEach(attachmentDTO -> {
            if (attachment.getId().equals(attachmentDTO.getId())) {
                attachment.update(attachmentDTO, shoppingTagMap.get(attachmentDTO.getSeq()));
            }
        }));
        // 기존에 없던 애들은 Create
        this.attachments = newAttachments.stream()
                .map(attachmentDTO -> new Attachment(this, attachmentDTO, shoppingTagMap.get(attachmentDTO.getSeq())))
                .collect(toList());
        // document <-> hash-tag 관계 업데이트
        hashTagMappingForUpdate(hashTags);
    }

    // document <-> hash-tag 의 관계를 업데이트 한다
    private void hashTagMappingForUpdate(List<HashTag> hashTags) {
        List<DocumentHashTagMapping> mappings = new ArrayList<>();
        hashTags.stream().forEach(hashTag -> {
            hashTag.getDocumentHashTagMappings().stream().forEach(documentHashTagMapping -> {});
            if (CollectionUtils.isEmpty(hashTag.getDocumentHashTagMappings())) {
                // 관계가없는 해시태그라면 매핑관계에 넣어줌
                mappings.add(new DocumentHashTagMapping(this, hashTag));
            } else {
                // 기존에 관계가 있다면 이 document와의 관계가 있는지 확인
                hashTag.getDocumentHashTagMappings().stream().forEach(documentHashTagMapping -> {
                    if (this.getId().equals(documentHashTagMapping.getDocument().getId())) {
                        // 이 문서와 관계가 있었다면 다시 넣어줌
                        mappings.add(documentHashTagMapping);
                    }
                });
            }
            // TODO : 해시태그쪽에서 관계를 끈어줌
            //hashTag.setDocumentHashTagMappings(Collections.emptyList());
        });
        //this.documentHashTagMappings = Collections.emptyList();
        // 새로운 관계 형성
        this.documentHashTagMappings = mappings;
    }

    // document <-> hash-tag 의 관계를 새로 형성한다
    private void hashTagMappingForCreate(List<HashTag> hashTags) {
        List<DocumentHashTagMapping> mappings = new ArrayList<>();
        hashTags.forEach(hashTag -> mappings.add(new DocumentHashTagMapping(this, hashTag)));
        // 새로운 관계 형성
        this.documentHashTagMappings = mappings;
    }
}
