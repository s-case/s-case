# Script used to execute the NLP REST service.
# This script is for Linux OS. Use run.bat for Windows OS.
# Note that using maven, one can package the service (using the command "mvn clean install") in a war file and then deploy it on a web server.
# In both options, it is necessary that the maximum memory of the java process is set to 4 GB.
# In this file this is handled inside the maven options.
export MAVEN_OPTS="-Xmx4096m"
mvn jetty:run -Djetty.port=8010
