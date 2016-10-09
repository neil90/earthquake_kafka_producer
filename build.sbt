lazy val root = (project in file(".")).
settings(
    name := "earthquake_kafka_producer",
    version := "0.1",
    scalaVersion := "2.11.8"
//    mainClass in Compile := Some("Launch")    
        )

libraryDependencies ++= Seq(
	"org.apache.kafka" % "kafka_2.11" % "0.10.0.1",
    "com.typesafe" % "config" % "1.3.1"
    )


