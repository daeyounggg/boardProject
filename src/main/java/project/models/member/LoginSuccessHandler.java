package project.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import project.commons.Utils;

import java.io.IOException;
import java.util.Objects;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();

        Utils.loginInit(session);

        /* 로그인 성공시 페이지 이동
         * 요청 데이터 redirectURL 값이 있으면 이동, 없으면 메인 페이지(/)
         *
         */
        String redirectURL = Objects.requireNonNullElse(request.getParameter("redirectURL"), "/");
        response.sendRedirect(request.getContextPath() + redirectURL);
    }
}
