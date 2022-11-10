package fr.inria.diverse;

import it.unimi.dsi.big.webgraph.labelling.ArcLabelledNodeIterator;
import it.unimi.dsi.bits.Fast;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.softwareheritage.graph.SwhUnidirectionalGraph;

import java.io.FileInputStream;
import java.io.IOException;

import static it.unimi.dsi.big.webgraph.labelling.BitStreamArcLabelledImmutableGraph.LABELS_EXTENSION;

public class Debug {
    static Logger logger = LogManager.getLogger(Debug.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration.init();
        SwhUnidirectionalGraph g = loadGraph();
        long currentNodeId = 23000000000L;
        detectException(currentNodeId, g);
        ArcLabelledNodeIterator.LabelledArcIterator it = g.labelledSuccessors(currentNodeId);

    }

    public static SwhUnidirectionalGraph loadGraph() throws IOException {
        logger.info("Loading graph " + (isMappedMemoryActivated() ? "MAPPED MODE" : ""));
        SwhUnidirectionalGraph graph = isMappedMemoryActivated() ?
                SwhUnidirectionalGraph.loadLabelledMapped(Configuration.getInstance().getGraphPath()) :
                SwhUnidirectionalGraph.loadLabelled(Configuration.getInstance().getGraphPath());
        logger.info("Graph loaded");
        return graph;
    }


    public static boolean isMappedMemoryActivated() {
        return Configuration.getInstance().getLoadingMode().equals("MAPPED");
    }

    public static void detectException(long currentNodeId, SwhUnidirectionalGraph g) {
        long size = 0L;
        try {
            FileInputStream fis = new FileInputStream(Configuration.getInstance()
                    .getGraphPath() + "-labelled" + LABELS_EXTENSION);
            size = fis.getChannel().size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long l = Fast.mostSignificantBit((size * Byte.SIZE + 1) / (g.numNodes() + 1));
        if ((l * currentNodeId) >>> 6 > Integer.MAX_VALUE) {
            logger.error("labelledSuccessors call will crash for node " + currentNodeId);
        }
        logger.info("Graph nodes number " + g.numNodes());
    }
}
