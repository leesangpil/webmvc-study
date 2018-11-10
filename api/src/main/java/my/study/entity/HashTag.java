package my.study.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.study.dto.document.HashTagDTO;

import javax.persistence.*;
import java.util.List;

/**
 * Created by leesangpil on 2018. 11. 8..
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "hashTag", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DocumentHashTagMapping> documentHashTagMappings;

    public HashTag(HashTagDTO hashTagDTO) {
        this.name = hashTagDTO.getName();
    }
}
