package Lumo.lumo_backend.domain.setting.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class MemberDeviceUpdateReqDTO {

    @Schema(
            description = "기기 이름",
            example = "lumo의 iPhone"
    )
    private String deviceName;


    @Schema(
            description = "모델 이름",
            example = "iPhone 16 Pro Max"
    )
    private String modelName;


    @Schema(
            description = "OS 버전",
            example = "iOS 26.1"
    )
    private String osVersion;
}
