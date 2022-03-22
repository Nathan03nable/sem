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