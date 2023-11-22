package project.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.entities.Member;

import java.time.LocalDateTime;

@SpringBootTest
public class JSONTest {

    private ObjectMapper om;

    @BeforeEach
    void init(){
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
    }

    @Test
    void test1() throws JsonProcessingException {
        Member member = Member.builder()
                .email("user01@test.org")
                .password("12345678")
                .userNm("사용자01")
                .build();
        member.setCreatedAt(LocalDateTime.now());

        String json = om.writeValueAsString(member);
        System.out.println(json);
    }
}
