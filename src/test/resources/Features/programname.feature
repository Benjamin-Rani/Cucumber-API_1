@tag
Feature:To To Verify batch id --- API automation with Rest Assured
Scenario Outline: Update program by programName
    Given  V1 A update Service with URL and path "<programName>"
    When "<programId>","<programName>","<programDescription>","<programStatus>",creationTime,lastModTime are updated
    And V1 PUT request is made 
    Then  V1 Save programName
    And  V1 Validate status code
    And V1 validate required fields "<programId>","<programName>","<programDescription>","<programStatus>"

 
  
    Examples: 
      | programId | programName | programDescription 		 | programStatus |
      | 8471 | SeleniumAutomation| Automation |Active |
 
