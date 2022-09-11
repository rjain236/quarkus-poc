#!/bin/bash

curr_dirname=$(pwd)
services=${SERVICES:-""}

if [ -z "$services" ];then
  exit 0
fi

function _publish_image_for_service() {
  service=$1
  existing_image="quarkus/${service}-jvm:${LOCAL_TAG}"
  new_image="rjain236/${service}-jvm:${SHORT_GIT_HASH}"
  docker tag "$existing_image" "$new_image"
  docker push "$new_image"
  echo "finished publish for $service"
}

function _publish_images() {
  docker login -u="${DOCKER_USERNAME}" -p="${DOCKER_PASSWORD}"
  for service in $services; do
    echo "start publish for $service"
    _publish_image_for_service "$service" &
  done
  wait
}

_publish_images

