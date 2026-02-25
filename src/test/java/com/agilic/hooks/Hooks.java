package com.agilic.hooks;

import com.agilic.driver.PlayWrightDriver;
import io.cucumber.java.After;

public class Hooks {
    @After
    public void tearDown() {
        System.out.println("Closing driver...");
        PlayWrightDriver.closeDriver();
    }
}
