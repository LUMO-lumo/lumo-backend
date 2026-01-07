package Lumo.lumo_backend.domain.todo.controller;

import Lumo.lumo_backend.domain.todo.dto.request.ToDoCreateRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.request.ToDoUpdateRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import Lumo.lumo_backend.domain.todo.service.ToDoService;
import Lumo.lumo_backend.domain.todo.status.ToDoSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/to-do")
@RequiredArgsConstructor
public class ToDoController {

    private final ToDoService toDoService;

    @Operation(summary = "할 일 생성")
    @PostMapping
    public APIResponse<ToDoResponseDTO> create(
            @RequestHeader Long memberId, // 토큰 설정 후 수정 필요
            @RequestBody @Valid ToDoCreateRequestDTO toDoCreateRequestDTO
    ) {
        ToDoResponseDTO toDoResponseDTO = toDoService.create(memberId, toDoCreateRequestDTO);
        return APIResponse.onSuccess(toDoResponseDTO, ToDoSuccessCode.CREATE_TODO_SUCCESS);
    }

    @Operation(summary = "할 일 수정")
    @PatchMapping("/{toDoId}")
    public APIResponse<ToDoResponseDTO> update(
            @RequestHeader Long memberId, // 수정 필요
            @PathVariable Long toDoId,
            @RequestBody @Valid ToDoUpdateRequestDTO toDoUpdateRequestDTO
    ) {
        ToDoResponseDTO toDoResponseDTO = toDoService.update(memberId, toDoId, toDoUpdateRequestDTO);
        return APIResponse.onSuccess(toDoResponseDTO, ToDoSuccessCode.UPDATE_TODO_SUCCESS);
    }
}
