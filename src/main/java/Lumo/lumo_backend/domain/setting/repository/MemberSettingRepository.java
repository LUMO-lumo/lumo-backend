package Lumo.lumo_backend.domain.setting.repository;

import Lumo.lumo_backend.domain.setting.entity.memberSetting.MemberSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberSettingRepository extends JpaRepository<MemberSetting, Long> {
}
