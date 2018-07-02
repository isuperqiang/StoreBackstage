#!/usr/bin/env bash
# 创建上传的文件夹，然后初始化数据库，注意修改文件路径。

mkdir ~/upload
mysql -h localhost -P 3306 -u root -prichie123 < /Users/richie/IdeaProjects/bugCatcher/src/main/resources/database/pfcase-schema.sql