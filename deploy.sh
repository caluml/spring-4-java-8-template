HOST=your.server.com
TARGET=/home/project/webapp/ROOT.war
mvn clean package $* && \
rsync -va target/project-1.0-SNAPSHOT.jar ${HOST}:${TARGET}
