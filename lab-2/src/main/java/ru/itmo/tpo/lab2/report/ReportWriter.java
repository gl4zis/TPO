package ru.itmo.tpo.lab2.report;

import java.io.IOException;

public interface ReportWriter<P> {
    void write(P params) throws IOException;
}
