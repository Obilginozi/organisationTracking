package com.duzceuniversity.kurumtakip.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/communication")
public class CommunicationController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String add(){
        return "Communication";
    }
}
