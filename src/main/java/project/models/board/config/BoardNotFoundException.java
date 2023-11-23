package project.models.board.config;

import org.springframework.http.HttpStatus;
import project.commons.Utils;
import project.commons.exceptions.AlertBackException;

public class BoardNotFoundException extends AlertBackException {
    public BoardNotFoundException() {
        super(Utils.getMessage("NotFound.board", "error"));
        setStatus(HttpStatus.NOT_FOUND);
    }
}