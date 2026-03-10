package com.agilic.runner;

import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberSerenityRunner;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.agilic.stepdefinitions", "com.agilic.hooks"},
        tags="@reporting"
        //tags="@security"
        //tags="@login or @signout or @security or @links"
        //tags="@smoke"
)
public class CucumberTest {
}