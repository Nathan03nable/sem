**USE CASE: 2 Produce a report on cities and their population**

**CHARACTERISTIC INFORMATION**

**Goal in Context**

As a user I want to retrieve reports on cities and their populations so that I can support the organisation’s business goals.

**Scope**

Designing and implementing a new system to allow easy access to this population information.

**Level**

Primary task.

**Preconditions**

Database contains cities and their population data.

**Success End Condition**

A report is available for the organisation.

**Failed End Condition**

No report is produced.

**Primary Actor**

User.

**Trigger**

A request for population information is sent by the user.

**MAIN SUCCESS SCENARIO**

1.Organisation request reporting on population information.

2.User extracts all the cities in the world organised by largest population to smallest.

3.User extracts all the cities in a continent organised by largest population to smallest.

4.User extracts all the cities in a region organised by largest population to smallest.

5.User extracts the top N populated cities in the world where N is provided by the user.

6.User extracts the top N populated cities in a continent where N is provided by the user.

7.User extracts the top N populated cities in a region where N is provided by the user.

8.User extracts the top N populated cities in a country where N is provided by the user.

9.User extracts the top N populated cities in a district where N is provided by the user.

10.User provides report to organisation.

**EXTENSIONS**

5. 6, 7, 8, 9 User provides a negative number when querying the top number populated countries

**SUB-VARIATIONS**

None.

**SCHEDULE**

**DUE DATE:** Release 1.0