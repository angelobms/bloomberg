#!/bin/bash

echo "Creating eureka-server docker image..."
cd eureka-server
./gradlew docker
echo "eureka-server docker image created."

echo "Creating calculator-api docker image..."
cd ..
cd calculator-api
./gradlew docker
echo "calculator-api docker image created."

echo "Creating math-expression docker image..."
cd ..
cd math-expression
./gradlew docker
echo "math-expression docker image created."


docker-compose up