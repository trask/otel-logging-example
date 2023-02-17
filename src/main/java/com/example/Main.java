package com.example;

import io.opentelemetry.api.logs.GlobalLoggerProvider;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.LogRecordExporter;
import io.opentelemetry.sdk.logs.export.SimpleLogRecordProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        // to use OTLP, replace this with OtlpGrpcLogRecordExporter.builder()...build();
        LogRecordExporter logRecordExporter = SystemOutLogRecordExporter.create();

        SdkLoggerProvider sdkLoggerProvider =
                SdkLoggerProvider.builder().addLogRecordProcessor(
                        SimpleLogRecordProcessor.create(logRecordExporter)
                ).build();

        GlobalLoggerProvider.set(sdkLoggerProvider);

        logger.info("hi");

        Thread.sleep(10000);
    }
}
