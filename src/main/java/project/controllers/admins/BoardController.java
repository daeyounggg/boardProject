package project.controllers.admins;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.commons.menus.Menu;

import java.util.Objects;

@Controller("adminBoardController")
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class BoardController {

    private final HttpServletRequest request;

    @GetMapping
    public String list(Model model){
        commonProcess("list",model);
        return "admin/board/list";
    }

    @GetMapping("/add")
    public String register(Model model){
        commonProcess("add",model);
        return "admin/board/add";
    }

    @GetMapping("/edit/{bId}")
    public String update(@PathVariable String bId, Model model) {
        commonProcess("edit",model);
        return "admin/board/edit";
    }

    @PostMapping("/save")
    public String save(){

        return "redirect:/admin/board";
    }

    private void commonProcess(String mode, Model model){
        String pageTitle = "게시판 목록";
        mode = Objects.requireNonNullElse(mode, "list");
        if (mode.equals("add")) pageTitle = "게시판 등록";
        else if (mode.equals("edit")) pageTitle = "게시판 수정";

        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("menuCode","board");
        model.addAttribute("submenus", Menu.gets("board"));
        model.addAttribute("subMenuCode",Menu.getSubMenuCode(request));
    }
}
