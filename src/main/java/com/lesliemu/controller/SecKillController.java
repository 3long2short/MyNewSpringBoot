package com.lesliemu.controller;

import com.lesliemu.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class SecKillController {

    @Autowired
    SecKillService service;
    @ResponseBody
    @RequestMapping("/doseckill")
    public Boolean secKill(String prodid){
        String userid = new Random().nextInt(50000)+"";
        Boolean result = service.doSecKill(prodid,userid);
        if (result==null){
            System.out.println("是null哦");
            return false;
        }
        return result;
    }
}
