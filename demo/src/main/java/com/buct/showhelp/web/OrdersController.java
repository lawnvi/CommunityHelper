package com.buct.showhelp.web;

import com.buct.showhelp.service.OrderService;
import com.buct.showhelp.utils.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(Global.PACKAGE_NAME+"/order/")
public class OrdersController {
    private OrderService orderService;

}
