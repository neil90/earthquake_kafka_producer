import java.io.{BufferedReader, InputStreamReader}
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import java.net.URL
import java.util.Properties

object EarthquakeKafkaProducer {

  def main(args: Array[String]) {

    val feedHtml = new URL("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_hour.csv")

    val topic = "Earthquake"
    val props = new Properties
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)

    while(true) {
      
      val file = new BufferedReader(new InputStreamReader(feedHtml.openStream()));
      file.readLine
      var inputLine = file.readLine
      while (inputLine != null) {
      
        val message = new ProducerRecord[String, String](topic, null, inputLine)
        producer.send(message)
        
        println(inputLine)
        inputLine = file.readLine
      }
    
    file.close
    Thread.sleep(390000)
    }
  }
}