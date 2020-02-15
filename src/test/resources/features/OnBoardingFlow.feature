Feature: In order to get to know about the product
  as a user
  I have to be able to walk through the on boarding tips

  Background:
    Given I navigate to the main page

  @smoke
  Scenario: Load the main page
    Then I check I can see text "Let's get this demo started"

  @positive
  Scenario: Walk through tips and check the e-mail was received by a customer
    Then I fill out work email with "esecurity@mybex.net"
    Then I press "Launch Demo" button
    Then I check I can see text "Welcome to our demo!"
    Then I press "Start the Tour" button
    Then I check on boarding tips and proceed further
      | Message subject         | Message                                                                       | element | element value             |
      | Vision Dashboard        | Check out the strengths and weaknesses of your company’s security posture.    | button  | Next                      |
      | Security Score          | The higher the score, the less risk you have of incidents.                    | button  | Next                      |
      | Behavior Map            | Which department needs your attention and who is a security superhero?        | button  | Next                      |
      | The Data                | We use data from security tools you already have via our API integrations.    | button  | Next                      |
      | Eeeek!                  | Find out which employees need the most guidance, focusing your resources.     | button  | Next                      |
      | Dive in                 | Let’s drill down into the                                                     | link    | Human Resources - Sica, C |
      | Department Level Detail | Drill-down views give you more insights into individual departments, teams    | button  | Got it                    |
      | Trends Over Time        | See how each team’s risk levels change over months.                           | button  | Next                      |
      | Security Champions      | Identify your highest performing employees and make them a champion of your   | button  | Next                      |
      | Measure Your Efforts    | Have a focus for the upcoming quarter? See how your communication & training  | button  | Next                      |
      | Personalized            | Personalized communications that motivate employees to improve their security | link    | Pulse                     |
      | Welcome to Pulse!       | Pulse empowers you to communicate directly with all your employees.           | button  | Next                      |
      | Shift security behavior | Employees and leaders receive key insights into their security performance    | button  | Explore Pulse             |
      | Drive Behavior Change   | Let’s drive behavior change, choose the employees and security behaviors most | button  | Got it                    |
    Then I check I can see text "Let’s send a sample Pulse email to your inbox - you will receive three, one for a high performer, each with different risk levels."
    Then I check if work Email is set "esecurity@mybex.net"
    Then I press "Send Pulse Email" button
    Then I check I can see text "A Pulse email is on its way!"
    Then I check I can see text "We’ve sent a sample Pulse email to:"
    Then I check that user get email with subject "Pulse Campaign from Elevate Platform Demo"

  @negative
  Scenario Outline: Attempt to set an incorrect or personal e-mail
    Then I fill out work email with "<incorrect e-mail>"
    Then I press "Launch Demo" button
    Then I check I can see text "<expected error-message>"
    Examples:
      | incorrect e-mail | expected error-message                    |
      |                  | Please enter a valid email address        |
      | my@              | Please enter a valid email address        |
      | @my.com          | Please enter a valid email address        |
      | my.com           | Please enter a valid email address        |
      | my@gmail.com     | Please enter your company’s email address |
      | my@outlook.com   | Please enter your company’s email address |
      | my@yahoo.com     | Please enter your company’s email address |
      | my@mail.ru       | Please enter your company’s email address |
