#!/usr/bin/env bash

set -e

curr_dirname="$(pwd)/$(dirname "$0")"
envs_file=$curr_dirname/envs

function _set_git() {
  echo "SHORT_GIT_HASH=$1" >> "$envs_file"
}

function _ci_hash() {
  sha=$(echo "$CIRCLE_SHA1" | cut -c -7)
  _set_git "$sha"
}

function _local_hash() {
  sha=$(git rev-parse --short HEAD)
  _set_git "$sha"
}

function _set_branch_name() {
  branch_name=$(git symbolic-ref --short HEAD)
  echo "GIT_BRANCH_NAME=$branch_name" >> "$envs_file"
}

function _set_services() {
  mapfile -t services < "$curr_dirname/services.list"
  echo "SERVICES=${services[*]}" >> "$envs_file"
}

function _set_env() {
  echo "ENV=${ENV:-local}" >> "$envs_file"
}

function _set_local_tag() {
  echo "LOCAL_TAG=${LOCAL_TAG:-local}" >> "$envs_file"
}


if [ -v CIRCLE_SHA1 ];then
  _ci_hash
else
  _local_hash
fi

_set_branch_name
_set_services
_set_env
_set_local_tag

