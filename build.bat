docker-compose down

docker rmi sem_app
docker rmi sem_db

mvn clean package

docker-compose up