package com.qunar.qchat.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by MSI on 2017/10/25.
 */
public class CleanupTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(CleanupTask.class);

    public void process() {
        LOGGER.info("qunartz run time", System.currentTimeMillis());
    }
}
