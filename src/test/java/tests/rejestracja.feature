Feature: Rejest
  Background:
    Given [COMMON] Create a web driver
    Given [COMMON] Open app 'o2'

  Scenario: Rejestracja
    Given [COMMON] maximize window
    Then [o2] user put name 'RANDOM_NAME' and last name 'RANDOM_LAST_NAME'
    Then [o2] user put login
    Given [o2] Select '1' option from only one option field
    And [COMMON] Take a screenShot
    Given [o2] wypelniamy date
    And [COMMON] Take a screenShot
    Given [o2] Kliknij dalej
    When [o2] Page is displayed
    Then [o2] uzytkownik Wpisuje hasla
    Given [o2] Kliknij dalej
    And [COMMON] Take a screenShot









