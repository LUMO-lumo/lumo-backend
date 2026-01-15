package Lumo.lumo_backend.domain.setting.service;

import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.setting.dto.InquiryCreateRequestDTO;
import Lumo.lumo_backend.domain.setting.dto.InquiryResponseDTO;
import Lumo.lumo_backend.domain.setting.entity.Inquiry;
import Lumo.lumo_backend.domain.setting.exception.InquiryException;
import Lumo.lumo_backend.domain.setting.repository.InquiryRepository;
import Lumo.lumo_backend.domain.setting.status.InquiryErrorCode;
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

    public InquiryResponseDTO update(Long memberId, Long inquiryId, InquiryCreateRequestDTO inquiryCreateRequestDTO) {
        //todo 자식 문의사항 생성 시 수정 불가
        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new InquiryException(InquiryErrorCode.INQUIRY_NOT_FOUND));

        if(!inquiry.isOwnedBy(memberId)){
            throw new InquiryException(InquiryErrorCode.INQUIRY_ACCESS_DENIED);
        }

        inquiry.update(inquiryCreateRequestDTO.getTitle(), inquiryCreateRequestDTO.getContent());

        inquiryRepository.flush(); //update_at 갱신 후 응답
        return InquiryResponseDTO.from(inquiry);
    }
}
