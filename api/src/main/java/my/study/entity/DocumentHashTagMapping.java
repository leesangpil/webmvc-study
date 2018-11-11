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
public class DocumentHashTagMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_id")
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hashTag_id")
    private HashTag hashTag;

    // Document <--> HashTag 사이의 관계를 추가한다
    public DocumentHashTagMapping(Document document, HashTag hashTag) {
        this.document = document;
        this.hashTag = hashTag;
        add();
    }

    public void add() {
        this.document.getDocumentHashTagMappings().add(this);
    }
}
