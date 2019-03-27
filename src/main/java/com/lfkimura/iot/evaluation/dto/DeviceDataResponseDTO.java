package com.lfkimura.iot.evaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDataResponseDTO {
    private Long deviceId;
    private Long dataId;
    private String value;
    private LocalDateTime date;
}
