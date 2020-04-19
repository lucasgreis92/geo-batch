package br.com.spintec.logicae.geobatch.controller.v1;

import br.com.spintec.logicae.geobatch.service.LesenseBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GenerateCalibrationData {

    @Autowired
    private LesenseBatchService lesenseBatchService;

    @Scheduled(cron = "*/30 * * ? * *")
    public void generateData() {
        lesenseBatchService.generateCalibrationData();
    }
}
