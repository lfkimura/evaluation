package com.lfkimura.iot.evaluation.iots;

import com.lfkimura.iot.evaluation.dto.DeviceInputDataDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SpeedMeasurer extends IOTDevice{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {
        deviceExecutor = new SpeedIOT();
        thread = new Thread(deviceExecutor);
    }

    @Override
    public void startDeviceExecution() {
        thread.start();
    }


    class SpeedIOT extends DeviceExecutor {
        @Override
        public void run() {
            while (keepRunning()) {
                // keep doing what this thread should do.
                logger.info("running SpeedMeasurer");

                try {
                    Double max = 140.0,min=0.0;
                    Double value = Math.floor(Math.random() * (max - min) + +min);

                    DeviceInputDataDTO input = new DeviceInputDataDTO("speedometer", "KimuraCorp", "SpeedMeasurer", "km/h", value.toString());
                    this.sendIOTData(input);

                    Thread.sleep(1L * 1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
