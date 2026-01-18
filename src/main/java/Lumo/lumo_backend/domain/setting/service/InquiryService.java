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

    public InquiryResponseDTO create(Member member, InquiryCreateRequestDTO inquiryCreateRequestDTO) {
        Member persistedMember = getPersistedMember(member);

        Inquiry inquiry = Inquiry.builder()
                .member(persistedMember)
                .title(inquiryCreateRequestDTO.getTitle())
                .content(inquiryCreateRequestDTO.getContent())
                .build();

        Inquiry saved = inquiryRepository.save(inquiry);

        return InquiryResponseDTO.from(saved);
    }

    public InquiryResponseDTO update(Member member, Long inquiryId, InquiryCreateRequestDTO inquiryCreateRequestDTO) {
        //todo 자식 문의사항 생성 시 수정 불가
        Member persistedMember = getPersistedMember(member);

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new InquiryException(InquiryErrorCode.INQUIRY_NOT_FOUND));

        if(inquiry.getMember()!=persistedMember){
            throw new InquiryException(InquiryErrorCode.INQUIRY_ACCESS_DENIED);
        }

        inquiry.update(inquiryCreateRequestDTO.getTitle(), inquiryCreateRequestDTO.getContent());

        inquiryRepository.flush(); //update_at 갱신 후 응답
        return InquiryResponseDTO.from(inquiry);
    }

    @Transactional(readOnly = true)
    public InquiryResponseDTO get(Member member, Long inquiryId) {
        Member persistedMember = getPersistedMember(member);

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new InquiryException(InquiryErrorCode.INQUIRY_NOT_FOUND));

        if(inquiry.getMember()!=persistedMember){
            throw new InquiryException(InquiryErrorCode.INQUIRY_ACCESS_DENIED);
        }

        return InquiryResponseDTO.from(inquiry);
    }

    private Member getPersistedMember(Member member) {
        return memberRepository.findById(member.getId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));
    }
}
