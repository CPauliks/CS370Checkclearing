#!/bin/bash

server=${1-localhost:8085}

response=`curl -H Content-Type:application/json -d '["one"]' http://$server/checkclearing`

if [ $response != '{"one":100}' ]; then
	echo 'TEST FAILED OH NOES!'
	exit 1
else
	echo 'Test succeded!'
fi

