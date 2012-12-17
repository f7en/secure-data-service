@smoke @RALLY_US209 @RALLY_DE87
Feature: As an SLI application, I want to be able to perform CRUD operations on various resources
This means I want to be able to perform CRUD on all entities.
and verify that the correct links are made available.

Background: Nothing yet
    Given I am logged in using "rrogers" "rrogers1234" to realm "IL"
      And format "application/vnd.slc+json"



    Scenario Outline: CRUD operations requiring explicit associations on an entity as staff
    Given I am logged in using "rrogers" "rrogers1234" to realm "IL"
      And format "application/vnd.slc+json"
       Given entity URI <Entity Resource URI>
        # Create
       Given a valid entity json document for a <Entity Type>
        When I navigate to POST "/<ENTITY URI>"
        Then I should receive a return code of 201
         And I should receive a new entity URI
        # Association
        When I create an association of type <Association Type>
        When I POST the association of type <Association Type>
        Then I should receive a return code of 201
        # Read
        When I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 200
         And a valid entity json document for a <Entity Type>
         And the response should contain the appropriate fields and values
         And "entityType" should be <Entity Type>
         And I should receive a link named "self" with URI "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        # Update
        When I set the <Update Field> to <Updated Value>
         And I navigate to PUT "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 204
         And I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
         And <Update Field> should be <Updated Value>
        # Delete
        When I navigate to DELETE "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 204
         And I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
         And I should receive a return code of 404

Examples:
| Entity Type                    | Entity Resource URI   | Association Type                         | Update Field             | Updated Value             |
| "staff"                        | "staff"               | "staffEducationOrganizationAssociation2" | "sex"                    | "Female"                  |
| "teacher"                      | "teachers"            | "teacherSchoolAssociation2"              | "highlyQualifiedTeacher" | "false"                   |
| "program"                      | "programs"            | "staffProgramAssociation"                | "programSponsor"         | "State Education Agency"  |

        Scenario Outline: CRUD operations on an entity and can't update natural key
       Given entity URI <Entity Resource URI>
        # Create
       Given a valid entity json document for a <Entity Type>
        When I navigate to POST "/<ENTITY URI>"
        Then I should receive a return code of 201
         And I should receive a new entity URI
        # Read
        When I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 200
         And the response should contain the appropriate fields and values
         And "entityType" should be <Entity Type>
         And I should receive a link named "self" with URI "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        # Update
        When I set the <Update Field> to <Updated Value>
         And I navigate to PUT "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 409
        # Delete
        When I navigate to DELETE "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 204
         And I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
         And I should receive a return code of 404

Examples:
| Entity Type                    | Entity Resource URI       | Update Field             | Updated Value                                |
| "assessment"                   | "assessments"             | "assessmentTitle"        | "Advanced Placement Test - Subject: Writing" |
| "attendance"                   | "attendances"             | "studentId"              | "274f4c71-1984-4607-8c6f-0a91db2d240a_id"       |
| "gradebookEntry"               | "gradebookEntries"        | "gradebookEntryType"     | "Homework"                                   |
| "studentAcademicRecord"        | "studentAcademicRecords"  | "sessionId"              | "abcff7ae-1f01-46bc-8cc7-cf409819bbce"       |

        Scenario Outline: CRUD operations on an entity requiring explicit associations and can't update natural key
       Given entity URI <Entity Resource URI>
        # Create
       Given a valid entity json document for a <Entity Type>
        When I navigate to POST "/<ENTITY URI>"
        Then I should receive a return code of 201
         And I should receive a new entity URI
        # Optional Association
        When I create an association of type <Association Type>
        When I POST the association of type <Association Type>
        Then I should receive a return code of 201
        # Read
        When I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 200
         And a valid entity json document for a <Entity Type>
         And the response should contain the appropriate fields and values
         And "entityType" should be <Entity Type>
         And I should receive a link named "self" with URI "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        # Update
        When I set the <Update Field> to <Updated Value>
         And I navigate to PUT "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 409
        # Delete
        When I navigate to DELETE "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 204
         And I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
         And I should receive a return code of 404

