package project.controllers.boards;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Errors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import project.commons.MemberUtil;
import project.commons.ScriptExceptionProcess;
import project.commons.Utils;
import project.commons.constants.BoardAuthority;
import project.commons.exceptions.AlertBackException;
import project.entities.Board;
import project.entities.BoardData;
import project.models.board.BoardInfoService;
import project.models.board.BoardSaveService;
import project.models.board.config.BoardConfigInfoService;
import project.models.board.config.BoardNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller("Controller")
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController implements ScriptExceptionProcess {
    private final Utils utils;
    private final MemberUtil memberUtil;
    private final BoardSaveService saveService;
    private final BoardInfoService infoService;
    private final BoardConfigInfoService configInfoService;

    @GetMapping("/write/{bId}")
    public String write(@PathVariable("bId") String bId, @ModelAttribute  BoardForm form, Model model) {
        commonProcess(bId, "write", model);

        if (memberUtil.isLogin()) {
            form.setPoster(memberUtil.getMember().getUserNm());
        }

        form.setBId(bId);

        return utils.tpl("board/write");
    }

    @GetMapping("/update/{seq}")
    public String update(@PathVariable("seq") Long seq, Model model) {

        return utils.tpl("board/update");
    }

    @PostMapping("/save")
    public String save(@Valid BoardForm form, Errors errors, Model model) {
        String mode = Objects.requireNonNullElse(form.getMode(), "write");
        String bId = form.getBId();

        commonProcess(bId, mode, model);

        if (errors.hasErrors()) {
            return utils.tpl("board/" + mode);
        }

        saveService.save(form);

        return "redirect:/board/list/" + bId;
    }

    @GetMapping("/view/{seq}")
    public String view(@PathVariable("seq") Long seq, Model model) {

        BoardData data = infoService.get(seq);

        model.addAttribute("boardData", data);

        return utils.tpl("board/view");
    }

    @GetMapping("/delete/{seq}")
    public String delete(@PathVariable("seq") Long seq) {

        return "redirect:/board/list/게시판 ID";
    }

    @GetMapping("/list/{bId}")
    public String list(@PathVariable("bId") String bId, Model model) {

        return utils.tpl("board/list");
    }

    private void commonProcess(String bId, String mode, Model model) {

        Board board = configInfoService.get(bId);

        if (board == null || (!board.isActive() && !memberUtil.isAdmin())) { // 등록되지 않거나 또는 미사용 중 게시판
            throw new BoardNotFoundException();
        }

        /* 게시판 분류 S */
        String category = board.getCategory();
        List<String> categories = StringUtils.hasText(category) ?
            Arrays.stream(category.trim().split("\\n"))
                    .map(s -> s.replaceAll("\\r",""))
                    .toList() : null;

        model.addAttribute("categories", categories);
        /* 게시판 분류 E */

        String bName = board.getBName();
        String pageTitle = bName;
        if (mode.equals("write")) pageTitle = bName + " 작성";
        else if (mode.equals("update")) pageTitle = bName + " 수정";
        else if (mode.equals("view")) pageTitle = "게시글 제목";


        /* 글쓰기, 수정시 권한 체크 S */
        if (mode.equals("write") || mode.equals("update")) {
            BoardAuthority authority = board.getAuthority();
            if (!memberUtil.isAdmin() && !memberUtil.isLogin()
                    && authority == BoardAuthority.MEMBER) { // 회원 전용
                throw new AlertBackException(Utils.getMessage("MemberOnly.board", "error"));
            }

            if (authority == BoardAuthority.ADMIN && !memberUtil.isAdmin()) { // 관리자 전용
                throw new AlertBackException(Utils.getMessage("AdminOnly.board", "error"));
            }
        }
        /* 글쓰기, 수정시 권한 체크 E */

        List<String> addCommonScript = new ArrayList<>();
        List<String> addScript = new ArrayList<>();

        if (mode.equals("write") || mode.equals("update")) {
            addCommonScript.add("ckeditor/ckeditor");
            addCommonScript.add("fileManager");

            addScript.add("board/form");
        }

        model.addAttribute("addCommonScript", addCommonScript);
        model.addAttribute("addScript", addScript);
        model.addAttribute("pageTitle", pageTitle);
    }
}