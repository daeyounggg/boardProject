package project.models.file;

import org.springframework.http.HttpStatus;
import project.commons.Utils;
import project.commons.exceptions.CommonException;

public class FileNotFoundException extends CommonException {

    public FileNotFoundException() {
        super(Utils.getMessage("NotFound.file", "error"), HttpStatus.NOT_FOUND);
    }
}
