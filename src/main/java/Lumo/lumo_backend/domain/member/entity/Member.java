package Lumo.lumo_backend.domain.member.entity;

import Lumo.lumo_backend.domain.alarm.entity.Alarm;
import Lumo.lumo_backend.domain.todo.entity.ToDo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Login login;

    @Email
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(length = 50) // SNS 연동 시에는 NULL
    private String password;

    private Integer missionSuccessRate = 0; // 초기값 = 0

    private Integer consecutiveSuccessCnt = 0; // 초기값 = 0

    @OneToMany
    private List<Alarm> alarmList = new ArrayList<>();

    @OneToMany
    private List<ToDo> toDoList = new ArrayList<>();

    /*@OneToOne
    private Setting setting;*/

}
