package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.setting.dto.MemberDeviceCreateReqDTO;
import Lumo.lumo_backend.domain.setting.dto.MemberDeviceResDTO;
import Lumo.lumo_backend.domain.setting.dto.MemberDeviceUpdateReqDTO;
import Lumo.lumo_backend.domain.setting.entity.MemberDevice;
import Lumo.lumo_backend.domain.setting.exception.SettingException;
import Lumo.lumo_backend.domain.setting.repository.MemberDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static Lumo.lumo_backend.domain.member.status.MemberErrorCode.CANT_FOUND_MEMBER;
import static Lumo.lumo_backend.domain.setting.status.SettingErrorCode.DEVICE_NOT_FOUND;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberDeviceService {

    private final MemberRepository memberRepository;

    public void create(Long memberId, MemberDeviceCreateReqDTO request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new MemberException(CANT_FOUND_MEMBER)
                );

        MemberDevice device = MemberDevice.builder()
                .deviceName(request.getDeviceName())
                .modelName(request.getModelName())
                .osVersion(request.getOsVersion())
                .uuid(request.getUuid())
                .build();

        member.getDeviceList().add(device);
    }

    public MemberDeviceResDTO getList(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new MemberException(CANT_FOUND_MEMBER)
                );

        return MemberDeviceResDTO.from(member.getDeviceList());
    }

    public void update(Long memberId, MemberDeviceUpdateReqDTO request, Long deviceId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new MemberException(CANT_FOUND_MEMBER)
                );


        MemberDevice memberDevice = member.getDeviceList().stream()
                .filter(device -> device.getId().equals(deviceId))
                .findFirst()
                .orElseThrow(
                        () -> new SettingException(DEVICE_NOT_FOUND)
                );


        memberDevice.update(
                request.getDeviceName(),
                request.getModelName(),
                request.getOsVersion(),
                request.getUuid()
        );
    }

    public void delete(Long memberId, Long deviceId) {

    }
}
