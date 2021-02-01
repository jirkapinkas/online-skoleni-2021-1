#!/bin/sh

cd ../data/;java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server -database.0 file:eshop -dbname.0 eshop
