package com.chainalysis.digital_currency.dao;

import com.chainalysis.digital_currency.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Yilinlou
 * @date 9/5/20 3:47 下午
 * @Email:ylou7@stevens.edu
 */


public interface CoinDao extends JpaRepository<Coin,String> {

    @Query(value = "select * from coins coin where coin.coin_type = ? and coin.coin_source=? order by coin_id desc limit 10",nativeQuery = true)
    List<Coin> findCoinByCoinDirectionAndAndCoinSource(String type, String source);

    @Query(value = "select * from coins coin where coin.coin_source=? order by coin_id desc limit 10",nativeQuery = true)
    List<Coin> findCoinByCoinDirectionAndAndCoinSource(String source);


}
