package Lumo.lumo_backend.domain.encouragement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "encouragement")
public class Encouragement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "encouragement_id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

}
