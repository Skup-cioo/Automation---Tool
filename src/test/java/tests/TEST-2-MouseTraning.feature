@Traning
Feature: Mouse Traning

  Background:
    Given [COMMON] Create a web driver
    Given [COMMON] Open app 'MOUSE'
    Given [COMMON] maximize window

  @Mouse @DragTraning
  Scenario: Drag and drop element
    Given [MOUSE][Main Page] Page is displayed
    Then [MOUSE][Main Page] Click ok popUp
    And [MOUSE][Main Page] Click element with text 'Drag and Drop'
    Then [MOUSE][Others] Drag and drop element
    And [COMMON] Take a screenShot

  @Mouse @DragTraning
  Scenario Outline: Drag element to position
    Given [MOUSE][Main Page] Page is displayed
    Then [MOUSE][Main Page] Click ok popUp
    And [MOUSE][Main Page] Click element with text 'Drag me'
    Then [MOUSE][Others] Drag and drop element to position: X = '<x>' Y = '<y>'
    And [COMMON] Take a screenShot
    Examples:
      | x   | y   |
      | 50  | 40  |
      | 300 | 100 |

  @Mouse @ClickTraning
  Scenario Outline: Click and Hold
    Given [MOUSE][Main Page] Page is displayed
    Then [MOUSE][Main Page] Click ok popUp
    And [MOUSE][Main Page] Click element with text 'Click and Hold in Selenium'
    Then [MOUSE][Others] Replace the letter '<firstLetter>' with the letter '<secondLetter>'
    And [COMMON] Take a screenShot
    Examples:
      | firstLetter | secondLetter |
      | A           | C            |
