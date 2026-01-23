package Lumo.lumo_backend.domain.setting.repository;

import Lumo.lumo_backend.domain.setting.entity.terms.MemberTermsAgreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberTermsAgreementRepository extends JpaRepository<MemberTermsAgreement, Long> {

}
