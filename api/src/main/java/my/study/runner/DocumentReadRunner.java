package my.study.runner;

import lombok.RequiredArgsConstructor;
import my.study.dto.document.DocumentDTO;
import my.study.dto.document.HashTagDTO;
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

        DocumentDTO dto = documentService.write(makeMemberDTO());

        System.out.println("document create complete!!");
        System.out.println("id : " + dto.getId());
        System.out.println("authorId : " + dto.getAuthorId());
        System.out.println("body : " + dto.getBody());
    }

    private DocumentDTO makeMemberDTO() {
        Long authorId = 1l;
        String body = "document body";

        List<HashTagDTO> hashTagDTOS = new ArrayList<>();
        hashTagDTOS.add(new HashTagDTO("tag1"));
        hashTagDTOS.add(new HashTagDTO("tag2"));

        return new DocumentDTO(authorId, body, hashTagDTOS);
    }
}
