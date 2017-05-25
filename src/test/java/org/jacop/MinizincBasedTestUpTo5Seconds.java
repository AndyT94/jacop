package org.jacop;

import org.jacop.fz.Fz2jacop;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Mariusz Świerkot
 */

@RunWith(Parameterized.class) public class MinizincBasedTestUpTo5Seconds extends MinizincBasedTestsHelper {
    protected static final String timeCategory = "upTo5sec/";

    @Rule
    public Timeout globalTimeout = Timeout.seconds(20);

    public MinizincBasedTestUpTo5Seconds(String testFilename) {

        this.testFilename = testFilename;

    }

    @Parameterized.Parameters public static Collection<String> parametricTest() throws IOException {

        return fileReader(timeCategory);
    }

    @Test
    public void testMinizinc() throws IOException {

        testExecution(timeCategory);
    }

    @After public void cleanUp() {
        String outputFilename = relativePath + testFilename + ".fzn" + ".out";
        try {
            Files.delete(Paths.get(outputFilename));
        } catch (IOException e) {
            // File was not created (because the test timeout before it was created so deleting it failed.
        }
    }

}
