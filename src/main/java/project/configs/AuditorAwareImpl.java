package project.configs;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import project.models.member.MemberInfo;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        String email = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //Object principal = auth.getPrincipal(); // 비회원 - String(문자열) : anonymousUser, 회원 - UserDetails 구현 객체

        if(auth != null && auth.getPrincipal() instanceof MemberInfo){
            MemberInfo member = (MemberInfo)auth.getPrincipal();
            email = member.getEmail();
        }

        return Optional.ofNullable(email);
    }
}
