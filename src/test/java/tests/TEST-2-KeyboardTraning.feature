@Traning
Feature: Keyboard Traning

  Background:
    Given [COMMON] Create a web driver
    Given [COMMON] Open app 'GOOGLE'
    Given [COMMON] maximize window

  @SendKeys
  Scenario: Write text and click search
    Then [GOOGLE][Main Page] Accept terms if displayed
    When [GOOGLE][Main Page] Page is displayed
    And [GOOGLE][Main Page] Send text 'Test' with Shift
    Then [GOOGLE][Main Page] Click Search button
    Then [COMMON] Take a screenShot