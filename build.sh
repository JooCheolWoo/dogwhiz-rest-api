#!/bin/bash
APP_NAME="dogwhiz-rest-api"

cd /home/galaxypoby/${APP_NAME}
git reset
git checkout .
git clean -fdx
git pull

# execute docker build
/bin/bash build-docker.sh



