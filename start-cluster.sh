#!/usr/bin/env bash

export MY_IP=`ip route get 1 | awk '{print $NF;exit}'`
docker-compose up -d --scale kafka=6