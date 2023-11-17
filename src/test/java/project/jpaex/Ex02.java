package project.jpaex;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import project.commons.constants.MemberType;
import project.entities.Member;

@SpringBootTest
public class Ex02 {
    @PersistenceContext
    private EntityManager em;

    @Test
    void test1(){

        Member member = Member.builder()
                .email("user01@test.org")
                .password("123456")
                .userNm("사용자01")
                .mobile("010")
                .mtype(MemberType.USER)
                .build();

        em.persist(member);
        em.flush();

        Member member2 = em.find(Member.class, member.getUserNo());
        System.out.println(member2);

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            throw new RuntimeException();
        }

        member2.setUserNm("김수정");
        em.flush();

        member2 = em.find(Member.class, member.getUserNo());
        System.out.println(member2);
    }

    @Test
    void test2(){

    }
}
