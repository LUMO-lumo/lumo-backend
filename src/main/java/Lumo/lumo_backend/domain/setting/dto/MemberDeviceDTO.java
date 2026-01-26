package Lumo.lumo_backend.domain.setting.dto;

import Lumo.lumo_backend.domain.setting.entity.MemberDevice;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MemberDeviceDTO {

    @Schema(
            description = "기기 ID",
            example= "1"
    )
    private Long id;

    @Schema(
            description = "기기명",
            example = "iPhone 16 Pro Max"
    )
    private String deviceName;
    
    @Schema(
            description = "모델명",
            example = "IP16PM"
    )
    private String modelName;

    @Schema(
            description = "운영체제 버전",
            example = "iOS 26.1"
    )
    private String osVersion;



    public static MemberDeviceDTO from(MemberDevice memberDevice) {
        return new MemberDeviceDTO(
                memberDevice.getId(),
                memberDevice.getDeviceName(),
                memberDevice.getModelName(),
                memberDevice.getOsVersion()
        );
    }
}
