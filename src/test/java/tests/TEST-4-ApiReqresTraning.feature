@Traning
Feature: API Regres

  @ApiUser
  Scenario: Verify User's name by ID
    Given [REQRES][API] get '2' user list page
    Then [REQRES][API] check if user with id '2' has first name 'Janet'
    And [REQRES][API] verify if user with id '23' does not exist

  @ApiUser
  Scenario: Create new user and verify if exist
    Given [REQRES][API] create new user with name 'Kacper' and job 'Programmer'
    Then [REQRES][API] update user's name 'Test' and job 'Tester' for user id '2'
    And [REQRES][API] register new account with email 'eve.holt@reqres.in' and password 'password'

  @ApiUser
  Scenario: Create new user and update his data
    Given [REQRES][API] create new user with name 'Kacper' and job 'Programmer'
    Then [REQRES][API] update user's name 'Hugo' and job 'Scrum Master' for recently created user
    And [REQRES][API] register new account with email 'eve.holt@reqres.in' and password 'password'
    And [REQRES][API] verify if recently created user exist
