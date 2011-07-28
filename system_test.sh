#!/bin/bash

history=`curl -s http://cpaulikscs370.appspot.com/checkclearing`

echo $history

response=`curl -s -H Content-Type:application/json -d "$history" http://localhost:8085/checkclearing`
echo " "
if [ "$response" != "lol" ]; then
	echo $response
	echo "OH NOES TEST FAILED"
	exit 1
else
	echo "Test Succeded!"
fi

