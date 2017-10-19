package com.liulishuo.interview.coins.dao;

import com.liulishuo.interview.coins.model.Coins;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinsDao extends JpaRepository<Coins, Long> {
}
