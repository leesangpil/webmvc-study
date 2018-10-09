package my.study.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {
    MAN("남자"),
    WOMAN("여자"),
    GAY("게이"),
    LESBIAN("레즈비언");
    private String gender;
}
