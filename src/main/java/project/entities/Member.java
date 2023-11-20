package project.entities;

import jakarta.persistence.*;
import lombok.*;
import project.commons.constants.MemberType;

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

public class Member extends Base{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userNo;

    @Column(length = 65, unique = true, nullable = false)
    private String email;

    @Column(length = 65, name="pw", nullable = false)
    private String password;

    @Column(length = 40, nullable = false)
    private String userNm;

    @Column(length = 11)
    private String mobile;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType mtype = MemberType.USER;

    @ToString.Exclude
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<BoardData> items = new ArrayList<>();

}