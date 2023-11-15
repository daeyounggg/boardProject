package project.commons;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.entities.Member;

@Component
@RequiredArgsConstructor
public class MemberUtil {
   private final HttpSession session;

   public boolean isLogin(){
       return getMember() != null;
   }

   public Member getMember(){
       return (Member)session.getAttribute("loginMember");
   }
}
