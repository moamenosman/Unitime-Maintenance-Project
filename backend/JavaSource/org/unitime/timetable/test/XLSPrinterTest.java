package org.unitime.timetable.test;

import org.junit.Test;
import org.unitime.timetable.export.XLSPrinter;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class XLSPrinterTest {

    @Test
    public void testXLSPrinter_NoCrashWithZeroValues() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        XLSPrinter printer = new XLSPrinter(out, false);

        try {
            // simulate basic usage that triggers internal row/column calculations
            printer.printHeader("Col1", "Col2");
            printer.printLine("1", "2");
            printer.printLine("3", "4");

            printer.flush();
            printer.close();

            assertTrue(true); // success if no exception
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }
}