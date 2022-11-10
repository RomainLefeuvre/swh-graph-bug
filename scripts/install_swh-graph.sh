#!/bin/bash  
wget https://files.pythonhosted.org/packages/e9/55/869d0a2698d773b556f936d3ed0f82c650598abab4177736c0212d3ad827/swh.graph-2.1.2.tar.gz
tar -xf swh.graph-2.1.2.tar.gz
mvn install:install-file -Dfile=./swh.graph-2.1.2/java/target/swh-graph-2.1.2.jar -DgroupId=org.softwareheritage.graph -DartifactId=swh-graph -Dversion=2.1.2 -Dpackaging=jar
rm ./swh.graph-2.1.2.tar.gz
rm -r ./swh.graph-2.1.2