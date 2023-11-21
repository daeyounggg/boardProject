package project.commons;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.commons.constants.MemberType;
import project.entities.Member;

@Component
@RequiredArgsConstructor
public class MemberUtil {
   private final HttpSession session;

   public boolean isLogin(){
       return getMember() != null;
   }

    /**
     * 관리자 여부 체크
     * @return
     */
    public boolean isAdmin() {
        return isLogin() && getMember().getMtype() == MemberType.ADMIN;
    }

    public Member getMember() {
        return (Member)session.getAttribute("loginMember");
    }
}
