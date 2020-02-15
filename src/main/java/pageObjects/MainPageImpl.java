package pageObjects;

import Selenium.SeleniumLibrary;
import cucumber.api.DataTable;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import stepDefinitions.GlobalSteps;
import utilities.GetProperties;
import utilities.Gmail.Email;

import java.util.List;
import java.util.Map;


public class MainPageImpl extends SeleniumLibrary {

    /**
     * Returns a SoftAssertions object to check things that you care about, but don't want to stop test
     * execution over if the assertion fails.
     *
     * @return The static SoftAssertions object from stepDefinitions.GlobalSteps to be checked on Scenario teardown.
     */
    public SoftAssertions softly() {
        return GlobalSteps.softly;
    }

    private static Email emailUtils;

    // Jo, this definitely has to be refactored! Only properties, I am a bit timeboxed, talk to you on a review
    public static void connectToEmail() {
        try {
            emailUtils = new Email("esecurity@mybex.net", "P@ssw0rd", "smtp.gmail.com", Email.EmailFolder.INBOX);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public void navigateMainPage() {
        getDriver().navigate().to(GetProperties.get("env.url"));
    }

    public void checkOnboardingPopups(DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (int i = 0; i < list.size(); i++) {
            softly().assertThat(text(list.get(i).get("Message subject")).isDisplayed()).as("Tips head").isTrue();
           softly().assertThat(text(list.get(i).get("Message")).isDisplayed()).as("Tips message").isTrue();
            if (list.get(i).get("element").toLowerCase().contains("button")) {
                button(list.get(i).get("element value")).click();
            } else
                link(list.get(i).get("element value")).click();
        }
    }

    public void setWorkEmail(String emailAddress){
        textbox(By.cssSelector("input")).sendKeys(emailAddress);
    }

    public void checkWorkEmail(String emailAddress){
       softly().assertThat(textbox(By.cssSelector("input")).getAttribute("value"))
               .as("Assert email is  set correctly")
               .isEqualTo(emailAddress);
    }

    public void pressButton(String button) {
        waitUntilPageFinishLoading();
        try {
            button(button).click();
        } catch (Exception e) {
            try {
                button(By.cssSelector(button)).click();
            } catch (Exception noElementFound) {
                System.out.println("Button was not found ");
                noElementFound.printStackTrace();
            }
        }
    }


    public void fillTextbox(String textField, String text) {
        try {
            textbox(textField).sendKeys(text);
        } catch (Exception e) {
            textbox(By.cssSelector(textField)).sendKeys(text);
        }
    }

    public void checkTextbox(String textField, String text) {
        Assert.assertTrue("Text field doesn't contain data", !textbox(textField).getText().equals(text));
    }

    public void clickTheLink(String link) {
        link(link).click();
    }

    public void checkCurrecntURL(String url) {
        System.getProperties();
        Assert.assertTrue("URL is wrong ", driver.getCurrentUrl().equals(GetProperties.get("env.url") + url));
    }

    public void checkEmailSubject(String emailSubject){
        connectToEmail();
        try  {
            String latestEmailSubject = emailUtils.getLatestMessage().getSubject();
            Assert.assertTrue(latestEmailSubject.equalsIgnoreCase(emailSubject));
            Assert.assertTrue(emailUtils.getMessagesBySubject(latestEmailSubject, true, 1).length != 0);
        }
        catch (Exception e){
          softly().assertThat(true).as("the emai letter was not found").isFalse();
        }
    }

    public void checkTextIsVisible(String text) {
        Assert.assertTrue("Text should be displayed: " + text, text(text).isDisplayed());
    }
}



