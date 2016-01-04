# Spark Facebook Streaming App [![Build Status](https://travis-ci.org/t3g7/spark-streaming-twitter.svg)](https://travis-ci.org/t3g7/spark-facebook) 

### Configuration
Get a user access token (https://developers.facebook.com/tools/explorer/) and copy it into ```src/main/resources/token.txt```.
Set the accounts you want feed to be recovered from in ```src/main/resources/facebookPages.txt```. The account names have to be written as they appear in the browser's address bar, one account per line.

### Building
Create the JAR with:
	
	mvn package

The jar file is now located in `target/spark-facebook-$VERSION.jar`

### Running the app
Note: a Cassandra instance must be running.
From the ```$SPARK_HOME``` folder, run the following:

    ./bin/spark-submit --jars --jars $(echo $PATH_TO_JAR/dependency/*.jar | tr ' ' ',') --class com.t3g7.spark.facebook.FacebookStreamingApp $PATH_TO_JAR/spark-facebook-$VERSION.jar

`$PATH_TO_JAR` should be /path_to_project/target where `path_to_project` is the directory in which the project is located.
