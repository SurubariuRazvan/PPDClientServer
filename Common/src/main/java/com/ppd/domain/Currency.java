package com.ppd.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Currency {
    @Column(name = "value")
    private double value;

    @Column(name = "currency")
    private String currency;

    public Currency(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public Currency() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
