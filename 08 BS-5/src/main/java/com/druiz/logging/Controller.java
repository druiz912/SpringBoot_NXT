package com.druiz.logging;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/c1")
public class Controller {

    @RequestMapping("/")
    public String index() {
        log.trace("TRACE Message");
        log.debug("DEBUG Message");
        log.info("INFO Messsage");
        log.warn("WARN Message");
        log.error("ERROR Message");

        return "Check out the Logs to see the output";
    }
}

