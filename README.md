#Steps
- Start zookeeper
```
cd ..\kafka
.\bin\windows\zookeeper-server-start.bat config/zookeeper.properties
```
- Start kafka server
```
cd ..\kafka
.\bin\windows\kafka-server-start.bat config/server.properties
```
- Create Kafka topic
```
 .\bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic sensorData
```
- Run Spring boot application
- Run Angular application
