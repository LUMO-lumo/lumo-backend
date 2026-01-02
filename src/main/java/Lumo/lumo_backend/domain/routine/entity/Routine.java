package Lumo.lumo_backend.domain.routine.entity;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.subroutine.Subroutine;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subroutine> subroutineList = new ArrayList<>();
}
