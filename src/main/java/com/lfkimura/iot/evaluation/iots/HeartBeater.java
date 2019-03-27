package com.lfkimura.iot.evaluation.iots;

import com.lfkimura.iot.evaluation.dto.DeviceDataResponseDTO;
import com.lfkimura.iot.evaluation.dto.DeviceInputDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class HeartBeater {


    protected String endpoint = "http://localhost:8080/device/data";

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private BeaterIOT myRunnableBeater;


    public void stopBeater(){
        myRunnableBeater.doStop();
    }

    public void startBeater(){
        myRunnableBeater.doStop();
    }



    @PostConstruct
    public void init() {
        myRunnableBeater = new BeaterIOT();

        Thread thread = new Thread(myRunnableBeater);

        thread.start();

    }



    class BeaterIOT implements Runnable {

        public void sendIOTData(DeviceInputDataDTO input) throws RestClientException {
            ResponseEntity<DeviceDataResponseDTO> response = null;
            DeviceDataResponseDTO data = null;
            try {
                response = new RestTemplate().postForEntity(endpoint, input, DeviceDataResponseDTO.class);
                data = response.getBody();
            } catch (Exception e) {
                logger.error("error {}", e.getMessage());

            } finally {
                logger.info("IOT request {} response {}", input, response);
            }

        }
        private boolean doStop = false;

        public synchronized void doStop() {
            this.doStop = true;
        }

        private synchronized boolean keepRunning() {
            return this.doStop == false;
        }

        @Override
        public void run() {
            while (keepRunning()) {
                // keep doing what this thread should do.
                logger.info("running hearBeater");

                try {
                    Double max = 200.0,min=50.0;
                    Double value = Math.floor(Math.random() * (max - min) + +min);

                    DeviceInputDataDTO input = new DeviceInputDataDTO("beater", "KimuraCorp", "HeartBeater", "bpm", value.toString());
                    this.sendIOTData(input);

                    Thread.sleep(1L * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
