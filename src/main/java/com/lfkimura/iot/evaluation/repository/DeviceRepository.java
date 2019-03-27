package com.lfkimura.iot.evaluation.repository;

import com.lfkimura.iot.evaluation.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
    Optional<Device> findByNameAndManufacturerAndType(String name, String manufacturer, String type);

}
