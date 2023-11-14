@Traning
Feature: Automation cases

  Background:
    Given [COMMON] Create a web driver
    Given [COMMON] Open app 'AUTOMATION'
    Given [COMMON] maximize window

  Scenario Outline: Put login credentials and select gender
    Given [AUTOMATION][Main Page] user put name '<Name>' and last name '<LastName>'
    And [AUTOMATION][Main Page] choose gender 'male'
    Then [COMMON] Take a screenShot

    Examples:
      | Name        | LastName         |
      | RANDOM_NAME | RANDOM_LAST_NAME |
      | KACPER      | TEST             |

  Scenario: Select one option
    Given [AUTOMATION][Main Page] Select '3' option from only one option field
    Then [COMMON] Take a screenShot

  Scenario: Select several options
    Given [AUTOMATION][Main Page] Select first '2' options from multiple options
    Then [COMMON] Take a screenShot

  Scenario: Select several options but start from second
    Given [AUTOMATION][Main Page] Select first '2' options begin from '3' option from multiple options
    Then [COMMON] Take a screenShot

  Scenario: Select several applicable options
    Given [AUTOMATION][Main Page] Select option by name 'Option 2' from applicable options
    Then [COMMON] Take a screenShot