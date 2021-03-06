package br.com.spintec.logicae.geobatch.controller.v1;

import br.com.spintec.logicae.geobatch.model.Sensors;
import br.com.spintec.logicae.geobatch.repository.SensorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("pagination/filter")
    public Page<Sensors> findByFilter(@RequestParam(required = true) String device,
                                      @RequestParam(required = false) Long port ,
                                      @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedIni,
                                      @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedFim,
                                      @PageableDefault(sort = {"dtCreated"},
                                              direction = Sort.Direction.DESC,page = 0,size = 10) Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(Sort.Direction.ASC,"on"));
        if (port == null) {
            return sensorsRepository.findByDeviceSerialAndCollectedBetween(device,collectedIni,collectedFim, pageable);
        } else {
            return sensorsRepository.findByDeviceSerialAndPortAndCollectedBetween(device,port,collectedIni,collectedFim,pageable);
        }

    }

    @GetMapping("findstatus/{device}/{port}")
    public Sensors findStatus(@PathVariable("device") String device, @PathVariable("port") Long port) {

        Sensors lastSensor = sensorsRepository.findLast(device,port);
        if (lastSensor == null) {
            return null;
        }
        Sensors lastOtherValueSensor = sensorsRepository.findLastOtherValue(device,port,lastSensor.getValue());
        if (lastOtherValueSensor == null) {
            return lastSensor;
        }
        return sensorsRepository.findFirstLastValue(device,port,lastSensor.getValue(),lastOtherValueSensor.getCollected());
    }
}
