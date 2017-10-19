package com.liulishuo.interview.coins.controller;

import com.liulishuo.interview.coins.dao.CoinsDao;
import com.liulishuo.interview.coins.model.Coins;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController = new UserController();

    @Mock
    private CoinsDao coinsDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Coins coins1 = new Coins();
        coins1.setUserId(1);
        coins1.setCoins(100);
        Coins coins2 = new Coins();
        coins2.setUserId(2);
        coins2.setCoins(20);

        Mockito.when(coinsDao.save(coins1)).thenReturn(coins1);
        Mockito.when(coinsDao.save(coins2)).thenReturn(coins2);

        Mockito.when(coinsDao.findOne(1L)).thenReturn(coins1);
        Mockito.when(coinsDao.findOne(2L)).thenReturn(coins2);
    }


    @Test
    public void testAddUser() {
        String resp;

        resp = userController.addUser(1, 100);
        Assert.assertEquals(resp, "OK");

        resp = userController.addUser(1, -100);
        Assert.assertEquals(resp, "Error coins");

        resp = userController.addUser(-1, 100);
        Assert.assertEquals(resp, "Error userId");
    }


    @Test
    public void testGetUser() {
        String resp;

        resp = userController.getUser(1);
        Assert.assertEquals(resp, "100");

        resp = userController.getUser(-1);
        Assert.assertEquals(resp, "Error");

        resp = userController.getUser(3);
        Assert.assertEquals(resp, "Not found");
    }


    @Test
    public void testTransfer() {
        String resp;

        resp = userController.transfer(-1, 2, 10);
        Assert.assertEquals(resp, "Error");

        resp = userController.transfer(1, -2, 10);
        Assert.assertEquals(resp, "Error");

        resp = userController.transfer(1, 2, -10);
        Assert.assertEquals(resp, "Error");

        resp = userController.transfer(1, 2, 0);
        Assert.assertEquals(resp, "OK");

        resp = userController.transfer(1, 1, 10);
        Assert.assertEquals(resp, "OK");

        resp = userController.transfer(1, 3, 10);
        Assert.assertEquals(resp, "Error, 3 not found");

        resp = userController.transfer(3, 2, 10);
        Assert.assertEquals(resp, "Error, 3 not found");

        resp = userController.transfer(1, 2, 1000);
        Assert.assertEquals(resp, "Error, 1 does not have enough coins");

        resp = userController.transfer(1, 2, 10);
        Assert.assertEquals(resp, "OK");
        resp = userController.getUser(1);
        Assert.assertEquals(resp, "90");
        resp = userController.getUser(2);
        Assert.assertEquals(resp, "30");
    }
}
