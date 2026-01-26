package Lumo.lumo_backend.domain.setting.entity;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * 사용자 기기 엔티티
 */
@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member_device")
public class MemberDevice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_device_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id", nullable = false)
//    private Member member;

    @Column(nullable = false)
    private String deviceName;

    @Column(nullable = false)
    private String modelName;

    @Column(nullable = false)
    private String osVersion;


    public void update(String deviceName, String modelName, String osVersion) {
        this.deviceName = deviceName;
        this.modelName = modelName;
        this.osVersion = osVersion;
    }

}
