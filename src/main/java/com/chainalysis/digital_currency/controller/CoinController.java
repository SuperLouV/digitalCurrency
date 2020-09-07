package com.chainalysis.digital_currency.controller;

import com.alibaba.fastjson.JSONArray;
import com.chainalysis.digital_currency.model.Coin;
import com.chainalysis.digital_currency.service.CoinService;
import com.chainalysis.digital_currency.utils.CountPrice;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
//import com.qiubao.school.api.model.juhe.JuheResult;


/**
 * @author Yilinlou
 * @date 9/3/20 5:44 下午
 * @Email:ylou7@stevens.edu
 */
@Controller
public class CoinController {
    @Autowired
    private CoinService coinService;


    @GetMapping("/v1")
    public String home() throws IOException {
        huobiCoins();
        krakenCoins();
        return "index";
    }


    @ResponseBody
    @GetMapping("/huobi_coins")
    public void huobiCoins() throws IOException {

        OkHttpClient clientEth = new OkHttpClient().newBuilder()
                .build();
        Request requestEth = new Request.Builder()
                .url("https://api.huobi.pro/market/history/trade?symbol=ethusdt&size=20")
                .method("GET", null)
                .build();
        Response responseEth = clientEth.newCall(requestEth).execute();
        if (responseEth.body() != null) {
            String result = responseEth.body().string();
            if (StringUtils.isNotBlank(result)) {
                JSONArray jsonArr;
                JSONObject jsonObject = JSONObject.parseObject(result);
                jsonArr = (JSONArray) jsonObject.get("data");
                for (int i = 0; i < jsonArr.size(); i++) {
                    JSONObject jsonObject2 = JSONObject.parseObject(jsonArr.get(i).toString());
                    JSONArray jsonArr2 = (JSONArray) jsonObject2.get("data");
                    for (int j = 0; j < Math.min(jsonArr2.size(), 20); j++) {
                        JSONObject sub = JSONObject.parseObject(jsonArr2.get(j).toString());
                        Coin coin = new Coin();
                        String price = sub.getString("price");
                        coin.setCoin_price(Double.parseDouble(price));
                        coin.setCoin_direction((String) sub.get("direction"));
                        coin.setCoin_source("huobi");
                        coin.setCoin_type("ETH");
                        coinService.saveCoin(coin);

                    }
                }
            }
        }


        OkHttpClient clientBtc = new OkHttpClient().newBuilder()
                .build();
        Request requestBtc = new Request.Builder()
                .url("https://api.huobi.pro/market/history/trade?symbol=btcusdt&size=20")
                .method("GET", null)
                .build();
        Response responseBtc = clientBtc.newCall(requestBtc).execute();
        if (responseBtc.body() != null) {
            String result = responseBtc.body().string();
            if (StringUtils.isNotBlank(result)) {
                JSONArray jsonArr;
                JSONObject jsonObject = JSONObject.parseObject(result);
                jsonArr = (JSONArray) jsonObject.get("data");
                for (int i = 0; i < jsonArr.size(); i++) {
                    JSONObject jsonObject2 = JSONObject.parseObject(jsonArr.get(i).toString());
                    JSONArray jsonArr2 = (JSONArray) jsonObject2.get("data");
                    for (int j = 0; j < Math.min(jsonArr2.size(), 20); j++) {
                        JSONObject sub = JSONObject.parseObject(jsonArr2.get(j).toString());
                        Coin coin = new Coin();
                        String price = sub.getString("price");
                        coin.setCoin_price(Double.parseDouble(price));
                        coin.setCoin_direction((String) sub.get("direction"));
                        coin.setCoin_source("huobi");
                        coin.setCoin_type("BTC");
                        coinService.saveCoin(coin);

                    }
                }
            }
        }

    }

