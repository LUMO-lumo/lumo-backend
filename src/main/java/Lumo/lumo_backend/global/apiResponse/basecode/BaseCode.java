package Lumo.lumo_backend.global.apiResponse.basecode;


import Lumo.lumo_backend.global.apiResponse.dto.ReasonDTO;

public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();

}

