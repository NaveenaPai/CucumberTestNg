Feature: Login page feature for the e-commerce website

Scenario: Verify login page title
Given user is on the login page
When user gets the title of the page
Then page title should be "Login - My Store"


Scenario: Successful login with right credentials (registered user) & Verify
Given user is on the login page
When user enters username "qalearningspace@gmail.com"
And user enters password "Testing@123"
And user clicks on Login button
Then user gets the title of useraccount page
And useraccount page title should be "My account - My Store"