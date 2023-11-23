Feature: Api Grocery Store

  @ApiTraning @GroceryStore
  Scenario: Check status, create client and basket for him
    Given [GROCERY][API] check api status
    Then [GROCERY][API] create new client with name 'Kacper' and email 'test1234@test.pl'
    And [GROCERY][API] create basket
    Then [GROCERY][API] get all available products

  @ApiTraning @GroceryStore
  Scenario: Test
    Then [GROCERY][API] create new client with name 'Kacper' and email 'kacper3122121423456@o2.pl'
    And [GROCERY][API] create basket
    Then [GROCERY][API] get all available products
    And [GROCERY][API] get all available products where category 'coffee'
    Then [GROCERY][API] add item with id '1225' and quantity '2' to the basket
    And [GROCERY][API] update product quantity to '1' in basket
    Then [GROCERY][API] check user basket