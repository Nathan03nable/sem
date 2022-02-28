**USE CASE: 4 Produce a report on population statistics**

**CHARACTERISTIC INFORMATION**

**Goal in Context**

As a user I want to retrieve reports on capital cities and their populations so that I can support the organisation’s business goals
**Scope**

Designing and implementing a new system to allow easy access to the capital cities statistics.

**Level**

Primary task.

**Preconditions**

Database contains capital cities statistics.

**Success End Condition**

A report is available for the organisation.

**Failed End Condition**

No report is produced.

**Primary Actor**

User.

**Trigger**

A request for capital cities information is sent by the user.

**MAIN SUCCESS SCENARIO**

1. Organisation requests reporting on capital cities. 

2. User extracts information for all capital cities in a continent organised by largest population to smallest.

3. User extracts information for all the capital cities in a region organised by largest to smallest.

4. User extracts information for the top N populated capital cities in the world where N is provided by the user.

5. User extracts information for the top N populated capital cities in a continent where N is provided by the user.

6. User extracts information for the top N populated capital cities in a region where N is provided by the user.

7.User provides report to organisation.


**EXTENSIONS**

4, 5, 6 User provides a negative number when querying the top number populated capital cities

**SUB-VARIATIONS**

None.

**SCHEDULE**

**DUE DATE:** Release 1.0