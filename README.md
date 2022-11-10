# Step to reproduce the bug

0- download the swh graph dataset and update config file

- download the swh graph dataset (s3://softwareheritage/graph/2022-04-25/compressed) you can use the
  ```scripts/dl_script.sh``` script
- update the graph path in the config file if you did not use the script

1- download and install swh-graph to local maven repo

```
cd scripts
sh install_swh-graph.sh
```

2 - package  
On project root :
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