Examples:
| Entity Type                    | Entity Resource URI       | Association Type            | Update Field             | Updated Value                                |
| "parent"                       | "parents"                 | "studentParentAssociation2" | "parentUniqueStateId"    | "ParentID102"                                |




    Scenario Outline: CRUD operations requiring explicit associations on an entity as an IT Admin Teacher
    Given I am logged in using "cgrayadmin" "cgray1234" to realm "IL"
      And format "application/vnd.slc+json"
       Given entity URI <Entity Resource URI>
        # Create
       Given a valid entity json document for a <Entity Type>
        When I navigate to POST "/<ENTITY URI>"
        Then I should receive a return code of 201
         And I should receive a new entity URI
        # Association
        When I create an association of type <Association Type>
        When I POST the association of type <Association Type>
        Then I should receive a return code of 201
        # Read
        When I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 200
         And a valid entity json document for a <Entity Type>
         And the response should contain the appropriate fields and values
         And "entityType" should be <Entity Type>
         And I should receive a link named "self" with URI "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        # Update
        When I set the <Update Field> to <Updated Value>
         And I navigate to PUT "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 204
         And I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
         And <Update Field> should be <Updated Value>
        # Delete
        When I navigate to DELETE "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 204
         And I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
         And I should receive a return code of 404

Examples:
| Entity Type                    | Entity Resource URI   | Association Type                       | Update Field             | Updated Value             |
| "cohort"                       | "cohorts"             | "studentCohortAssocation"              | "cohortDescription"      | "frisbee golf team"       |
| "disciplineIncident"           | "disciplineIncidents" | "studentDisciplineIncidentAssociation" | "incidentTime"           | "01:02:15"                |
| "program"                      | "programs"            | "studentProgramAssociation"            | "programSponsor"         | "State Education Agency"  |
| "section"                      | "sections"            | "studentSectionAssociation"            | "sequenceOfCourse"       | "2"                       |
| "staff"                        | "staff"               | "staffEducationOrganizationAssociation"| "sex"                    | "Female"                  |
| "student"                      | "students"            | "studentSectionAssociation2"           | "sex"                    | "Female"                  |
| "teacher"                      | "teachers"            | "teacherSchoolAssociation"             | "highlyQualifiedTeacher" | "false"                   |

# Session and course require multiple levels of associations, e.g. course -> courseOffering -> section -> teacherSectionAssoc
#| "session"                      | "sessions"            | | |  "totalInstructionalDays" | "43"                                         |
#| "course"                       | "courses"             | "courseOffering"                       | "section"         | "courseDescription"      | "Advanced Linguistic Studies" |

    Scenario Outline: CRUD operations requiring explicit associations on an entity as an IT Admin Teacher and can't update natural keys
    Given I am logged in using "cgrayadmin" "cgray1234" to realm "IL"
      And format "application/vnd.slc+json"
       Given entity URI <Entity Resource URI>
        # Create
       Given a valid entity json document for a <Entity Type>
        When I navigate to POST "/<ENTITY URI>"
        Then I should receive a return code of 201
         And I should receive a new entity URI
        # Association
        When I create an association of type <Association Type>
        When I POST the association of type <Association Type>
        Then I should receive a return code of 201
        # Read
        When I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 200
         And a valid entity json document for a <Entity Type>
         And the response should contain the appropriate fields and values
         And "entityType" should be <Entity Type>
         And I should receive a link named "self" with URI "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        # Update
        When I set the <Update Field> to <Updated Value>
         And I navigate to PUT "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 409
        # Delete
        When I navigate to DELETE "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
        Then I should receive a return code of 204
         And I navigate to GET "/<ENTITY URI>/<NEWLY CREATED ENTITY ID>"
         And I should receive a return code of 404

