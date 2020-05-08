$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("bdd/items.feature");
formatter.feature({
  "line": 1,
  "name": "Menu Items",
  "description": "",
  "id": "menu-items",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "Custom page creation",
  "description": "",
  "id": "menu-items;custom-page-creation",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of menu editor items",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new custom page with name \u003cname\u003e",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of items is equal to the old set with the added custom page",
  "keyword": "Then "
});
formatter.examples({
  "line": 8,
  "name": "",
  "description": "",
  "id": "menu-items;custom-page-creation;",
  "rows": [
    {
      "cells": [
        "name"
      ],
      "line": 9,
      "id": "menu-items;custom-page-creation;;1"
    },
    {
      "cells": [
        "test name"
      ],
      "line": 10,
      "id": "menu-items;custom-page-creation;;2"
    },
    {
      "cells": [
        "test qwer"
      ],
      "line": 11,
      "id": "menu-items;custom-page-creation;;3"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 11511626300,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Custom page creation",
  "description": "",
  "id": "menu-items;custom-page-creation;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of menu editor items",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new custom page with name test name",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of items is equal to the old set with the added custom page",
  "keyword": "Then "
});
formatter.match({
  "location": "ItemsStepDefinitions.loadItems()"
});
formatter.result({
  "duration": 7330668400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test name",
      "offset": 37
    }
  ],
  "location": "ItemsStepDefinitions.createCustomPage(String)"
});
formatter.result({
  "duration": 9648817200,
  "status": "passed"
});
formatter.match({
  "location": "ItemsStepDefinitions.verifyCustomPageCreated()"
});
formatter.result({
  "duration": 2809701300,
  "status": "passed"
});
formatter.after({
  "duration": 926458300,
  "status": "passed"
});
formatter.before({
  "duration": 10922994900,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Custom page creation",
  "description": "",
  "id": "menu-items;custom-page-creation;;3",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "a set of menu editor items",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "I create a new custom page with name test qwer",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "the new set of items is equal to the old set with the added custom page",
  "keyword": "Then "
});
formatter.match({
  "location": "ItemsStepDefinitions.loadItems()"
});
formatter.result({
  "duration": 7033828400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "test qwer",
      "offset": 37
    }
  ],
  "location": "ItemsStepDefinitions.createCustomPage(String)"
});
formatter.result({
  "duration": 9388069200,
  "status": "passed"
});
formatter.match({
  "location": "ItemsStepDefinitions.verifyCustomPageCreated()"
});
formatter.result({
  "duration": 2819066700,
  "status": "passed"
});
formatter.after({
  "duration": 951026600,
  "status": "passed"
});
});