#!/bin/bash

git reset
git checkout .
git clean -fdx
git pull

# execute docker build
/bin/bash build-docker.sh



