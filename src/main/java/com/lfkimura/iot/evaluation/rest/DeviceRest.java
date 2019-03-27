package com.lfkimura.iot.evaluation.rest;

import com.lfkimura.iot.evaluation.dto.DeviceDataResponseDTO;
import com.lfkimura.iot.evaluation.dto.DeviceInputDataDTO;
import com.lfkimura.iot.evaluation.dto.DeviceResponseDTO;
import com.lfkimura.iot.evaluation.dto.ValueDTO;
import com.lfkimura.iot.evaluation.service.InputDeviceDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "device")
public class DeviceRest {

    @Autowired
    private InputDeviceDataService inputDeviceDataService;


    @PostMapping("/data")
    @ApiOperation(
            value = "create/input data for IOT device",
            notes = "uses name manufacturer and type to check if the device already exists")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "CREATED"),
            @ApiResponse(code = 400, message = "", response = DeviceDataResponseDTO.class),
            @ApiResponse(code = 500, message = "Error", response = DeviceDataResponseDTO.class)})
    public ResponseEntity<DeviceDataResponseDTO> inputData(@Validated @RequestBody DeviceInputDataDTO request) {

        DeviceDataResponseDTO result = inputDeviceDataService.inputData(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping()
    @ApiOperation(
            value = "list devices registers",
            notes = "devices created")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "", response = DeviceResponseDTO.class),
            @ApiResponse(code = 500, message = "Error", response = DeviceResponseDTO.class)})
    public ResponseEntity<List<DeviceResponseDTO>> listDevice() {

        List<DeviceResponseDTO> result = inputDeviceDataService.listDevices();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping("/data/{type}/history")
    @ApiOperation(
            value = "list devices data history",
            notes = "devices created")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "", response = DeviceDataResponseDTO.class),
            @ApiResponse(code = 500, message = "Error", response = DeviceDataResponseDTO.class)})
    public ResponseEntity<List<DeviceDataResponseDTO>> historyDataType(@PathVariable("type") String type) {

        List<DeviceDataResponseDTO> result = inputDeviceDataService.history(type);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }


    @GetMapping("/data/{type}/average")
    @ApiOperation(
            value = "list devices registers",
            notes = "devices data created")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "", response = ValueDTO.class),
            @ApiResponse(code = 500, message = "Error", response = ValueDTO.class)})
    public ResponseEntity<ValueDTO> averageData(@PathVariable("type") String type) {

        ValueDTO result = inputDeviceDataService.average(type);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/data/{type}/max")
    @ApiOperation(
            value = "max value of  device data registered",
            notes = "max data of type created")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "SUCCESS"),
            @ApiResponse(code = 400, message = "", response = ValueDTO.class),
            @ApiResponse(code = 500, message = "Error", response = ValueDTO.class)})
    public ResponseEntity<ValueDTO> maxValue(@PathVariable("type") String type) {

        ValueDTO result = inputDeviceDataService.maxValue(type);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
