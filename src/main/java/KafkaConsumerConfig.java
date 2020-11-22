import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

public class KafkaConsumerConfig {
    @Bean
    public static Properties consumerFactory() {
Properties consumerProperties = new Properties();
consumerProperties.put("bootstrap.servers", "localhost:9094");
consumerProperties.put("group.id", "ishan");
consumerProperties.put("zookeeper.session.timeout.ms", "6000");
consumerProperties.put("zookeeper.sync.time.ms","2000");
consumerProperties.put("auto.commit.enable", "false");
consumerProperties.put("auto.commit.interval.ms", "1000");
consumerProperties.put("consumer.timeout.ms", "-1");
consumerProperties.put("max.poll.records", "1");
consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    return consumerProperties;
    }


    public static void main(String[] args) {
        /*
         * Creating a thread to listen to the kafka topic
         */
        Thread kafkaConsumerThread = new Thread(() -> {
            System.out.println("Starting Kafka consumer thread.");

            SimpleKafkaConsumer simpleKafkaConsumer = new SimpleKafkaConsumer(
                    "ishanTest",
                    consumerFactory()
            );

            simpleKafkaConsumer.runSingleWorker();
        });

        /*
         * Starting the first thread.
         */
        kafkaConsumerThread.start();
    }
}
