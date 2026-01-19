package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.setting.dto.DeviceCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.entity.MemberDevice;
import Lumo.lumo_backend.domain.setting.exception.SettingException;
import Lumo.lumo_backend.domain.setting.repository.MemberDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static Lumo.lumo_backend.domain.member.status.MemberErrorCode.CANT_FOUND_MEMBER;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberDeviceService {

    private final MemberRepository memberRepository;

    public void create(Long memberId, DeviceCreateRequestDTO request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new MemberException(CANT_FOUND_MEMBER)
                );

        MemberDevice device = MemberDevice.builder()
                .deviceName(request.getDeviceName())
                .modelName(request.getModelName())
                .osVersion(request.getOsVersion())
                .build();

        member.getDevices().add(device);
    }
}
