package com.bonaparte.controller;

import com.bonaparte.interfacet.ChargeMoney;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangmingquan on 2018/10/1.
 */
@RestController
@RequestMapping(name = "/charge")
public class ChargeMoneyController {
    @Autowired
    ChargeMoney chargeMoney;

    @RequestMapping("/info")
    public Double test() {
        return chargeMoney.getChargeMoneyById(1);
    }
}
