@Traning
Feature: Automation cases part 2

  Background:
    Given [COMMON] Create a web driver
    Given [COMMON] Open app 'AUTOMATION'
    Given [COMMON] maximize window

  Scenario: Move slider
    Given [AUTOMATION][Main Page] user move slider to value '0.75'
    Then [COMMON] Take a screenShot

  Scenario Outline: Fill full form and submit
    Given [AUTOMATION][Main Page] user put name '<Name>' and last name '<LastName>'
    And [AUTOMATION][Main Page] choose gender 'male'
    Then [AUTOMATION][Main Page] Select '3' option from only one option field
    And [AUTOMATION][Main Page] Select first '2' options begin from '3' option from multiple options
    Then [AUTOMATION][Main Page] Select option by name 'Option 2' from applicable options
    Then [COMMON] Take a screenShot
    Then [AUTOMATION][Main Page] user click Submit button

    Examples:
      | Name        | LastName         |
      | RANDOM_NAME | RANDOM_LAST_NAME |
      | KACPER      | TEST             |