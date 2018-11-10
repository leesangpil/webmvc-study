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
import java.util.Collections;
import java.util.List;

/**
 * Created by leesangpil on 2018. 11. 10..
 */
@Order(3)
//@Profile("xxxx")
@Component
@RequiredArgsConstructor
public class DocumentUpdateRunner implements ApplicationRunner {
    private final DocumentService documentService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("3. update document");

        DocumentDTO dto = documentService.update(makeMemberDTO());

        System.out.println("document create complete!!");
        System.out.println("id : " + dto.getId());
        System.out.println("authorId : " + dto.getAuthorId());
        System.out.println("body : " + dto.getBody());
    }

    private DocumentDTO makeMemberDTO() {
        // 수정 내용
        // 1번 문서
        // Document의 본문을 수정하고
        // 사진을 하나 추가하고
        // 첫번째 사진의 쇼핑태그를 하나 더 추가한다.
        // 두번째 비디오에 있던 쇼핑태그는 제거.
        // 태그도 하나 걍 지움

        Long documentId = 1l;              // 1번 document를 수정한다
        Long authorId = 1l;
        String body = "update document body1";

        List<HashTagDTO> hashTagDTOS = new ArrayList<>();
        hashTagDTOS.add(new HashTagDTO(1l, "tag1"));
        //hashTagDTOS.add(new HashTagDTO("tag2"));

        List<AttachmentDTO> attachmentDTOS = new ArrayList<>();

        List<ShoppingTagDTO> shoppingTagDTOS1 = new ArrayList<>();
        shoppingTagDTOS1.add(new ShoppingTagDTO(1l, "place", "place001"));
        shoppingTagDTOS1.add(new ShoppingTagDTO(2l, "item", "item001"));
        shoppingTagDTOS1.add(new ShoppingTagDTO("custem", "custom001"));

        List<ShoppingTagDTO> shoppingTagDTOS2 = new ArrayList<>();
        //shoppingTagDTOS2.add(new ShoppingTagDTO("brand", "brand001"));

        attachmentDTOS.add(new AttachmentDTO(1l, "image1", shoppingTagDTOS1));
        attachmentDTOS.add(new AttachmentDTO(2l, "video1", shoppingTagDTOS2));
        attachmentDTOS.add(new AttachmentDTO("image2", Collections.emptyList()));

        return new DocumentDTO(documentId, authorId, body, hashTagDTOS, attachmentDTOS);
    }
}
