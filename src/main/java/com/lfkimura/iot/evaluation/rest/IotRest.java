package com.lfkimura.iot.evaluation.rest;

import com.lfkimura.iot.evaluation.dto.DeviceDataResponseDTO;
import com.lfkimura.iot.evaluation.iots.HeartBeater;
import com.lfkimura.iot.evaluation.iots.SpeedMeasurer;
import com.lfkimura.iot.evaluation.iots.TemperatureMeasurer;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "iot")
public class IotRest {

    @Autowired
    private HeartBeater heartBeater;

    @Autowired
    private SpeedMeasurer speedMeasurer;

    @Autowired
    private TemperatureMeasurer temperatureMeasurer;


    @PostMapping("/start/{iotType}")
    @ApiOperation(
            value = "start IOT to send data",
            notes = "start thread")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED"),
            @ApiResponse(code = 400, message = "", response = DeviceDataResponseDTO.class),
            @ApiResponse(code = 500, message = "Error", response = DeviceDataResponseDTO.class)})
    public ResponseEntity<String> start(@PathVariable("iotType") String type) {

        switch (type) {
            case "heartBeater":
                heartBeater.startDeviceExecution();
                break;
            case "speedMeasurer":
                speedMeasurer.startDeviceExecution();
                break;
            case "temperatureMeasurer":
                temperatureMeasurer.startDeviceExecution();
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID TYPE");

        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("SUCCESS");
    }


    @PostMapping("/resume/{iotType}")
    @ApiOperation(
            value = "start IOT to send data",
            notes = "start thread")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED"),
            @ApiResponse(code = 400, message = "", response = DeviceDataResponseDTO.class),
            @ApiResponse(code = 500, message = "Error", response = DeviceDataResponseDTO.class)})
    public ResponseEntity<String> resume(@PathVariable("iotType") String type) {

        switch (type) {
            case "heartBeater":
                heartBeater.resumeDeviceExecution();
                break;
            case "speedMeasurer":
                speedMeasurer.resumeDeviceExecution();
                break;
            case "temperatureMeasurer":
                temperatureMeasurer.resumeDeviceExecution();
                break;
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID TYPE");

        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("SUCCESS");
    }


    @PostMapping("/stop/{iotType}")
    @ApiOperation(
            value = "start IOT to send data",
            notes = "start thread")
    @ApiResponses(value = {@ApiResponse(code = 202, message = "ACCEPTED"),
            @ApiResponse(code = 400, message = "", response = DeviceDataResponseDTO.class),
            @ApiResponse(code = 500, message = "Error", response = DeviceDataResponseDTO.class)})
    public ResponseEntity<String> stop(@PathVariable("iotType") String type) {

        switch (type) {
            case "heartBeater":
                heartBeater.stopDeviceExecution();
                break;
            case "speedMeasurer":
                speedMeasurer.stopDeviceExecution();
                break;
            case "temperatureMeasurer":
                temperatureMeasurer.stopDeviceExecution();
                break;
            default:

                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("INVALID TYPE");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("SUCCESS");
    }
}