Examples:
| Entity Type                    | Entity Resource URI   | Association Type                       | Update Field             | Updated Value             |
| "parent"                       | "parents"             | "studentParentAssociation"             | "parentUniqueStateId"    | "ParentID102"             |

    Scenario Outline: Get All Entities as School Teacher
    
    Given I am logged in using "cgray" "cgray1234" to realm "IL"
     And format "application/vnd.slc+json"
    And my contextual access is defined by table:
    | Context                | Ids                                                                          |
    | schools	             | 92d6d5a0-852c-45f4-907a-912752831772,6756e2b9-aba1-4336-80b8-4a5dde3c63fe    |
    | educationOrganizations | 92d6d5a0-852c-45f4-907a-912752831772,6756e2b9-aba1-4336-80b8-4a5dde3c63fe    |
    | staff	                 | e9ca4497-e1e5-4fc4-ac7b-24bad1f2998b                                         |
    | teachers               | e9ca4497-e1e5-4fc4-ac7b-24bad1f2998b                                         |
    | sections               | 15ab6363-5509-470c-8b59-4f289c224107_id,47b5adbf-6fd0-4f07-ba5e-39612da2e234_id |
    Given entity URI <Entity Resource URI>
    #TODO BUG for the 6 entities routed to ES, revert back to "0" when bug is fixed
    Given parameter "limit" is "250"
     When I navigate to GET "/<ENTITY URI>"
     Then I should receive a return code of 200
      And I should receive a collection of "<Count>" entities
      And each entity's "entityType" should be <Entity Type>
      And uri was rewritten to "<Rewrite URI>"

Examples:
| Entity Type             | Entity Resource URI       | Count | Rewrite URI|
| "assessment"            | "assessments"             | 17    |/search/assessments|                                                                            
| "attendance"            | "attendances"             | 3     |/sections/@ids/studentSectionAssociations/students/attendances|
| "cohort"                | "cohorts"                 | 4     |/staff/@ids/staffCohortAssociations/cohorts|
| "course"                | "courses"                 | 27    |/schools/@ids/courses|
| "disciplineAction"      | "disciplineActions"       | 0     |/staff/@ids/disciplineActions|                                                           
| "disciplineIncident"    | "disciplineIncidents"     | 0     |/staff/@ids/disciplineIncidents|                                                         
| "school"                | "educationOrganizations"  | 2     |/teachers/@ids/teacherSchoolAssociations/schools|              
| "gradebookEntry"        | "gradebookEntries"        | 1     |/sections/@ids/gradebookEntries|
| "learningObjective"     | "learningObjectives"      | 5     |/search/learningObjectives|                                                                     
| "learningStandard"      | "learningStandards"       | 14    |/search/learningStandards|                                                                      
| "parent"                | "parents"                 | 2     |/sections/@ids/studentSectionAssociations/students/studentParentAssociations/parents|
| "program"               | "programs"                | 1     |/staff/@ids/staffProgramAssociations/programs|                                           
| "school"                | "schools"                 | 2     |/teachers/@ids/teacherSchoolAssociations/schools|                                                                              
| "section"               | "sections"                | 2     |/teachers/@ids/teacherSectionAssociations/sections|                                                                  
| "session"               | "sessions"                | 6     |/educationOrganizations/@ids/sessions|                                                                  
| "staff"                 | "staff"                   | 3     |/educationOrganizations/@ids/staffEducationOrgAssignmentAssociations/staff|              
| "student"               | "students"                | 25    |/sections/@ids/studentSectionAssociations/students|                                        
| "studentAcademicRecord" | "studentAcademicRecords"  | 2     |/sections/@ids/studentSectionAssociations/students/studentAcademicRecords|                 
| "studentGradebookEntry" | "studentGradebookEntries" | 1     |/sections/@ids/studentSectionAssociations/students/studentGradebookEntries|                
| "teacher"               | "teachers"                | 3     |/schools/@ids/teacherSchoolAssociations/teachers|                                        
| "grade"                 | "grades"                  | 1     |/sections/@ids/studentSectionAssociations/grades|
| "studentCompetency"     | "studentCompetencies"     | 2     |/sections/@ids/studentSectionAssociations/studentCompetencies|
| "gradingPeriod"         | "gradingPeriods"          | 2     |/schools/@ids/sessions/gradingPeriods|                                                   
| "reportCard"            | "reportCards"             | 3     |/sections/@ids/studentSectionAssociations/students/reportCards|
| "studentCompetencyObjective" | "studentCompetencyObjectives" | 0 |/search/studentCompetencyObjectives    |

	@DE1825
	Scenario: Invalid data parsing fails gracefully
		When I navigate to GET "/v1/staffEducationOrgAssignmentAssociations?endDate=blah"
    	Then I should receive a return code of 400
        When I create an association of type "studentSectionAssociation"
    	And field "beginDate" is removed from the json document
    	When I navigate to POST "/v1/studentSectionAssociations"
    	Then I should receive a return code of 400
