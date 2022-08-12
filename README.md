# kafka Spark-Streaming Example

## 1. clone this project
## 2. prepare kafka

start kafka docker
```shell
docker-compose up -d 
docker-compose exec kafka bash  
```

create topic `mytopic`
```shell
cd /opt/kafka/bin
./kafka-topics.sh \
   --create \
   --topic mytopic \
   --partitions 1 \
   --replication-factor 1 \
   --bootstrap-server localhost:9092
```
check topic:
```shell
./kafka-topics.sh --describe --bootstrap-server localhost:9092 --topic mytopic
```

send data:
```shell
./kafka-console-producer.sh --broker-list localhost:9092 --topic mytopic
>hello  
>kafka spark
```

## 3. run spark application in Intellij

open this project in Intellij and install dependencies by maven.
Run `ScalaSparkApp.scala`.
