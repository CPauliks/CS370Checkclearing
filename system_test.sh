#!/bin/bash

server=${1-localhost:8085}

response=`curl -H Content-Type:application/json -d '["one and 22/100"]' 
http://$server/checkclearing`

if [ $response != '{"one and 22/100":122}' ]; then
	echo 'TEST FAILED OH NOES!'
	exit 1
else
	echo 'Test succeded!'
fi

