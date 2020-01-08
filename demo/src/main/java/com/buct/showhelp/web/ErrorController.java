package com.buct.showhelp.web;

import com.buct.showhelp.utils.Global;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tips")
public class ErrorController {

    @RequestMapping("/noPage")
    public String showNoPage(){
        return "error/awsl";
    }
}
