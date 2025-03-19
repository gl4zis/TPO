package ru.itmo.tpo.lab2.report;

import lombok.extern.log4j.Log4j2;
import ru.itmo.tpo.lab2.math.MathFunction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

@Log4j2
public class CsvReportWriter extends MathFuncReportWriter {

    public CsvReportWriter(String filename, MathFunction func) {
        super(filename, func);
    }

    @Override
    public void write(Params params) throws IOException {
        Path path = Paths.get(filename);
        File file = path.toFile();
        if (file.exists()) {
            if (!file.delete()) {
                throw new IOException("Can't delete file " + file);
            }
        }
        if (!file.createNewFile()) {
            throw new IOException("Can't create file " + file);
        }
        try (var writer = new PrintWriter(file)) {
            String funcName =  func.getClass().getSimpleName().toLowerCase();
            writer.printf("x, %s(x)\n", funcName);
            double currX = params.from();
            while (currX < params.to()) {
                try {
                    double val = func.calc(currX);
                    if (!Double.isInfinite(val) && !Double.isNaN(val)) {
                        writer.printf("%.3f, %3f\n", currX, func.calc(currX));
                    }
                } catch (IllegalArgumentException e) {
                    log.warn("{}({}): {}", funcName, String.format("%.3f", currX), e.getMessage());
                }
                currX += params.step();
            }
        }
    }
}
