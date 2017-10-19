package com.liulishuo.interview.coins.controller;

import com.liulishuo.interview.coins.$;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/ops")
@ResponseBody
public class OpsController {

    @GetMapping(value = "/jstack")
    public String getJstack() throws Exception{
        return $.getJstack();
    }
}
