package br.com.spintec.logicae.geobatch.controller.v1;


import br.com.spintec.logicae.geobatch.model.CraneData;
import br.com.spintec.logicae.geobatch.repository.CraneDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("rs/v1/cranedata")
public class CraneDataControllerV1 {

    @Autowired
    private CraneDataRepository craneDataRepository;

    @GetMapping("filter")
    public List<CraneData> findByFilter(@RequestParam(required = true) String device,
                                        @RequestParam(required = false) Long port ,
                                        @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedIni,
                                        @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedFim) {

        if (port == null) {
            return craneDataRepository.findByFilter(device, collectedIni, collectedFim);
        } else {
            return craneDataRepository.findByFilter(device, port.intValue(), collectedIni, collectedFim);
        }

    }

    @GetMapping("pagination/filter")
    public Page<CraneData> findByFilter(@RequestParam(required = true) String device,
                                        @RequestParam(required = false) Long port ,
                                        @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedIni,
                                        @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime collectedFim,
                                        @PageableDefault(sort = {"dtCreated"},
                                                direction = Sort.Direction.DESC,page = 0,size = 10) Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),Sort.by(Sort.Direction.ASC,"on"));
        if (port == null) {
            return craneDataRepository.findByDeviceSerialAndOffBetween(device, collectedIni, collectedFim, pageable);
        } else{
            return craneDataRepository.findByDeviceSerialAndPortAndOffBetween(device, port.intValue(), collectedIni, collectedFim, pageable);
        }
    }


    }
