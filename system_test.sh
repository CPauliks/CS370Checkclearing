#!/bin/bash

server="localhost:8085"

history=`curl -s cpaulikscs370.appspot.com/checkclearing`

echo $history

response=`curl -s -H Content-Type:application/json -d "$history" http://$server/checkclearing`

echo $response


