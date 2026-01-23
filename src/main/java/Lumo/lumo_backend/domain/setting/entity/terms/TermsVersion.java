//package Lumo.lumo_backend.domain.setting.entity.terms;
//
//
//import Lumo.lumo_backend.global.BaseEntity.BaseEntity;
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
///**
// * 약관 버전 엔티티
// */
//@Entity
//@Builder
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor(access = AccessLevel.PRIVATE)
//@Getter
//@Table(name = "terms_version")
//public class TermsVersion extends BaseEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "terms_version_id")
//    private Long id;
//
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "terms_id", nullable = false)
//    private Terms terms;
//
//    @Column(nullable = false)
//    private String version;
//
//    @Column(nullable = false)
//    private String content;
//
//    @Column(nullable = false)
//    private LocalDateTime effectiveAt;
//
//    @Column(nullable = false)
//    private LocalDateTime expiredAt;
//
//    @Column(nullable = false)
//    private boolean active;
//
//}
