#!/bin/bash
APP_NAME="dogwhiz-rest-api"

cd /home/galaxypoby/projects/${APP_NAME}
git reset
git checkout .
git clean -fdx
git pull

PROFILE=$1

# execute docker build
/bin/bash build-docker.sh $PROFILE



