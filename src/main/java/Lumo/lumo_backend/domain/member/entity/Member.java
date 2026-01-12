package Lumo.lumo_backend.domain.member.entity;

import Lumo.lumo_backend.domain.alarm.entity.Alarm;
import Lumo.lumo_backend.domain.routine.entity.Routine;
import Lumo.lumo_backend.domain.setting.entity.Feedback;
import Lumo.lumo_backend.domain.setting.entity.memberSetting.MemberSetting;
import Lumo.lumo_backend.domain.todo.entity.ToDo;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
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
public class Member extends BaseEntity {

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

    private Boolean isProUpgraded = false;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Alarm> alarmList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ToDo> toDoList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "member", orphanRemoval = true)
    private MemberSetting setting;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Routine> routineList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Feedback> feedbacks = new ArrayList<>();

    public void addRoutine (Routine routine){
        this.routineList.add(routine);
    }
}