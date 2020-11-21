import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Configuration
public class KafkaProducerConfig {
    private static KafkaProducer<String, String> producer;
    // private static final Logger logger = Logger.getLogger(KafkaProducerConfig.class);

    @Value(value = "${kafka.bootstrap.servers}")
    static String bootstrapAddress;

    @Value(value = "${kafka.topic.thetechcheck}")
    static String theTechCheckTopicName;
    /*
Creating a Kafka Producer object with the configuration above.
*/
   // KafkaProducer<String, String> producer = new KafkaProducer<>(producerFactory());

    @Bean
    public static Properties producerFactory() {
        /*
         * Defining producer properties.
         */
        Properties producerProperties = new Properties();
        producerProperties.put("bootstrap.servers", "localhost:9094");
        producerProperties.put("acks", "all");
        producerProperties.put("retries", 0);
        producerProperties.put("batch.size", 16384);
        producerProperties.put("linger.ms", 1);
        producerProperties.put("buffer.memory", 33554432);
        producerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return producerProperties;
    }



    /**
     * Function to send a message to Kafka
     * @param payload The String message that we wish to send to the Kafka topic
     * @param producer The KafkaProducer object
     * @param topic The topic to which we want to send the message
     */
    private static void sendKafkaMessage(String payload,
                                         KafkaProducer<String, String> producer,
                                         String topic) throws ExecutionException, InterruptedException {
        //logger.info("Sending Kafka message: " + payload);
        Future<RecordMetadata> send = producer.send(new ProducerRecord<>(topic, payload));
        RecordMetadata recordMetadata = send.get();
        System.out.println((recordMetadata.toString()));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
Creating a loop which iterates 10 times, from 0 to 9, and sending a
simple message to Kafka.
 */
        producer=new KafkaProducer<>(producerFactory());
        for (int index = 0; index < 12; index++) {
            sendKafkaMessage(generatePayLoad(index), producer, "ishanTest");
        }
    }

    private static String generatePayLoad(int index) {
        /*
Creating a loop which iterates 10 times, from 0 to 9, and creates an instance of JSONObject in each iteration. We'll use this simple JSON object to illustrate how we can send a JSON object as a message in Kafka.
 */


    /*
    We'll create a JSON object which will have a bunch of fields, and another JSON object, which will be nested inside the first JSON object. This is just to demonstrate how complex objects could be serialized and sent to topics in Kafka.
     */
            JSONObject jsonObject = new JSONObject();
            JSONObject nestedJsonObject = new JSONObject();

            try {
        /*
        Adding some random data into the JSON object.
         */
//                for(int i=0;i<index;i++){
//                    jsonObject.put("index", index);
//                    jsonObject.put("message", "The index is now: " + index);
//                }
                jsonObject.put("index", index);
                jsonObject.put("message", "The index is now: " + index);

        /*
        We're adding a field in the nested JSON object.
         */
                nestedJsonObject.put("nestedObjectMessage", "This is a nested JSON object with index: " + index);

        /*
        Adding the nexted JSON object to the main JSON object.
         */
                jsonObject.put("nestedJsonObject", nestedJsonObject);

            } catch (JSONException e) {
                System.out.println(e);
            }

    /*
    We'll now serialize the JSON object we created above, and send it to the same topic in Kafka, using the same function we used earlier.
    You can use any JSON library for this, just make sure it serializes your objects properly.
    A popular alternative to the one I've used is Gson.
     */
            return jsonObject.toString();
        }
    }
