#!/bin/bash
APP_NAME="dogwhiz-rest-api"

PROFILE=$1

cd /home/galaxypoby/projects/${APP_NAME}
git reset
git checkout .
git clean -fdx

if [ "$PROFILE" = "dev" ]; then
    echo "git pull from origin dev"
    git pull origin dev
elif [ "$PROFILE" = "prod" ]; then
    echo "git pull from origin main"
    git pull origin main
else
    echo "Invalid PROFILE: $PROFILE. Please use 'dev' or 'prod'."
    exit 1
fi


# execute docker build
/bin/bash build-docker.sh $PROFILE

