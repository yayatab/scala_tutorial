#!/usr/bin/env bash
TOPIC_NAME=test
HOST=localhost
PORT=9002

bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties

#  this is for general purposes,
# Topic creation with $TOPIC_NAME
bin/kafka-topics.sh --create --bootstrap-server $HOST:$PORT --replication-factor 1 --partitions 1 --topic $TOPIC_NAME

# this part is for cli commands and checks
#      # List all topics
#      bin/kafka-topics.sh --list --bootstrap-server $HOST:$PORT
#
#      # console producer
#
#      bin/kafka-console-producer.sh --broker-list $HOST:$PORT --topic $TOPIC_NAME
#
#      #console consumer
#      bin/kafka-console-consumer.sh --bootstrap-server $HOST:$PORT --topic $TOPIC_NAME --from-beginning

bin/kafka-topics.sh --create \
  --bootstrap-server $HOST:$PORT \
  --replication-factor 1 \
  --partitions 1 \
  --topic streams-plaintext-input
# should output:
# Created topic "streams-plaintext-input".


bin/kafka-topics.sh --create \
  --bootstrap-server $HOST:$PORT \
  --replication-factor 1 \
  --partitions 1 \
  --config cleanup.policy=compact\
  --topic streams-wordcount-input
# should output:
# Created topic "streams-plaintext-input".
