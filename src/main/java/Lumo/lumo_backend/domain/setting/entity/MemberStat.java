package Lumo.lumo_backend.domain.setting.entity;


import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자 통계 엔티티
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_stat")
public class MemberStat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_stat_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false, unique = true)
    private Member member;

    @Column(nullable = false)
    private int alarmCreationCount;

    @Column(nullable = false)
    private int alarmActivateCount;

    @Column(nullable = false)
    private int appOpenCount;

    @Column(nullable = false)
    private int alarmOffMissionCompleteCount;

    @Column(nullable = false)
    private int activatedAlarmOffCount;

}
