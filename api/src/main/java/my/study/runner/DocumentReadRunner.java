package my.study.runner;

import lombok.RequiredArgsConstructor;
import my.study.dto.document.DocumentDTO;
import my.study.entity.service.DocumentService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by leesangpil on 2018. 9. 12..
 */
@Order(2)
@Profile("xxx")
@Component
@RequiredArgsConstructor
public class DocumentReadRunner implements ApplicationRunner {
    private final DocumentService documentService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("2. read document");

        DocumentDTO dto = documentService.read(1l);

        System.out.println("document read complete!!");
        System.out.println("id : " + dto.getId());
    }
}
