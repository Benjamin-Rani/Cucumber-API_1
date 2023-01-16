@tag
Feature: To Verify batch id --- API automation with Rest Assured

  @tag1
  Scenario Outline: Update Batch by ID
    Given A update Service with URL and path "<BatchID>"
    When "<BatchID>","<batchDescription>","<batchStatus>","<batchNoOfClasses>","<programId>","<programName>",creationTime,lastModTime are updated
    And PUT request is made
    Then Save batchId
    And Validate status code
    And Validate required fields "<BatchID>","<batchDescription>","<batchStatus>"

    Examples: 
      | BatchID | batchDescription      | batchStatus | batchNoOfClasses | programId | programName |
      |    1255 | API Training  2023 | Active      | 184 | 1255 | Program-2023051236583 |
