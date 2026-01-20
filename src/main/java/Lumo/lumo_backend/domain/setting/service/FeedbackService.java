package Lumo.lumo_backend.domain.setting.service;


import Lumo.lumo_backend.domain.member.entity.Member;
import Lumo.lumo_backend.domain.member.repository.MemberRepository;
import Lumo.lumo_backend.domain.setting.dto.FeedbackCreateReqDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackResDTO;
import Lumo.lumo_backend.domain.setting.dto.FeedbackUpdateReqDTO;
import Lumo.lumo_backend.domain.setting.entity.Feedback;
import Lumo.lumo_backend.domain.setting.repository.FeedbackRepository;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final MemberRepository memberRepository;

    public Long create(Long memberId, FeedbackCreateReqDTO request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow();

        Feedback feedback = Feedback.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        return feedbackRepository.save(feedback).getId();
    }

    @Transactional(readOnly = true)
    public FeedbackResDTO get(Long feedbackId) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow();
        return FeedbackResDTO.from(feedback);
    }

    public void update(Long feedbackId, FeedbackUpdateReqDTO request) {
        Feedback feedback = feedbackRepository.findById(feedbackId)
                .orElseThrow();
        feedback.update(request.getTitle(), request.getContent());
    }

    public void delete(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
}
