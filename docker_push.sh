#!/bin/bash
docker login -u "$DOCKER_USER" -p "$DOCKER_PASS";
./gradlew dockerPush
