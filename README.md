# Step to reproduce the bug

0- download the swh graph dataset and update config file

- download the swh graph dataset (s3://softwareheritage/graph/2022-04-25/compressed) you can use the
  scripts/dl_script.sh
- update the path in the config file
  ```graphPath=/.../DATASET/python_data/graph-transposed```

1- install swh-graph to local maven repo

```mvn install:install-file -Dfile=./scripts/swh-graph-2.1.2.jar
-DgroupId=org.softwareheritage.graph -DartifactId=swh-graph -Dversion=2.1.2 -Dpackaging=jar
```

2 - package  
```mvn clean package```

3- run

```  java \
    -ea \
    -server \
    -XX:PretenureSizeThreshold=512M \
    -XX:MaxNewSize=4G \
    -XX:+UseLargePages \
    -XX:+UseTransparentHugePages \
    -XX:+UseNUMA \
    -XX:+UseTLAB \
    -XX:+ResizeTLAB \
    -Djava.io.tmpdir=../java-tmp-dir \
    -Xmx60G \
    -jar ./target/debug-1.0-SNAPSHOT.jar
```

