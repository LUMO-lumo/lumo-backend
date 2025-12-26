package Lumo.lumo_backend.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 50)
    private String email;

    @Column
    private Login login;

    private Integer mcnScsRate; // 미션 성공률, double 이 아닌 정수로만

    private Integer consecutiveScsCnt; // 연속 성공수
}
