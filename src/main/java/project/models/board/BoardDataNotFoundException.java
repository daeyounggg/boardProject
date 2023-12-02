package project.models.board;

import org.springframework.http.HttpStatus;
import project.commons.Utils;
import project.commons.exceptions.AlertBackException;

public class BoardDataNotFoundException extends AlertBackException {

    public BoardDataNotFoundException() {
        super("등록되지 않은 게시글 입니다.");
        setStatus(HttpStatus.NOT_FOUND);
    }
}