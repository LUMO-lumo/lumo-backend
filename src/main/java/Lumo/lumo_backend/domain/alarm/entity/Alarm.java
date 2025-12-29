
package Lumo.lumo_backend.domain.alarm.entity;

import Lumo.lumo_backend.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "alarm")
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long alarmId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "alarm_time", nullable = false)
    private LocalTime alarmTime;

    @Column(name = "label", length = 100)
    private String label;

    @Column(name = "is_enabled")
    @Builder.Default
    private Boolean isEnabled = true;

    @Column(name = "sound_type", length = 50)
    private String soundType;

    @Column(name = "vibration")
    @Builder.Default
    private Boolean vibration = true;

    @Column(name = "volume")
    @Builder.Default
    private Integer volume = 50;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AlarmRepeatDay> repeatDays = new ArrayList<>();

    @OneToOne(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    private AlarmSnooze alarmSnooze;

    @OneToOne(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    private AlarmMission alarmMission;

    @OneToMany(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<AlarmLog> alarmLogs = new ArrayList<>();

    @OneToMany(mappedBy = "alarm", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<MissionHistory> missionHistories = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}