package ru.itmo.tpo.lab2.report;

import ru.itmo.tpo.lab2.math.MathFunction;

public abstract class MathFuncReportWriter implements ReportWriter<MathFuncReportWriter.Params> {
    protected final String filename;
    protected final MathFunction func;

    protected MathFuncReportWriter(String filename, MathFunction func) {
        this.filename = filename;
        this.func = func;
    }

    public record Params(double from, double to, double step) {
        public Params {
            double diff = to - from;
            if (diff < 0) {
                throw new IllegalArgumentException("Argument interval should be positive");
            }
        }
    }
}
