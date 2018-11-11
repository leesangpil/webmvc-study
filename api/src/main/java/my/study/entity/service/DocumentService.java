package my.study.entity.service;

import lombok.RequiredArgsConstructor;
import my.study.dto.document.AttachmentDTO;
import my.study.dto.document.DocumentDTO;
import my.study.dto.document.HashTagDTO;
import my.study.dto.document.ShoppingTagDTO;
import my.study.entity.*;
import my.study.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

/**
 * Created by leesangpil on 2018. 11. 8..
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentService {
    private final MemberRepository memberRepository;
    private final DocumentRepository documentRepository;
    private final HashTagRepository hashTagRepository;
    private final ShoppingTagRepository shoppingTagRepository;

    private final AttachmentRepository attachmentRepository;

    // write document
    @Transactional(readOnly = false)
    public DocumentDTO write(DocumentDTO documentDTO) {
        // 0. find author
        Member author = memberRepository.findById(documentDTO.getAuthorId()).orElseThrow(() -> new EntityNotFoundException());

        // 1. save hash-tag
        List<HashTag> hashTags = saveHashTag(documentDTO.getHashTagDTOS());

        // 2. save shopping-tag
        Map<Integer, List<ShoppingTag>> shoppingTagMap = saveShoppingTagMap(documentDTO.getAttachmentDTOS());

        // 4. save attachment
        List<Attachment> attachments = saveAttachment(documentDTO.getAttachmentDTOS(), shoppingTagMap);

        // 3. save document
        Document document = documentRepository.save(new Document(author, documentDTO, hashTags, attachments, shoppingTagMap));

        return new DocumentDTO(document);
    }

    // HashTag를 중복 체크 한 후, 저장한다
    private List<HashTag> saveHashTag(List<HashTagDTO> hashTagDTOS) {
        List<HashTag> hashTags = new ArrayList<>();
        hashTagDTOS.forEach(hashTagDTO -> {
            HashTag hashTag = hashTagRepository.findByName(hashTagDTO.getName()).orElseGet(() -> hashTagRepository.save(new HashTag(hashTagDTO)));
            hashTags.add(hashTag);
        });
        return hashTags;
    }

    // Attachment에 속한 ShoppingTag를 저장한 후 그룹핑해서 가져온다
    private Map<Integer, List<ShoppingTag>> saveShoppingTagMap(List<AttachmentDTO> attachmentDTOS) {
        Map<Integer, List<ShoppingTag>> listMap = new HashMap<>();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        attachmentDTOS.stream().forEach(attachmentDTO -> {
            // 여기서 Attachment 순서 지정
            attachmentDTO.setSeq(atomicInteger.getAndIncrement());
            listMap.put(attachmentDTO.getSeq(), saveShoppingTag(attachmentDTO.getShoppingTagDTOS()));
        });
        return listMap;
    }

    // ShoppingTag를 중복 체크 한 후, 저장한다
    private List<ShoppingTag> saveShoppingTag(List<ShoppingTagDTO> shoppingTagDTOS) {
        List<ShoppingTag> shoppingTags = new ArrayList<>();
        shoppingTagDTOS.forEach(shoppingTagDTO -> {
            ShoppingTag shoppingTag = shoppingTagRepository.findByTypeAndUniqueId(shoppingTagDTO.getType(), shoppingTagDTO.getUniqueId())
                    .orElseGet(() -> shoppingTagRepository.save(new ShoppingTag(shoppingTagDTO)));
            shoppingTags.add(shoppingTag);
        });
        return shoppingTags;
    }

    // Attachment를 저장한다
    private List<Attachment> saveAttachment(List<AttachmentDTO> attachmentDTOS, Map<Integer, List<ShoppingTag>> shoppingTagMap) {
        return attachmentDTOS.stream().map(attachmentDTO -> {
            if (attachmentDTO.getId() == null) {
                return attachmentRepository.save(new Attachment(attachmentDTO, shoppingTagMap.get(attachmentDTO.getSeq())));
            } else {
                return attachmentRepository.findById(attachmentDTO.getId()).orElseGet(() -> attachmentRepository.save(new Attachment(attachmentDTO, shoppingTagMap.get(attachmentDTO.getSeq()))));
            }
        }).collect(toList());
    }

    // read document
    @Transactional(readOnly = true)
    public DocumentDTO read(Long documentId) {
        return new DocumentDTO(documentRepository.findById(documentId).orElseThrow(() -> new EntityNotFoundException()));
    }

    // update document
    @Transactional(readOnly = false)
    public DocumentDTO update(DocumentDTO documentDTO) {
        // TODO : validate author
        System.out.println("================");
        System.out.println("1. read document");
        // 1. read document
        Document document = documentRepository.findById(documentDTO.getId()).orElseThrow(() -> new EntityNotFoundException());
        document.getAttachments();

        System.out.println("================");
        System.out.println("2. save hash-tag");
        // 2. save hash-tag
        List<HashTag> newHashTags = saveHashTag(documentDTO.getHashTagDTOS());

        System.out.println("================");
        System.out.println("3. save shopping-tag");
        // 3. save shopping-tag
        Map<Integer, List<ShoppingTag>> shoppingTagMap = saveShoppingTagMap(documentDTO.getAttachmentDTOS());

        System.out.println("================");
        System.out.println("4. save attachment");
        // 4. save attachment
        List<Attachment> attachments = saveAttachment(documentDTO.getAttachmentDTOS(), shoppingTagMap);

        System.out.println("================");
        System.out.println("5. update document");
        // 5. update document
        document.update(documentDTO, newHashTags, attachments, shoppingTagMap);

        return new DocumentDTO(document);
    }

}
