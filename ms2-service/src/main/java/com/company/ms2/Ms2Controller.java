package com.company.ms2; 

import javax.servlet.http.HttpServletRequest;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ms2-service")
@Slf4j
public class Ms2Controller {
    Environment env;

    //@Autowired 
    public Ms2Controller(Environment env) { this.env = env; }

    @GetMapping("/welcome")
    public String welcome() { return "..... ms2 service....."; }

    @GetMapping("/message")
    public String message(@RequestHeader("ms2-request") String header) {
        log.info(header);
        return "..... ms2 service.....";
    }
    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        return String.format("Ms1 Service on PORT  : %s" , env.getProperty("local.server.port"));
    }
}
