package com.chainalysis.digital_currency.utils;

import com.chainalysis.digital_currency.model.Coin;
import com.chainalysis.digital_currency.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yilinlou
 * @date 9/6/20 4:55 下午
 * @Email:ylou7@stevens.edu
 */
public class CountPrice {


    public String[] getPrice(List<Coin> huobi,List<Coin> kraken,String operation) {
        String [] str=new String[2];
        double price;
        String source=null;
        if (operation.equals("sell")){
            price=Integer.MIN_VALUE;
            for (Coin coin:huobi){
                if (coin.getCoin_price()>price&&coin.getCoin_direction().equals("sell")){
                    price=coin.getCoin_price();
                    source="huobi";
                }
            }
            for (Coin coin:kraken){
                if (coin.getCoin_price()>price&&coin.getCoin_direction().equals("sell")){
                    price=coin.getCoin_price();
                    source="kraken";
                }
            }
        }else {
            price=Integer.MAX_VALUE;
            for (Coin coin:huobi){
                if (coin.getCoin_price()<price&&coin.getCoin_direction().equals("buy")){
                    price=coin.getCoin_price();
                    source="huobi";
                }
            }
            for (Coin coin:kraken){
                if (coin.getCoin_price()<price&&coin.getCoin_direction().equals("buy")){
                    price=coin.getCoin_price();
                    source="kraken";
                }
            }
        }
        if (price==Integer.MAX_VALUE||price==Integer.MIN_VALUE){
            price=0;
            source="No trading record";
        }

        str[0]=String.valueOf(price);
        str[1]=source;


        return str;
    }
}
