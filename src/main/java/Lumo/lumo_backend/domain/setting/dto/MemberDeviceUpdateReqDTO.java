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

    @Schema(
            description = "기기 고유값",
            example = "E2C56DB5-DFFB-48D2-B060-D0F5A71096E0"
    )
    private String uuid;
}
