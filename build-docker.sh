#!/bin/bash

# Get the profile from command line argument
PROFILE=$1

APP_NAME="dogwhiz-rest-api-${PROFILE}"
APP_NAME_OLD="${APP_NAME}-old"
server_version="0.0.1"

source ./yaml.sh

function rename_current_docker_container() {
    echo "---------- [Deploy Step - 1] : Rename Current Docker Container"
    docker rename ${APP_NAME} ${APP_NAME_OLD}
}

function rename_current_docker_image() {
    echo "---------- [Deploy Step - 2] : Rename Current Docker Image"
    docker tag ${APP_NAME}:${server_version} ${APP_NAME_OLD}:${server_version}
}

function gradle_build() {
    echo "---------- [Deploy Step - 3] : Gradle Build"
    sh gradlew build -x test
}

function build_docker_image() {
    echo "---------- [Deploy Step - 4] : Build New Docker Image"
    docker build --build-arg PROFILE=${PROFILE} -t ${APP_NAME}:${server_version} .
}

function stop_old_docker_container() {
    echo "---------- [Deploy Step - 5] : Stop Old Docker Container"
    if [ "$(docker ps -aq -f name=${APP_NAME_OLD})" ]; then
        docker stop ${APP_NAME_OLD}
    fi
}

function remove_old_docker_container() {
    echo "---------- [Deploy Step - 6] : Remove Old Docker Container"
    if [ "$(docker ps -aq -f name=${APP_NAME_OLD})" ]; then
        docker rm ${APP_NAME_OLD}
    fi
}

function remove_old_docker_image() {
    echo "---------- [Deploy Step - 7] : Remove Old Docker Image"
    docker rmi ${APP_NAME_OLD}:${server_version} || true
}

function run_new_docker_container() {
    echo "---------- [Deploy Step - 8] : Run New Docker Container"
    if [ "$PROFILE" == "prod" ]; then
        DOMAIN="api.hellodogwhiz.com"
    else
        DOMAIN="${PROFILE}.api.hellodogwhiz.com"
    fi
    docker run -d \
        -e VIRTUAL_HOST=${DOMAIN} \
        -e LETSENCRYPT_HOST=${DOMAIN} \
        -e LETSENCRYPT_EMAIL=tkfkdal@naver.com \
        -e TZ=Asia/Seoul \
        -e PROFILE=${PROFILE} \
        -v /etc/localtime:/etc/localtime:ro \
        --network nginx-proxy \
        --restart unless-stopped \
        --name ${APP_NAME} \
        -v /home/galaxypoby/storage/dogwhiz-${PROFILE}:/home \
        ${APP_NAME}:${PROFILE}-${server_version}
}


function show_docker_logs() {
    docker logs -f ${APP_NAME}
}

functions=(rename_current_docker_container rename_current_docker_image gradle_build build_docker_image stop_old_docker_container remove_old_docker_container remove_old_docker_image run_new_docker_container show_docker_logs)

for function in "${functions[@]}"
do
    $function
done
