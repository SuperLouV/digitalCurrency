package com.chainalysis.digital_currency.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Yilinlou
 * @date 9/4/20 5:08 下午
 * @Email:ylou7@stevens.edu
 */


@Entity //define the entity
@Table(name = "coins")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Coin {

    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String coin_id;

    private String coin_type;

    private double coin_price;

    private String coin_direction;

    private String coin_source;

    public String getCoin_source() {
        return coin_source;
    }

    public void setCoin_source(String coin_source) {
        this.coin_source = coin_source;
    }

    public String getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(String coin_id) {
        this.coin_id = coin_id;
    }

    public String getCoin_type() {
        return coin_type;
    }

    public void setCoin_type(String coin_type) {
        this.coin_type = coin_type;
    }

    public double getCoin_price() {
        return coin_price;
    }

    public void setCoin_price(double coin_price) {
        this.coin_price = coin_price;
    }

    public String getCoin_direction() {
        return coin_direction;
    }

    public void setCoin_direction(String coin_direction) {
        this.coin_direction = coin_direction;
    }
}
