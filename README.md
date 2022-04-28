#Build Status

![workflow](https://github.com/Nathan03nable/sem/actions/workflows/main.yml/badge.svg)
[![LICENSE](https://img.shields.io/github/license/Nathan03nable/sem.svg?style=flat-square)](https://github.com/Nathan03nable/sem/blob/master/LICENSE)
[![Releases](https://img.shields.io/github/release/Nathan03nable/sem/all.svg?style=flat-square)](https://github.com/Nathan03nable/sem/releases)
![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/Nathan03nable/sem/A%20workflow%20for%20my%20Hello%20World%20App)


#How to build
- Remove all docker containers and images (Serviced>Containers/Images select all and delete) 
- Run `mvn clean package` or alternatively select the maven toolbar on the right hand side and double click clean, then double click package
- Locate the `docker-compose.yml` in the root of the project
- Select run all
- Connect to database through selecting 'Docker-compose: sem', expand 'app' and run '/sem_app_1' 


#Requirements

| ID  |                                                                            Name                                                                             | Met |                                                                                                                                                                                                                                                                                                                          Screenshot |
|-----|:-----------------------------------------------------------------------------------------------------------------------------------------------------------:|----:|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| 1   |                                         All the countries in the world organised by largest population to smallest.                                         | Yes |                             ![img.png](screenshots/Report 1/img.png)![img.png](screenshots/Report 1/img_1.png)![img.png](screenshots/Report 1/img_2.png)![img.png](screenshots/Report 1/img_3.png) ![img.png](screenshots/Report 1/img_4.png) ![img.png](screenshots/Report 1/img_5.png) ![img.png](screenshots/Report 1/img_6.png) |
| 2   |                                        All the countries in a continent organised by largest population to smallest.                                        | Yes |                                                                                ![img.png](screenshots/Report 2/img.png)                                                                                                                                                                                                           ! |
| 3   |                                         All the countries in a region organised by largest population to smallest.                                          | Yes |                                                                                                                                                                                                                                                                                            ![img.png](screenshots/Report 3/img.png) |
| 4   |                                         The top N populated countries in the world where N is provided by the user.                                         | Yes |                                                                                                                                                                                                                                                                                            ![img.png](screenshots/Report 4/img.png) |
| 5   |                                        The top N populated countries in a continent where N is provided by the user                                         | Yes |                                                                                                                                                                                                                                                                                            ![img.png](screenshots/Report 5/img.png) |
| 6   |                                         The top N populated countries in a region where N is provided by the user.                                          | Yes |                                                                                                                                                                                                                                                                                            ![img.png](screenshots/Report 6/img.png) |
| 7   |                                          All the cities in the world organised by largest population to smallest.                                           | Yes | (took screenshot of top of query, middle and end)                                                                                                            ![img.png](screenshots/Report 7/img.png)![img.png](screenshots/Report 7/img_1.png)![img.png](screenshots/Report 7/img_2.png)![img.png](screenshots/Report 7/img_3.png) |
| 8   |                                         All the cities in a continent organised by largest population to smallest.                                          | Yes |                                                                                                                                                                                                                                                  ![img.png](screenshots/Report 8/img.png)![img.png](screenshots/Report 8/img_1.png) |
| 9   |                                           All the cities in a region organised by largest population to smallest.                                           | Yes |                                                                                                                                                                                                                                       ![img.png](screenshots/Report 9/Report 9.png) ![img.png](screenshots/Report 9/Report 9.2.png) |
| 10  |                                          All the cities in a country organised by largest population to smallest.                                           | Yes |                                                                                                                                                                                                                                                                                   ![img.png](screenshots/Report 10/Report 10.2.png) |
| 11  |                                          All the cities in a district organised by largest population to smallest.                                          | Yes |                                                                                                                                                                                                                                                                                     ![img.png](screenshots/Report 11/Report 11.png) |
| 12  |                                          The top N populated cities in the world where N is provided by the user.                                           | Yes |                                                                                                                                                                                                                                                                                     ![img.png](screenshots/Report 12/Report 12.png) |
| 13  |                                         The top N populated cities in a continent where N is provided by the user.                                          | Yes |                                                                                                                                                                                                                                                                                     ![img.png](screenshots/Report 13/Report 13.png) |
| 14  |                                           The top N populated cities in a region where N is provided by the user.                                           | Yes |                                                                                                                                                                                                                                                                                     ![img.png](screenshots/Report 14/Report 14.png) |
| 15  |                                          The top N populated cities in a country where N is provided by the user.                                           | Yes |                                                                                                                                                                                                                                                                                     ![img.png](screenshots/Report 15/Report 15.png) |
| 16  |                                          The top N populated cities in a district where N is provided by the user.                                          | Yes |                                                                                                                                                                                                                                                                                     ![img.png](screenshots/Report 16/Report 16.png) |
| 17  |                                      All the capital cities in the world organised by largest population to smallest.                                       | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 18  |                                     All the capital cities in a continent organised by largest population to smallest.                                      | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 19  |                                            All the capital cities in a region organised by largest to smallest.                                             | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 20  |                                      The top N populated capital cities in the world where N is provided by the user.                                       | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 21  |                                     The top N populated capital cities in a continent where N is provided by the user.                                      | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 22  |                                       The top N populated capital cities in a region where N is provided by the user.                                       | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 23  |                            The population of people, people living in cities, and people not living in cities in each continent.                            | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 24  |                             The population of people, people living in cities, and people not living in cities in each region.                              | Yes |                                                                                                                                                                                                                                                                                                                                     |
| 25  |                             The population of people, people living in cities, and people not living in cities in each country.                             | Yes |                                                                  ![img.png](screenshots/Report 25/img.png)![img.png](screenshots/Report 25/img_1.png)![img.png](screenshots/Report 25/img_2.png)![img.png](screenshots/Report 25/img_3.png) ![img.png](screenshots/Report 25/img_4.png) ![img.png](screenshots/Report 25/img_5.png) |
| 26  |                                                                The population of the world.                                                                 | Yes |                                                                                                                                                                                                                                                                                           ![img.png](screenshots/Report 26/img.png) |
| 27  |                                                               The population of a continent.                                                                | Yes |                                                                                                                                                                                                                                                                                           ![img.png](screenshots/Report 27/img.png) |
| 28  |                                                                 The population of a region.                                                                 | Yes |                                                                                                                                                                                                                                                                                           ![img.png](screenshots/Report 28/img.png) |
| 29  |                                                                The population of a country.                                                                 | Yes |                                                                                                                                                                                                                                                                                           ![img.png](screenshots/Report 29/img.png) |
| 30  |                                                                The population of a district.                                                                | Yes |                                                                                                                                                                                                                                                                                           ![img.png](screenshots/Report 30/img.png) |
| 31  |                                                                  The population of a city.                                                                  | Yes |                                                                                                                                                                                                                                                                                           ![img.png](screenshots/Report 31/img.png) |
| 32  | The number of people who speak chinese, english, hindi, spanish, arabic from greatest number to smallest, including the percentage of the world population. | Yes |                                                                                                                                                                                                                                                                                           ![img.png](screenshots/Report 32/img.png) |