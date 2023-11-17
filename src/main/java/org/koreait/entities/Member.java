package org.koreait.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.koreait.commons.constants.MemberType;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name="idx_member_userNm", columnList = "userNm"),
        @Index(name="idx_member_mobile", columnList = "mobile")
})
public class Member extends Base {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo;

    @Column(length=65, unique = true, nullable = false)
    private String email;

    @Column(length=65, name="pw", nullable = false)
    private String password;

    @Column(length=40, nullable = false)
    private String userNm;

    @Column(length=11)
    private String mobile;

    @Column(length=10, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType mtype = MemberType.USER;

    @OneToMany(mappedBy = "member")
    private List<BoardData> items = new ArrayList<>();

}
