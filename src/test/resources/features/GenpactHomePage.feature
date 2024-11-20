Feature: Validating Helpmate flows for Genpact Home page
   

  @Regression 
  Scenario: Searching for HelpMate on Genpact Home page
    Given I navigate to application URL
    Then I verified title "Genpact - Production - Sign In" on Login Page after launched the URL
    When I type username as "defined in config" in username filed on Login page
    And I type password as "defined in config" in password filed on Login page
    And I click Login button on Login page
    And I wait for 15 seconds
    Then I verified title "GSocial" on Login Page after launched the URL
    And I enter "HelpMate" in search text box on Homepage
    And I wait for 10 seconds
    And I click Logout button from the application
    
 
