package Lumo.lumo_backend.domain.setting.service;


import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.setting.dto.FeedbackCreateReqDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackResDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackUpdateReqDTO;
import Lumo.lumo_backend.domain.setting.entity.Feedback;
import Lumo.lumo_backend.domain.setting.exception.SettingException;
import Lumo.lumo_backend.domain.setting.repository.FeedbackRepository;
import Lumo.lumo_backend.domain.setting.status.SettingErrorCode;
import Lumo.lumo_backend.global.apiResponse.status.ErrorCode;
import Lumo.lumo_backend.global.exception.GeneralException;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long create(Long memberId, FeedbackCreateReqDTO request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(
                        () -> new MemberException(MemberErrorCode.CANT_FOUND_MEMBER)
                );

        Feedback feedback = Feedback.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .email(request.getEmail())
                .build();

        return feedbackRepository.save(feedback).getId();
    }

//    @Transactional(readOnly = true)
//    public FeedbackResDTO get(Long memberId, Long feedbackId) {
//        Feedback feedback = feedbackRepository.findById(feedbackId)
//                .orElseThrow(
//                        () -> new SettingException(SettingErrorCode.FEEDBACK_NOT_FOUND)
//                );
//
//        // member 검증
//        if (feedback.getMember().getId() != memberId) {
//            throw new GeneralException(ErrorCode.BAD_REQUEST);
//        }
//
//        return FeedbackResDTO.from();
//    }

    @Transactional
    public void update(Long memberId, Long feedbackId, FeedbackUpdateReqDTO request) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow(
                        () -> new SettingException(SettingErrorCode.FEEDBACK_NOT_FOUND)
                );


        // member 검증
        if (feedback.getMember().getId() != memberId) {
            throw new GeneralException(ErrorCode.BAD_REQUEST);
        }

        feedback.update(request.getTitle(), request.getContent(), request.getEmail());
    }

    @Transactional
    public void delete(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
