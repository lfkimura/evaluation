package com.lfkimura.iot.evaluation.service;

import com.lfkimura.iot.evaluation.domain.Device;
import com.lfkimura.iot.evaluation.domain.DeviceData;
import com.lfkimura.iot.evaluation.dto.DeviceInputDataDTO;
import com.lfkimura.iot.evaluation.dto.DeviceDataResponseDTO;
import com.lfkimura.iot.evaluation.dto.DeviceResponseDTO;
import com.lfkimura.iot.evaluation.dto.ValueDTO;
import com.lfkimura.iot.evaluation.repository.DeviceDataRepository;
import com.lfkimura.iot.evaluation.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class InputDeviceDataService {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceDataRepository deviceDataRepository;


    public DeviceDataResponseDTO inputData(DeviceInputDataDTO request) {

        Optional<Device> deviceOpt = deviceRepository.findByNameAndManufacturerAndType(request.getName(), request.getManufacturer(), request.getType());

        Device device = deviceOpt.orElseGet(() -> deviceRepository.save(new Device(request.getName(), request.getManufacturer(), request.getType(), request.getMeasureUnit())));
        DeviceData data = deviceDataRepository.save(new DeviceData(device, request.getValue()));
        DeviceDataResponseDTO response = new DeviceDataResponseDTO();
        response.setDeviceId(device.getId());
        response.setDataId(data.getId());
        response.setValue(data.getValue());
        response.setDate(data.getCreatedAt());
        return response;
    }

    public List<DeviceResponseDTO> listDevices() {

        List<Device> devices = deviceRepository.findAll();

        return devices.stream().map(device -> new DeviceResponseDTO(device.getId(), device.getName(), device.getManufacturer(), device.getType())).collect(Collectors.toList());
    }

    public List<DeviceDataResponseDTO> history(String type) {

        return deviceDataRepository.findAllByDeviceType(type).stream().map(data -> new DeviceDataResponseDTO(data.getDevice().getId(), data.getId(), data.getValue(), data.getCreatedAt())).collect(Collectors.toList());
    }

    public ValueDTO average(String type) {
        return new ValueDTO(deviceDataRepository.findAllByDeviceType(type).stream().mapToDouble(data -> new Double(data.getValue()).doubleValue()).average().getAsDouble());

    }

    public ValueDTO maxValue(String type) {
        return new ValueDTO(deviceDataRepository.findAllByDeviceType(type).stream().mapToDouble(data -> new Double(data.getValue()).doubleValue()).max().getAsDouble());

    }
}
