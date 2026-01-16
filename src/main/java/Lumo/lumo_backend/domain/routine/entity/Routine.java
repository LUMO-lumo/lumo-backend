package Lumo.lumo_backend.domain.routine.entity;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.subroutine.entity.Subroutine;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Routine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(/*mappedBy = "routine",*/ cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Subroutine> subroutineList = new ArrayList<>();

    public Routine(String title, Member member){
        this.title = title;
        this.member = member;
    }

    public void addSubroutine (Subroutine subroutine){
        this.subroutineList.add(subroutine);
    }

    public void renameRoutine(String title){
        this.title = title;
    }
}
