Feature: Menu Items

  Scenario Outline: Custom page creation
    Given a set of menu editor items
    When I create a new custom page with name <name>
    Then the new set of items is equal to the old set with the added custom page

    Examples:
    | name      |
    | test name |
    | test qwer |