package br.al.mcz.agibank.filereader.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;


public class ThreadUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadUtil.class);

    public static void esperarJob(JobExecution job) {
        while (job.isRunning()) {
            LOGGER.debug("Job ainda esta rodando...");
        }
    }
}
