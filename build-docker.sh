#!/bin/bash

PROFILE=$1

echo "---------- [Active profile] : $PROFILE"

APP_NAME="dogwhiz-rest-api-$PROFILE"
APP_NAME_OLD="${APP_NAME}-old"
server_version_old="0.0.1"
server_version="0.0.1"

source ./yaml.sh

# 1. Change the current docker container name to old
echo "---------- [Deploy Step - 1] : Rename Current Docker Container"
docker rename $APP_NAME $APP_NAME_OLD
# 2. Change the current docker image name to old
echo "---------- [Deploy Step - 2] : Rename Current Docker Image"
docker tag $APP_NAME:$server_version_old $APP_NAME_OLD:$server_version_old
# 3. Build the jar using gradle
echo "---------- [Deploy Step - 3] : Gradle Build"
sh gradlew build -x test
# 4. Build the docker image
echo "---------- [Deploy Step - 4] : Build New Docker Image"
docker build -t $APP_NAME:$server_version .
# 5. Stop the old docker container
echo "---------- [Deploy Step - 5] : Stop Old Docker Container"
docker stop $APP_NAME_OLD || true
# 6. Remove the old docker container
echo "---------- [Deploy Step - 6] : Remove Old Docker Container"
docker rm $APP_NAME_OLD || true
# 7. Remove the old docker image
echo "---------- [Deploy Step - 7] : Remove Old Docker Image"
docker rmi $APP_NAME_OLD:$server_version_old || true
# 8. Run the new docker container
echo "---------- [Deploy Step - 8] : Run New Docker Container"
if [ "$PROFILE" == "prod" ]; then
    DOMAIN="api.hellodogwhiz.com"
else
    DOMAIN="$PROFILE.api.hellodogwhiz.com"
fi
docker run -d \
    -e VIRTUAL_HOST=$DOMAIN \
    -e LETSENCRYPT_HOST=$DOMAIN \
    -e LETSENCRYPT_EMAIL=tkfkdal@naver.com \
    -e TZ=Asia/Seoul \
    -e PROFILE=$PROFILE \
    -v /etc/localtime:/etc/localtime:ro \
    -v /home/galaxypoby/storage/dogwhiz-$PROFILE:/home \
    --env-file ../documents/dogwhiz-$PROFILE.env \
    --network nginx-proxy \
    --restart unless-stopped \
    --name $APP_NAME \
    $APP_NAME:$server_version

# Show docker logs
docker logs -f $APP_NAME
