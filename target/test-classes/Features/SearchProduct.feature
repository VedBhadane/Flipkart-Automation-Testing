Feature: Flipkart User Journey Tests

  Scenario: 1. Search iPhone, Sort Price, and Select First Result
    Given I open the Flipkart Application
    When I search for "iPhone 15"
    And I sort the results by "Price -- Low to High"
    And I click on the first search result
    Then I switch to the new tab and verify the product details

  Scenario: 2. Add a Mobile to Cart
    Given I open the Flipkart Application
    When I search for "Mobiles"
    And I click on the first search result
    And I switch to the new tab
    And I click on Add to Cart button
    Then I should see the product in the cart

  Scenario: 3. Verify Login Functionality
    Given I open the Flipkart Application
    When I click Login and enter mobile number "9999999999"
    Then I should see the OTP verification step