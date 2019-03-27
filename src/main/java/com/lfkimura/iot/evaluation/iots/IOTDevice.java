package com.lfkimura.iot.evaluation.iots;

public abstract class IOTDevice {

    DeviceExecutor deviceExecutor;

    Thread thread;

    public abstract void startDeviceExecution();

    public void stopDeviceExecution(){
        deviceExecutor.doStop();
    }

    public void resumeDeviceExecution(){
        deviceExecutor.doResume();
    }
}
