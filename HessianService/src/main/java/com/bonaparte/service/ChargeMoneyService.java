package com.bonaparte.service;

import com.bonaparte.interfacet.ChargeMoney;
import org.springframework.stereotype.Service;

/**
 * Created by yangmingquan on 2018/10/1.
 */
@Service(value = "chargeMoneyService")
public class ChargeMoneyService implements ChargeMoney {

    @Override
    public double getChargeMoneyById(Integer uid) {
        return 0;
    }
}
