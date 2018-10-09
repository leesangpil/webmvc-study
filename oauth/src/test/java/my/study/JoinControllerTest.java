package my.study;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import lombok.extern.slf4j.Slf4j;
import my.study.account.AccountDTO;
import my.study.account.AccountService;
import my.study.account.enums.GenderEnum;
import my.study.account.enums.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class JoinControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

    public AccountDTO createAccountDTO(){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUsername("stonegyu");
        accountDTO.setPassword("password");
        accountDTO.setAge(30);
        accountDTO.setEmail("stonegyu@gmail.com");
        accountDTO.setGender(GenderEnum.GAY);
        return accountDTO;
    }
    public void requestJoinResponseFail(AccountDTO accountDTO) throws Exception {
        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(accountDTO)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void joinSuccessTest() throws Exception {
        mockMvc.perform(post("/join")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectToJson(createAccountDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void joinFailBecauseDupplicatedId() throws Exception {
        String username = "dupplicate_id";
        String password = "password";
        accountService.createAccount(username, password, RoleEnum.USER);

        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setUsername(username);
        accountDTO.setPassword(password);
        accountDTO.setAge(30);
        requestJoinResponseFail(accountDTO);
    }

    @Test
    public void joinFailBecauseNotValidUserName() throws Exception {
        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setUsername("lll");
        accountDTO.setPassword("password");
        accountDTO.setAge(30);
        requestJoinResponseFail(accountDTO);

        accountDTO.setUsername(" ");
        requestJoinResponseFail(accountDTO);

        accountDTO.setUsername("tooolonggggggggggggggggggggggggggg");
        requestJoinResponseFail(accountDTO);
    }


    @Test
    public void joinFailBecauseNotValidUserPassword() throws Exception {
        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setUsername("stonegyu");
        accountDTO.setPassword("1");
        accountDTO.setAge(30);
        requestJoinResponseFail(accountDTO);

        accountDTO.setPassword(" ");
        requestJoinResponseFail(accountDTO);

        accountDTO.setPassword("111111111111111111111111111111111");
        requestJoinResponseFail(accountDTO);
    }

    @Test
    public void joinFailBecauseNotValidAge() throws Exception {
        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setUsername("stonegyu");
        accountDTO.setPassword("1");
        accountDTO.setAge(-1);
        requestJoinResponseFail(accountDTO);

        accountDTO.setAge(201);
        requestJoinResponseFail(accountDTO);
    }

    @Test
    public void joinFailBecauseNotValidEmail() throws Exception {
        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setEmail("");
        requestJoinResponseFail(accountDTO);

        accountDTO.setEmail("invalidemail@");
        requestJoinResponseFail(accountDTO);

        accountDTO.setEmail("@invalidemail");
        requestJoinResponseFail(accountDTO);

        accountDTO.setEmail("@");
        requestJoinResponseFail(accountDTO);
    }

    @Test
    public void joinFailBecauseNotValidHomePhomeNumber() throws Exception {
        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setHomePhoneNumber("11122255");
        requestJoinResponseFail(accountDTO);

        accountDTO.setHomePhoneNumber("111-22255");
        requestJoinResponseFail(accountDTO);

        accountDTO.setHomePhoneNumber("02888-8888");
        requestJoinResponseFail(accountDTO);
    }

    @Test
    public void joinFailBecauseNotValidCellPhoneNumber() throws Exception {
        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setHomePhoneNumber("1111111111");
        requestJoinResponseFail(accountDTO);

        accountDTO.setHomePhoneNumber("111-111111");
        requestJoinResponseFail(accountDTO);
    }

    @Test
    public void joinFailBecauseNotValidEmergencyNumber() throws Exception {
        AccountDTO accountDTO = createAccountDTO();
        accountDTO.setEmergencyNumber("11122255");
        requestJoinResponseFail(accountDTO);

        accountDTO.setHomePhoneNumber("111-111111");
        requestJoinResponseFail(accountDTO);
    }

    public String objectToJson(Object object) throws JsonProcessingException {
        return new ObjectMapper().registerModule(new Jdk8Module()).writeValueAsString(object);
    }

}
