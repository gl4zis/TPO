package ru.itmo.tpo.extension;

import com.sun.management.OperatingSystemMXBean;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CpuUsageExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private final OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private ScheduledExecutorService scheduler;
    private double cpuLoad;

    @Override
    public void beforeTestExecution(ExtensionContext context) {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            cpuLoad = osBean.getProcessCpuLoad();
            System.out.printf("%s [%s] CPU load: %.2f%%%n",
                    LocalDateTime.now(), context.getDisplayName(), cpuLoad * 100);
        }, 0, 500, TimeUnit.MILLISECONDS);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }
}
