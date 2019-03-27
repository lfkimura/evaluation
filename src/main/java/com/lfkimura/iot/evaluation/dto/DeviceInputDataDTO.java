package com.lfkimura.iot.evaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class DeviceInputDataDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "manufacturer is mandatory")
    private String manufacturer;
    @NotBlank(message = "type is mandatory")
    private String type;
    @NotBlank(message = "measureUnit is mandatory")
    private String measureUnit;
    @NotBlank(message = "value is mandatory")
    private String value;
}
