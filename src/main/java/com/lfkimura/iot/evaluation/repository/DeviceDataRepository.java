package com.lfkimura.iot.evaluation.repository;

import com.lfkimura.iot.evaluation.domain.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceDataRepository extends JpaRepository<DeviceData, String> {
    List<DeviceData> findAllByDeviceType(String type);
}
