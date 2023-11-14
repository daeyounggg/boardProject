package project.controllers.members;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.commons.Utils;
import project.models.member.MemberInfo;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final Utils utils;

    @GetMapping("/join")
    public String join() {

        return utils.tpl("member/join");
    }

    @GetMapping("/login")
    public String login(String redirectURL, Model model) {
        model.addAttribute("redirectURL", redirectURL);
        return utils.tpl("member/login");
    }

    @ResponseBody
    @GetMapping("/info")
    public void info(){
        MemberInfo member = (MemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info(member.toString());
    }

    /*
    public void info(@AuthenticationPrincipal MemberInfo memberInfo){
        log.info(memberInfo.toString());
    }
     */

/*
    public void info(Principal principal){
        String email = principal.getName();
        log.info(email);

   }
 */
}