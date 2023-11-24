package project.controllers.boards;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.commons.MemberUtil;
import project.commons.ScriptExceptionProcess;
import project.commons.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController implements ScriptExceptionProcess {
    private final Utils utils;

    private final MemberUtil memberUtil;

    @GetMapping("/write/{bId}")
    public String write(@PathVariable String bId,@Valid BoardForm form, Model model){
        commonProcess(bId,model,"write");

        return utils.tpl("board/write");
    }

    @GetMapping("/update/{seq}")
    public String update(@PathVariable Long seq, Model model){
        return utils.tpl("board/update");
    }

    @PostMapping("/save")
    public String save(@Valid BoardForm form, Errors errors, Model model){
        String mode = Objects.requireNonNullElse(form.getMode(),"write");
        String bId = form.getBId();
        if(errors.hasErrors()){
            return utils.tpl("board/" + mode);
        }

        return "redirect:/board/list/게시판ID";
    }

    @GetMapping("/view/{seq}")
    public String view(@PathVariable Long seq, Model model){

        return utils.tpl("board/view");
    }

    @GetMapping("/delete/{seq}")
    public String delete(@PathVariable Long seq){

        return "redirect:/board/list/게시판 ID";
    }

    private void commonProcess(String mode, Model model, String bId){
        String pageTitle = "게시글 목록";
        if (mode.equals("write")) pageTitle="게시글 작성";
        else if (mode.equals("update")) pageTitle="게시글 수정";
        else if (mode.equals("view")) pageTitle="게시글 제목";

        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();

        if(mode.equals("write") || mode.equals("update")){
            addCommonScript.add("ckeditor/ckeditor");
            addCommonScript.add("fileManager");

            addScript.add("board/form");
        }

        model.addAttribute("addCommonScript",addCommonScript);
        model.addAttribute("addScript",addScript);
        model.addAttribute("pageTitle",pageTitle);
    }

}
