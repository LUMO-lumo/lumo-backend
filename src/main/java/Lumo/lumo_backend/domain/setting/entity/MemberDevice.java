package Lumo.lumo_backend.domain.setting.entity;

import Lumo.lumo_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * 사용자 기기 엔티티
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "member_device")
public class MemberDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_device_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String device_name;

    @Column(nullable = false)
    private String model_name;

    @Column(nullable = false)
    private String os_version;

}
