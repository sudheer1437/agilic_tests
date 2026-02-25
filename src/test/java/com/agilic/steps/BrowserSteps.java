package com.agilic.steps;

import com.agilic.driver.PlayWrightDriver;
import com.microsoft.playwright.Page;
import net.thucydides.core.steps.ScenarioSteps;

public class BrowserSteps extends ScenarioSteps {

    private Page getPage() {
        return PlayWrightDriver.getPage();
    }
    public void clickOnBrowserButton(String buttonName){


            switch (buttonName.toLowerCase()) {

                case "back":
                    getPage().goBack();
                    break;

                case "forward":
                    getPage().goForward();
                    break;

                case "refresh":
                    getPage().reload();
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Unsupported browser button: " + buttonName
                    );
            }
        }
}

