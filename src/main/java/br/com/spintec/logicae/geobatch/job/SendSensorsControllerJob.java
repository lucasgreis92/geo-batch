package br.com.spintec.logicae.geobatch.job;

import br.com.spintec.logicae.geobatch.service.LesenseBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendSensorsControllerJob {

    @Autowired
    private LesenseBatchService lesenseBatchService;

    @Scheduled(cron = "*/20 * * ? * *")
    public void sendSensors() {
        lesenseBatchService.sendSensorsStart();
    }

}
