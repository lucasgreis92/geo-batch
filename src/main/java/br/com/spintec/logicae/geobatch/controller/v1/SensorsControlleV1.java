package br.com.spintec.logicae.geobatch.controller.v1;

import br.com.spintec.logicae.geobatch.model.Sensors;
import br.com.spintec.logicae.geobatch.repository.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("rs/v1/sensors")
public class SensorsControlleV1 {

    @Autowired
    private SensorsRepository sensorsRepository;

    @GetMapping("filter")
    public List<Sensors> findByFilter(@RequestParam(required = true) String device,
                                      @RequestParam(required = false) Long port ,
                                      @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedIni,
                                      @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedFim) {

        if (port == null) {
            return sensorsRepository.findByFilter(device, collectedIni, collectedFim);
        } else {
            return sensorsRepository.findByFilter(device, port, collectedIni, collectedFim);
        }

    }
}
