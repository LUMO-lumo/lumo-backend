package Lumo.lumo_backend.domain.subroutine;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subroutine {
    @Id
    private Long id;

    private String title;

    private Integer successCount;

    private Boolean isSuccess;


}
