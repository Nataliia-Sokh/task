package stepDefinitions;

import cucumber.api.java8.En;
import pageObjects.MainPageImpl;


public class MainPageSampleStepsDefinition implements En {

    public MainPageSampleStepsDefinition() {

        MainPageImpl mainPage = new MainPageImpl();

        When("^I navigate to the main page$", mainPage::navigateMainPage);
        Then("^I press \"([^\"]*)\" button$", mainPage::pressButton);
        Then("^I check I can see text \"([^\"]*)\"$", mainPage::checkTextIsVisible);
        Then("^I fill out \"([^\"]*)\" with \"([^\"]*)\"$", mainPage::fillTextbox);
        Then("^I check the \"([^\"]*)\" text field has the \"([^\"]*)\" value$", mainPage::checkTextbox);
        Then("^I click  the \"([^\"]*)\" link$", mainPage::clickTheLink);
        Then("^I check if url is \"([^\"]*)\"$", mainPage::checkCurrecntURL);
        Then("^I check on boarding tips and proceed further$", mainPage::checkOnboardingPopups);
        Then("^I fill out work email with \"([^\"]*)\"$", mainPage::setWorkEmail);
        Then("^I check if work Email is set \"([^\"]*)\"$", mainPage::checkWorkEmail);
        Then("^I check that user get email with subject \"([^\"]*)\"$", mainPage::checkEmailSubject);
    }
}
