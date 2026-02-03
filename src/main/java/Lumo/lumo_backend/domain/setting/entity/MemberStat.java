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

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", nullable = false, unique = true)
//    private Member member;

//    @Column(nullable = false)
//    private int alarmCreationCount; // 알람 생성 횟수

    @Column(nullable = false)
    private int alarmActivateCount = 0; // 알람 활성화 횟수

    @Column(nullable = false)
    private int appOpenCount = 0; // 앱 연 횟수

    @Column(nullable = false)
    private int missionCompleteCount = 0; // 미션 완료 횟수

//    @Column(nullable = false)
//    private int activatedAlarmOffCount;

    public static MemberStat createDefault() {
        return MemberStat.builder()
//                .member(member)
//                .alarmCreationCount(0)
//                .appOpenCount(0)
                .build();
    }


}
