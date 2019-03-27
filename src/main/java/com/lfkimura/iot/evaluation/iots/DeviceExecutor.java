package com.lfkimura.iot.evaluation.iots;

import com.lfkimura.iot.evaluation.dto.DeviceDataResponseDTO;
import com.lfkimura.iot.evaluation.dto.DeviceInputDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public abstract class DeviceExecutor implements Runnable {

    protected String serverEndpoint = "http://localhost:8080/device/data";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void sendIOTData(DeviceInputDataDTO input) throws RestClientException {
        ResponseEntity<DeviceDataResponseDTO> response = null;
        DeviceDataResponseDTO data = null;
        try {
            response = new RestTemplate().postForEntity(serverEndpoint, input, DeviceDataResponseDTO.class);
            data = response.getBody();
        } catch (Exception e) {
            logger.error("error {}", e.getMessage());

        } finally {
            logger.info("IOT request {} response {}", input, data);
        }

    }

    protected boolean doStop = false;

    public synchronized void doStop() {
        this.doStop = true;
    }

    public synchronized void doResume() {
        this.doStop = false;
    }

    protected synchronized boolean keepRunning() {
        return this.doStop == false;
    }
}
