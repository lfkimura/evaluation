package com.lfkimura.iot.evaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceResponseDTO {
    private Long id;
    private String name;
    private String manufacturer;
    private String type;
}
