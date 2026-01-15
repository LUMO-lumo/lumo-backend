package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.setting.dto.InquiryCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.InquiryResponseDTO;
import Lumo.lumo_backend.domain.setting.entity.Inquiry;
import Lumo.lumo_backend.domain.setting.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InquiryService {
    private final MemberRepository memberRepository;
    private final InquiryRepository inquiryRepository;

    public InquiryResponseDTO create(Long memberId, InquiryCreateRequestDTO inquiryCreateRequestDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));

        Inquiry inquiry = Inquiry.builder()
                .member(member)
                .title(inquiryCreateRequestDTO.getTitle())
                .content(inquiryCreateRequestDTO.getContent())
                .build();

        Inquiry saved = inquiryRepository.save(inquiry);

        return InquiryResponseDTO.from(saved);
    }
}
