package my.study.runner;

import lombok.RequiredArgsConstructor;
import my.study.dto.document.AttachmentDTO;
import my.study.dto.document.DocumentDTO;
import my.study.dto.document.HashTagDTO;
import my.study.dto.document.ShoppingTagDTO;
import my.study.entity.service.DocumentService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Order(1)
@Component
@RequiredArgsConstructor
public class DocumentInsertRunner implements ApplicationRunner {
    private final DocumentService documentService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("1. create document");

        DocumentDTO dto = documentService.write(makeDocumentDTO());

        System.out.println("document create complete!!");
        System.out.println("id : " + dto.getId());
        System.out.println("authorId : " + dto.getAuthorId());
        System.out.println("body : " + dto.getBody());
    }

    private DocumentDTO makeDocumentDTO() {
        Long documentId = null;
        Long authorId = 1l;
        String body = "document body";

        List<HashTagDTO> hashTagDTOS = new ArrayList<>();
        hashTagDTOS.add(new HashTagDTO("tag1"));
        hashTagDTOS.add(new HashTagDTO("tag2"));

        List<AttachmentDTO> attachmentDTOS = new ArrayList<>();

        List<ShoppingTagDTO> shoppingTagDTOS1 = new ArrayList<>();
        shoppingTagDTOS1.add(new ShoppingTagDTO("place", "place001"));
        shoppingTagDTOS1.add(new ShoppingTagDTO("item", "item001"));

        List<ShoppingTagDTO> shoppingTagDTOS2 = new ArrayList<>();
        shoppingTagDTOS2.add(new ShoppingTagDTO("brand", "brand001"));

        attachmentDTOS.add(new AttachmentDTO("image1", shoppingTagDTOS1));
        attachmentDTOS.add(new AttachmentDTO("video1", shoppingTagDTOS2));

        return new DocumentDTO(documentId, authorId, body, hashTagDTOS, attachmentDTOS);
    }
}
