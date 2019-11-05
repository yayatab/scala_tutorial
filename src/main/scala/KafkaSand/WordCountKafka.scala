package KafkaSand


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util
import java.util.Locale
import java.util.Properties;

//incomlete.
// look at :
// https://kafka.apache.org/23/documentation/streams/quickstart
// and
// https://github.com/confluentinc/kafka-streams-examples/blob/5.3.1-post/src/main/scala/io/confluent/examples/streams/WordCountScalaExample.scala
//

class WordCountKafka extends App {
  val properties: Properties = new Properties()
  properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount")
  properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0)
  properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
  properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String.getClass.getName)
  // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
  // Note: To re-run the demo, you need to use the offset reset tool:
  // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
  properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");


  val builder: StreamsBuilder = new StreamsBuilder();

  val source: KStream[String, String] = builder.stream("streams-plaintext-input");

//  val splitString = {
//    value => util.Arrays.asList(value.toLowerCase(Locale.getDefault).split(" "))
//  }

  val counts = source.flatMapValues(value => {
    println(value.getClass.getSimpleName)
    util.Arrays.asList(value.toLowerCase(Locale.getDefault).split(" "))
  }).groupBy((_, value) => value).count

  counts.toStream.to("streams-wordcount-output")

}
