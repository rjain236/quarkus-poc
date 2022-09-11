#!/bin/bash

curr_dirname=$(pwd)
mvnw="$curr_dirname/mvnw"
$mvnw clean package