    @ResponseBody
    @GetMapping("/kraken_coins")
    public void krakenCoins() throws IOException {
        OkHttpClient clientBtc = new OkHttpClient().newBuilder()
                .build();
        Request requestBtc = new Request.Builder()
                .url("https://api.kraken.com/0/public/Trades?pair=BTCUSD")
                .method("GET", null)
                .build();
        Response responseBtc = clientBtc.newCall(requestBtc).execute();
        if (responseBtc.body() != null) {
            String resultBtc = responseBtc.body().string();
            JSONObject jsonObject = JSONObject.parseObject(resultBtc);
            JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get("result").toString());
            JSONArray jsonArr;
            jsonArr = (JSONArray) jsonObject2.get("XXBTZUSD");

            for (int i = 0; i < Math.min(jsonArr.size(), 20); i++) {
                String strCur = jsonArr.get(i).toString();
                strCur =
                        strCur.replace("[", "").
                                replace("]", "")
                                .replace("\"", "")
                                .replace("\"\"", "");
                System.out.println(strCur);
                String[] arr = strCur.split(",");
                System.out.println(Arrays.toString(arr));
                Coin coin = new Coin();
                coin.setCoin_source("kraken");
                coin.setCoin_type("BTC");
                coin.setCoin_price(Double.valueOf(arr[0]));
                if (arr[3].equals("s")) {
                    coin.setCoin_direction("sell");
                } else if (arr[3].equals("b")) {
                    coin.setCoin_direction("buy");
                }
                coinService.saveCoin(coin);
            }


        }


        OkHttpClient clientEth = new OkHttpClient().newBuilder()
                .build();
        Request requestEth = new Request.Builder()
                .url("https://api.kraken.com/0/public/Trades?pair=ETHUSD")
                .method("GET", null)
                .build();
        Response responseEth = clientEth.newCall(requestEth).execute();
        if (responseEth.body() != null) {
            String resultEth = responseEth.body().string();
            JSONObject jsonObject = JSONObject.parseObject(resultEth);
            JSONObject jsonObject2 = JSONObject.parseObject(jsonObject.get("result").toString());
            JSONArray jsonArr;
            jsonArr = (JSONArray) jsonObject2.get("XETHZUSD");

            for (int i = 0; i < Math.min(jsonArr.size(), 20); i++) {
                String strCur = jsonArr.get(i).toString();
                strCur =
                        strCur.replace("[", "").
                                replace("]", "")
                                .replace("\"", "")
                                .replace("\"\"", "");
                String[] arr = strCur.split(",");
                Coin coin = new Coin();
                coin.setCoin_source("kraken");
                coin.setCoin_type("ETH");
                coin.setCoin_price(Double.valueOf(arr[0]));
                if (arr[3].equals("s")) {
                    coin.setCoin_direction("sell");
                } else if (arr[3].equals("b")) {
                    coin.setCoin_direction("buy");
                }
                coinService.saveCoin(coin);
            }


        }

    }


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/v1/data")
    public List[] coin_info() throws IOException {
        List<String[]> coinPrices = new ArrayList<>();
        CountPrice countPrice=new CountPrice();
        huobiCoins();
        krakenCoins();
        List[] dataList = new List[3];
        List<Coin> huobiBtc = coinService.findCoinBySourceAndDirection("BTC", "huobi");
        List<Coin> huobiEth = coinService.findCoinBySourceAndDirection("ETH", "huobi");
        List<Coin> krakenBtc = coinService.findCoinBySourceAndDirection("BTC", "kraken");
        List<Coin> krakenEth = coinService.findCoinBySourceAndDirection("ETH", "kraken");

        String [] btcHighestsell=countPrice.getPrice(huobiBtc,krakenBtc,"sell");
        String [] btcLowestbuy=countPrice.getPrice(huobiBtc,krakenBtc,"buy");
        String [] ethHighestsell=countPrice.getPrice(huobiEth,krakenEth,"sell");
        String [] ethLowestbuy=countPrice.getPrice(huobiEth,krakenEth,"buy");
        coinPrices.add(btcHighestsell);
        coinPrices.add(btcLowestbuy);
        coinPrices.add(ethHighestsell);
        coinPrices.add(ethLowestbuy);

        huobiBtc.addAll(huobiEth);
        krakenBtc.addAll(krakenEth);
        dataList[0]=huobiBtc;
        dataList[1]=krakenBtc;
        dataList[2]= coinPrices;
        return dataList;
    }




}
