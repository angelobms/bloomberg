#!/bin/bash

echo "Creating eureka-server docker image..."
cd eureka-server
./gradlew docker
rm -rf build/
echo "eureka-server docker image created."

echo "Creating calculator-api docker image..."
cd ..
cd calculator-api
./gradlew docker
rm -rf build/
echo "calculator-api docker image created."

echo "Creating math-expression docker image..."
cd ..
cd math-expression
./gradlew docker
rm -rf build/
echo "math-expression docker image created."

echo "Running the docker images..."
docker-compose up