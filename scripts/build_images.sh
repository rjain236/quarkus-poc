#!/bin/bash

curr_dirname=$(pwd)
services=${SERVICES:-""}
docker_repository=rjain236

if [ -z "$services" ];then
  exit 0
fi

function _build_images() {
  for service in $services; do
    echo "building image for $service"
    cd "${curr_dirname}/${service}" || exit
    #tag them as local so that can be run locally using this tag
    docker build -f "src/main/docker/Dockerfile.jvm" -t "quarkus/${service}-jvm:${LOCAL_TAG}" .
  done
}

_latest_docker_tags() {
   echo "$(docker images "$docker_repository/$1" --format "{{.Tag}}" | grep -v "latest" | head -n 1)"
}

#check later how to make it work
_docker_tags() {
    item=$1
    tokenUri="https://auth.docker.io/token"
    data=("service=registry.docker.io" "scope=repository:$item:pull")
    token="$(curl --silent --get --data-urlencode ${data[0]} --data-urlencode ${data[1]} $tokenUri | jq --raw-output '.token')"
    listUri="https://registry-1.docker.io/v2/$item/tags/list"
    authz="Authorization: Bearer $token"
    result="$(curl --silent --get -H "Accept: application/json" -H "Authorization: Bearer $token" $listUri | jq --raw-output '.')"
    echo $result
}

_build_images

