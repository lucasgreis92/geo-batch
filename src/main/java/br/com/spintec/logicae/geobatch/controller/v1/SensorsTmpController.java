package br.com.spintec.logicae.geobatch.controller.v1;

import br.com.spintec.logicae.geobatch.dto.SensorsDtoV1;
import br.com.spintec.logicae.geobatch.model.Sensors;
import br.com.spintec.logicae.geobatch.model.SensorsTmp;
import br.com.spintec.logicae.geobatch.service.SensorsTmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/1.0/sensor/lesense")
public class SensorsTmpController {

    @Autowired
    private SensorsTmpService sensorsTmpService;

    @PostMapping
    public void saveBatch(ArrayList<SensorsDtoV1> sensors) {
        sensorsTmpService.savelAll(sensors);
    }


}
