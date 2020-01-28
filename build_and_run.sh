#!/bin/bash

set -e

sudo docker stop service-customer

mvn clean test package

sudo docker build --tag service-customer .
sudo docker-compose up -d