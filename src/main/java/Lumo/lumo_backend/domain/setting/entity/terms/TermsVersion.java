package Lumo.lumo_backend.domain.setting.entity.terms;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * 약관 버전 엔티티
 */
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "terms_version")
public class TermsVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_version_id")
    private Long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "terms_id", nullable = false)
    private Terms terms;

    @Column(nullable = false)
    private String version;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant effectiveAt;

    @Column(nullable = false)
    private Instant expiredAt;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private boolean active;

}
