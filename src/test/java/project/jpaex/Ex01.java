package project.jpaex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import project.commons.constants.MemberType;
import project.entities.BoardData;
import project.entities.Member;
import project.repositories.BoardDataRepository;
import project.repositories.MemberRepository;

@SpringBootTest
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class Ex01 {
    @Autowired
    private BoardDataRepository boardDataRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;


    @BeforeEach
    void init(){
        Member member = Member.builder()
                .email("user01@test.org")
                .password("123456")
                .userNm("사용자01")
                .mtype(MemberType.USER)
                .build();

        memberRepository.saveAndFlush(member);

        BoardData item = BoardData.builder()
                .subject("제목")
                .content("내용")
                .member(member)
                .build();

        boardDataRepository.saveAndFlush(item);
        em.clear();
    }

    @Test
    void test1(){
        Member member = Member.builder()
                .email("user01@test.org")
                .password("123456")
                .userNm("사용자01")
                .mtype(MemberType.USER)
                .build();

        memberRepository.saveAndFlush(member);

        BoardData item = BoardData.builder()
                .subject("제목")
                .content("내용")
                .member(member)
                .build();

        boardDataRepository.saveAndFlush(item);
        BoardData data = boardDataRepository.findById(1L).orElse(null);

        Member member2 = data.getMember();
        String email = member2.getEmail(); // 2차 쿼리 실행
        System.out.println(email);

    }
}
