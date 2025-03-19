package ru.itmo.tpo.lab2;

import ru.itmo.tpo.lab2.math.FuncSystem;
import ru.itmo.tpo.lab2.math.MathFunction;
import ru.itmo.tpo.lab2.math.logariphmic.*;
import ru.itmo.tpo.lab2.math.trigonometric.Cos;
import ru.itmo.tpo.lab2.math.trigonometric.Cot;
import ru.itmo.tpo.lab2.math.trigonometric.Sec;
import ru.itmo.tpo.lab2.report.CsvReportWriter;
import ru.itmo.tpo.lab2.report.MathFuncReportWriter;

import java.io.IOException;

public class FuncCalculator {
    private static final MathFuncReportWriter.Params DEFAULT_REPORT_PARAMS =
            new MathFuncReportWriter.Params(-5, 5, 0.1);
    private static final String HOME_DIR = "/Users/makeev/IdeaProjects/TPO/lab-2/reports/";

    private static final Cos COS = new Cos();
    private static final Cot COT = new Cot(COS);
    private static final Sec SEC = new Sec(COS);
    private static final Ln LN = new Ln();
    private static final Log LOG_2 = new Log(2, LN);
    private static final Log LOG_3 = new Log(3, LN);
    private static final Log LOG_5 = new Log(5, LN);
    private static final Log LOG_10 = new Log(10, LN);
    private static final FuncSystem FUNC_SYSTEM = new FuncSystem(
            COS, COT, SEC, LN, LOG_2, LOG_3, LOG_5, LOG_10
    );

    public static void main(String[] args) throws IOException {
        report("cos", COS);
        report("cot", COT);
        report("sec", SEC);
        report("ln", LN);
        report("log2", LOG_2);
        report("log3", LOG_3);
        report("log5", LOG_5);
        report("log10", LOG_10);
        report("funcsystem", FUNC_SYSTEM);
    }

    private static void report(String name, MathFunction func) throws IOException {
        String filepath = HOME_DIR + name + "-report.csv";
        new CsvReportWriter(filepath, func).write(DEFAULT_REPORT_PARAMS);
    }
}
