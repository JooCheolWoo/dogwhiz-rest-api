#!/bin/bash

git reset
git checkout .
git clean -fdx
git pull

# Get the profile from command line argument
PROFILE=$1

# execute docker build
/bin/bash build-docker.sh $PROFILE
