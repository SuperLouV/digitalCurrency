package com.chainalysis.digital_currency.service;

import com.chainalysis.digital_currency.dao.CoinDao;
import com.chainalysis.digital_currency.model.Coin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yilinlou
 * @date 9/5/20 3:49 下午
 * @Email:ylou7@stevens.edu
 */

@Service
public class CoinService {
    @Autowired
    private CoinDao coinDao;


    public Coin saveCoin(Coin coin) {
        return coinDao.save(coin);
    }

    public List<Coin> findCoinBySourceAndDirection(String type, String source) {
        return coinDao.findCoinByCoinDirectionAndAndCoinSource(type, source);
    }

    public List<Coin> findCoinBySourceAndDirection(String source) {
        return coinDao.findCoinByCoinDirectionAndAndCoinSource(source);
    }

}
