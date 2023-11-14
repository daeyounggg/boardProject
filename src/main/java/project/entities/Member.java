package project.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import project.commons.constants.MemberType;

import java.time.LocalDateTime;

@Data
public class Member {

    @Id
    private Long userNo;

    private String email;

    private String password;

    private String userNm;

    private String mobile;

    private MemberType mtype = MemberType.USER;

    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
