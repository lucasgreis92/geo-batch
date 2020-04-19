package br.com.spintec.logicae.geobatch.controller.v1;


import br.com.spintec.logicae.geobatch.model.Tags;
import br.com.spintec.logicae.geobatch.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rs/v1/tags")
public class TagsControllerV1 {

    @Autowired
    private TagsRepository tagsRepository;

    @GetMapping("{serial}")
    public List<Tags> findByDeviceSerial(@PathVariable("serial") String deviceSerial) {
        return tagsRepository.findByDeviceSerial(deviceSerial);
    }
}
