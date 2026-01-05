package Lumo.lumo_backend.domain.todo.controller;

import Lumo.lumo_backend.domain.todo.dto.request.CreateToDoRequestDTO;
import Lumo.lumo_backend.domain.todo.dto.response.ToDoResponseDTO;
import Lumo.lumo_backend.domain.todo.service.ToDoService;
import Lumo.lumo_backend.domain.todo.status.ToDoSuccessCode;
import Lumo.lumo_backend.global.apiResponse.APIResponse;
import Lumo.lumo_backend.global.apiResponse.status.SuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
            @RequestBody @Valid CreateToDoRequestDTO createToDoRequestDTO
    ) {
        ToDoResponseDTO toDoResponseDTO = toDoService.create(memberId, createToDoRequestDTO);
        return APIResponse.onSuccess(toDoResponseDTO, ToDoSuccessCode.CREATE_TODO_SUCCESS);
    }

}
