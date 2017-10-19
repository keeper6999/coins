package com.liulishuo.interview.coins.model;

import javax.persistence.*;

@Entity
@Table(name = "coins")
public class Coins {
    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name = "coins")
    private int coins;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
