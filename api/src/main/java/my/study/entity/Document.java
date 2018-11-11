package my.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.study.dto.document.DocumentDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentHashTagMapping> documentHashTagMappings = new ArrayList<>();

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("seq asc")
    private List<Attachment> attachments = new ArrayList<>();

    public Document(Member author, DocumentDTO documentDTO, List<HashTag> hashTags, List<Attachment> attachments) {
        this.author = author;
        this.body = documentDTO.getBody();
        // hash-tag 관계 생성
        addHashTags(hashTags);
        // attachment 관계 생성
        addAttachment(attachments);
    }

    public void update(DocumentDTO documentDTO, List<HashTag> hashTags, List<Attachment> attachments) {
        // update document meta info
        this.body = documentDTO.getBody();

        // document <-> hash-tag 관계 업데이트
        addHashTags(hashTags);
        // document <-> attachment 관계 생성
        addAttachment(attachments);
    }

    // document <-> attachment 의 관계를 새로 형성한다
    private void addAttachment(List<Attachment> attachments) {
        // 기존 관계 제거 (전체 관계 제거)
        this.attachments.clear();
        // 새 Attachment와 기존 문서 관계 형성
        attachments.forEach(attachment -> attachment.setDocument(this));
        this.attachments.addAll(attachments);
    }

    // document <-> hash-tag 의 관계를 새로 형성한다
    private void addHashTags(List<HashTag> hashTags) {
        // 기존 관계 제거 (전체 관계 제거)
        this.getDocumentHashTagMappings().clear();
        // 새 태그와 기존 문서 관계 형성
        hashTags.forEach(hashTag -> new DocumentHashTagMapping(this, hashTag));
    }
}
