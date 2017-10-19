package com.liulishuo.interview.coins.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class OpsControllerTest {

    private OpsController opsController = new OpsController();

    @Test
    public void testOpsController() throws Exception {
        Assert.assertNotNull(opsController.getJstack());
    }
}
