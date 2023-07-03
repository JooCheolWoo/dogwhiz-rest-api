#!/bin/bash
APP_NAME="dogwhiz-rest-api"

cd ~/projects/${APP_NAME}
git reset
git checkout .
git clean -fdx
git pull

# execute docker build
/bin/bash build-docker.sh



