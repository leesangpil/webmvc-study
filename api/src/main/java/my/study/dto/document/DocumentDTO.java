package my.study.dto.document;

import lombok.Data;
import my.study.entity.Document;
import my.study.entity.DocumentHashTagMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Data
public class DocumentDTO {
    private Long id;

    private Long authorId;

    private String body;

    private List<AttachmentDTO> attachmentDTOS;

    private List<HashTagDTO> hashTagDTOS;

    public DocumentDTO(Document document) {
        this.id = document.getId();
        this.authorId = document.getAuthor().getId();
        this.body = document.getBody();
        this.hashTagDTOS = document.getDocumentHashTagMappings().stream().map(DocumentHashTagMapping::getHashTag).map(HashTagDTO::new).collect(toList());
        this.attachmentDTOS = document.getAttachments().stream().map(AttachmentDTO::new).collect(toList());
    }

    public DocumentDTO(Long id, Long authorId, String body, List<HashTagDTO> hashTagDTOS, List<AttachmentDTO> attachmentDTOS) {
        this.id = id;
        this.authorId = authorId;
        this.body = body;
        this.hashTagDTOS = hashTagDTOS;
        this.attachmentDTOS = attachmentDTOS;
    }
}
