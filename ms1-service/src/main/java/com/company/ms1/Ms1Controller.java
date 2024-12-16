package com.company.ms1; 

import javax.servlet.http.HttpServletRequest;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ms1-service")
@Slf4j
public class Ms1Controller {
    Environment env;

    //@Autowired 
    public Ms1Controller(Environment env) { this.env = env; }

    @GetMapping("/welcome")
    public String welcome() { return "..... ms1 service....."; }

    @GetMapping("/message")
    public String message(@RequestHeader("ms1-request") String header) {
        log.info(header);
        return "..... ms1 service.....";
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request) {
        return String.format("Ms1 Service on PORT  : %s" , env.getProperty("local.server.port"));
    }
}
