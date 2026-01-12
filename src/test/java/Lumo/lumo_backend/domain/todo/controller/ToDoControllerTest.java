package Lumo.lumo_backend.domain.todo.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import Lumo.lumo_backend.domain.member.exception.MemberException;
import Lumo.lumo_backend.domain.member.status.MemberErrorCode;
import Lumo.lumo_backend.domain.todo.dto.request.ToDoCreateRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.request.ToDoUpdateRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoListResponseDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import Lumo.lumo_backend.domain.todo.entity.ToDo;
import Lumo.lumo_backend.domain.todo.exception.ToDoException;
import Lumo.lumo_backend.domain.todo.service.ToDoService;
import Lumo.lumo_backend.domain.todo.status.ToDoErrorCode;
import Lumo.lumo_backend.domain.todo.status.ToDoSuccessCode;
import Lumo.lumo_backend.global.exception.ExceptionAdvice;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ToDoController.class)
@Import(ExceptionAdvice.class)
@AutoConfigureMockMvc(addFilters = false)
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    JpaMetamodelMappingContext jpaMappingContext;

    @MockitoBean
    private ToDoService toDoService;

    @Test
    @DisplayName("할 일 생성 성공")
    void create() throws Exception {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2025, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO request = new ToDoCreateRequestDTO(eventDate, content);
        ToDoResponseDTO response = new ToDoResponseDTO(1L, content, eventDate);
        given(toDoService.create(memberId, request)).willReturn(response);

        mockMvc.perform(post("/api/to-do")
                        .header("memberId", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1L))
                .andExpect(jsonPath("$.result.content").value(content))
                .andExpect(jsonPath("$.result.eventDate").value(eventDate.toString()));

    }

    @Test
    @DisplayName("할 일 생성-사용자 NOT FOUND")
    void createNOT_FOUND() throws Exception {
        Long memberId = 0L;
        LocalDate eventDate = LocalDate.of(2025, 1, 1);
        String content = "쓰레기 버리기";

        ToDoCreateRequestDTO request = new ToDoCreateRequestDTO(eventDate, content);
        given(toDoService.create(memberId, request)).willThrow(new MemberException(MemberErrorCode.CANT_FOUND_MEMBER));

        mockMvc.perform(post("/api/to-do")
                        .header("memberId", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("MEMBER4000"));

    }

    @Test
    @DisplayName("할 일 생성-검증 로직 실패")
    void createValidationFailed() throws Exception {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2025, 1, 1);
        String content = "";

        ToDoCreateRequestDTO request = new ToDoCreateRequestDTO(eventDate, content);

        mockMvc.perform(post("/api/to-do")
                        .header("memberId", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("COMMON400_1"))
                .andExpect(jsonPath("$.result.content").value("must not be blank"));

    }

    @Test
    @DisplayName("할 일 생성-JSON 형식 오류")
    void createInvalidJSON() throws Exception {

        mockMvc.perform(post("/api/to-do")
                        .header("memberId", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"eventDate\": \"20205-01-0\", \"content\": \"쓰레기 버리기\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("COMMON400_2"))
                .andExpect(jsonPath("$.message").value("JSON 형식이 올바르지 않습니다."));

    }

    @Test
    @DisplayName("할 일 수정 성공")
    void update() throws Exception {
        Long memberId = 1L;
        Long toDoId = 1L;
        LocalDate eventDate = LocalDate.of(2025, 1, 1);
        String content = "쓰레기 버리기";
        String newContent = "분리수거하기";

        ToDoUpdateRequestDTO request = new ToDoUpdateRequestDTO(newContent);
        ToDoResponseDTO response = new ToDoResponseDTO(toDoId, newContent, eventDate);
        given(toDoService.update(memberId, toDoId, request)).willReturn(response);

        mockMvc.perform(patch("/api/to-do/{toDoId}", toDoId)
                        .header("memberId", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.id").value(1L))
                .andExpect(jsonPath("$.result.content").value(newContent))
                .andExpect(jsonPath("$.result.eventDate").value(eventDate.toString()));

    }

    @Test
    @DisplayName("할 일 수정-할 일 NOT FOUND")
    void updateTODO_NOT_FOUND() throws Exception {
        Long memberId = 1L;
        Long toDoId = 0L;
        String newContent = "분리수거하기";

        ToDoUpdateRequestDTO request = new ToDoUpdateRequestDTO(newContent);

        given(toDoService.update(memberId, toDoId, request)).willThrow(new ToDoException(ToDoErrorCode.NOT_FOUND));
        mockMvc.perform(patch("/api/to-do/{toDoId}", toDoId)
                        .header("memberId", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ToDoErrorCode.NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.message").value(ToDoErrorCode.NOT_FOUND.getMessage()));

    }

    @Test
    @DisplayName("할 일 수정-접근 권한 없음")
    void updateACCESS_DENIED() throws Exception {
        Long memberId = 1L;
        Long toDoId = 0L;
        String newContent = "분리수거하기";

        ToDoUpdateRequestDTO request = new ToDoUpdateRequestDTO(newContent);
        given(toDoService.update(memberId, toDoId, request)).willThrow(new ToDoException(ToDoErrorCode.ACCESS_DENIED));

        mockMvc.perform(patch("/api/to-do/{toDoId}", toDoId)
                        .header("memberId", memberId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ToDoErrorCode.ACCESS_DENIED.getCode()))
                .andExpect(jsonPath("$.message").value(ToDoErrorCode.ACCESS_DENIED.getMessage()));

    }

    @Test
    @DisplayName("할 일 삭제")
    void delete() throws Exception {
        Long memberId = 1L;
        Long toDoId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/to-do/{doDoId}", toDoId)
                        .header("memberId", memberId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ToDoSuccessCode.DELETE_TODO_SUCCESS.getCode()))
                .andExpect(jsonPath("$.message").value(ToDoSuccessCode.DELETE_TODO_SUCCESS.getMessage()));

    }

    @Test
    @DisplayName("할 일 삭제-TODO_NOTFOUND")
    void deleteToDoNOTFOUND() throws Exception {
        Long memberId = 1L;
        Long toDoId = 0L;
        willThrow(new ToDoException(ToDoErrorCode.NOT_FOUND))
                .given(toDoService).delete(memberId, toDoId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/to-do/{doDoId}", toDoId)
                        .header("memberId", memberId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ToDoErrorCode.NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.message").value(ToDoErrorCode.NOT_FOUND.getMessage()));
    }

    @Test
    @DisplayName("할 일 삭제-TODO_ACCESS_DENIED")
    void deleteToDoNACCESS_DENIED() throws Exception {
        Long memberId = 2L;
        Long toDoId = 1L;
        willThrow(new ToDoException(ToDoErrorCode.ACCESS_DENIED))
                .given(toDoService).delete(memberId, toDoId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/to-do/{doDoId}", toDoId)
                        .header("memberId", memberId))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ToDoErrorCode.ACCESS_DENIED.getCode()))
                .andExpect(jsonPath("$.message").value(ToDoErrorCode.ACCESS_DENIED.getMessage()));
    }

    @Test
    @DisplayName("일별 할 일 목록 조회")
    void findToDoListByEventDate() throws Exception {
        Long memberId = 1L;
        LocalDate eventDate = LocalDate.of(2020, 1, 1);

        List<ToDo> toDoList=new ArrayList<>();
        long toDoId = 1L;
        String content = "쓰레기 버리기";
        toDoList.add(ToDo.builder()
                .id(toDoId)
                .content(content)
                .eventDate(eventDate)
                .build());
        given(toDoService.findToDoListByEventDate(memberId, eventDate))
                .willReturn(ToDoListResponseDTO.from(toDoList));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/to-do")
                        .param("eventDate", eventDate.toString())
                        .header("memberId", memberId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(ToDoSuccessCode.GET_TODO_SUCCESS.getCode()))
                .andExpect(jsonPath("$.message").value(ToDoSuccessCode.GET_TODO_SUCCESS.getMessage()))
                .andExpect(jsonPath("$.result.toDoList[0].id").value(toDoId))
                .andExpect(jsonPath("$.result.toDoList[0].content").value(content))
                .andExpect(jsonPath("$.result.toDoList[0].eventDate").value(eventDate.toString()));
    }
}