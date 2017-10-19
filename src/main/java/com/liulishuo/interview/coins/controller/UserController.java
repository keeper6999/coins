package com.liulishuo.interview.coins.controller;

import com.liulishuo.interview.coins.dao.CoinsDao;
import com.liulishuo.interview.coins.model.Coins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired
    private CoinsDao coinsDao;


    @PostMapping(value = "/add")
    public String addUser(
            @RequestParam(value = "user_id") long userId,
            @RequestParam(value = "coins") int coins
    ) {
        if (coins < 0) {
            return "Error coins";
        } else if (userId < 0) {
            return "Error userId";
        } else {
            Coins row = new Coins();
            row.setUserId(userId);
            row.setCoins(coins);
            coinsDao.save(row);
            return "OK";
        }
    }


    @GetMapping(value = "/{id}/coins")
    public String getUser(@PathVariable long id) {
        if (id < 0) {
            return "Error";
        } else {
            Coins row = coinsDao.findOne(id);
            if (row != null) {
                return Integer.toString(row.getCoins());
            } else {
                return "Not found";
            }
        }
    }


    @PostMapping(value = "/transfer")
    public String transfer(
            @RequestParam(value = "from_user_id") long from,
            @RequestParam(value = "to_user_id") long to,
            @RequestParam(value = "coins") int coins
    ) {
        if (coins < 0 || from < 0 || to < 0) {
            return "Error";
        } else if (from == to || coins == 0) {
            return "OK";
        } else {
            Coins fromRow = coinsDao.findOne(from);
            Coins toRow = coinsDao.findOne(to);
            if (fromRow == null) {
                return "Error, " + Long.toString(from) + " not found";
            } else if (toRow == null) {
                return "Error, " + Long.toString(to) + " not found";
            } else if (fromRow.getCoins() < coins) {
                return "Error, " + Long.toString(from) + " does not have enough coins";
            } else {
                transfer(fromRow, toRow, coins);
                return "OK";
            }
        }
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    private void transfer(Coins fromRow, Coins toRow, int coins) {
        int fromCoins = fromRow.getCoins();
        int toCoins = toRow.getCoins();

        fromRow.setCoins(fromCoins - coins);
        toRow.setCoins(toCoins + coins);

        coinsDao.save(fromRow);
        coinsDao.save(toRow);
    }
}
