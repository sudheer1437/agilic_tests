package com.agilic.runner;

import io.cucumber.java.an.Cuan;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberSerenityRunner;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"com.agilic.stepdefinitions"},
        tags="@search"
)
public class CucumberTestRunner {
}
