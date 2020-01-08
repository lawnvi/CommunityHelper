package com.buct.showhelp.web;

import java.util.List;

import com.buct.showhelp.mapper.TestMapper;
import com.buct.showhelp.pojo.Test;
import com.buct.showhelp.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Global.PACKAGE_NAME+"/test/")
public class TestController {
    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/listTest")
    public String listCategory(Model m) throws Exception {
        List<Test> testList = testMapper.findAll();

        m.addAttribute("ts", testList);

        return "listTest";
    }

}