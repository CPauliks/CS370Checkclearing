#!/bin/bash

server=${1-localhost:8085}

history=`curl -s http://$server/checkclearing`

echo $history

response=`curl -s -H Content-Type:application/json -d "$history" http://$server/checkclearing`

if [ "$response" != "lol" ]; then
	echo $response
	echo "OH NOES TEST FAILED"
	exit 1
else
	echo "Test Succeded!"
fi

