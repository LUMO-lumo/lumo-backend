package Lumo.lumo_backend.domain.subroutine.entity;

import Lumo.lumo_backend.domain.routine.entity.Routine;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Subroutine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 각 서브루틴 제목

    private Integer successCount = 0; // 연속 성공 횟수

    private Boolean isSuccess = false;

    @ManyToOne
    @JoinColumn(name = "routine_id")
    private Routine routine;

    public Subroutine(String title, Routine routine){
        this.title = title;
        this.routine = routine;
    }

    public void renameSubroutine (String title){
        this.title = title;
    }

    public void checkSubroutine (){
        this.isSuccess = !this.isSuccess;
    }

    public void increateCnt (){
        this.successCount++;
    }
}
