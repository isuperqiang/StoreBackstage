#!/usr/bin/env bash

nohup java -jar backstage-1.0.0.jar --server.port=80 --upload.dir=/root/backstage/upload/ >/root/backstage/temp.txt &