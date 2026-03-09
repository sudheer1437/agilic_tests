package com.agilic.steps;

import com.agilic.pages.SummaryPage;
import io.cucumber.datatable.DataTable;
import net.serenitybdd.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;


public class SummarySteps extends ScenarioSteps {


    @Steps
    SummaryPage summaryPage;

    public void waitTillUserDirectedToSummaryPage(){
        summaryPage.waitTillSummaryPageLoaded();
        summaryPage.verifyPageStructure();
        summaryPage.verifyUserSession();
    }

    public void clickSignOut(){
        summaryPage.clickOnSignOutButton();
    }

    public void verifySummaryPageCardsAndTabs(List<Map<String,String>> list,String type){
        for(Map<String, String> nameMap : list){
            String cardName=nameMap.get("Field Name");
            if(type.equalsIgnoreCase("cards"))
                assertTrue(summaryPage.checkForCard(cardName));
            if(type.equalsIgnoreCase("tabs"))
                assertTrue(summaryPage.checkForTab(cardName));
        }
    }

    public void clickListsView(){
        summaryPage.clickOnElement("summaryLists");
    }


}
