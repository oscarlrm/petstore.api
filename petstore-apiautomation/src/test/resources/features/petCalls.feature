Feature: Pet Calls

  Scenario Outline: Create Pet
    Given I want to create a pet with the following data: <id>, "<name>", <category_id>, "<category_name>", "<photoUrl>", <tag_id>, "<tag_name>", "<status>"
    Then Response should be "<statusCode>"

    Examples:
      | id  | name |category_id|category_name|photoUrl|tag_id|tag_name  |status    |statusCode|
      | 11  | Paco |     1     |Dogs         |testUrl |0     |testString|available |   200    |

  Scenario Outline: Search Pet By ID
    Given I want to search a pet by <id>
    Then Response should be "<statusCode>"
    And The pet name should be "<name>"

    Examples:
    | id  | name| statusCode|
    | 11  | Paco|200        |

  Scenario Outline: Update Pet
    Given I want to update a pet with the following data: <id>, "<name>", <category_id>, "<category_name>", "<photoUrl>", <tag_id>, "<tag_name>", "<status>"
    Then Response should be "<statusCode>"
    And The pet name should be "<name>"

    Examples:
      | id  | name  |category_id|category_name|photoUrl|tag_id|tag_name  |status    |statusCode|
      | 11  | Bruno |     1     |Dogs         |testUrl |0     |testString|available |   200    |

  Scenario Outline: Delete Pet By ID
    Given I want to delete a pet by <id>
    Then Response should be "<statusCode>"

    Examples:
      | id  | statusCode|
      | 11  |200        |
