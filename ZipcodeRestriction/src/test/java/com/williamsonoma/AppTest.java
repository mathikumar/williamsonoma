package com.williamsonoma;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class AppTest extends TestCase {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    public AppTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(AppTest.class);
        return suite;
    }

    public void testApp() {
        assertTrue( true );
    }

    public void testListPopulated(){
        String input = "[94133,94133] [94200,94299] [94600,94699] [94133,94139] [94226,94399]";
        ZipcodeRestrictor zipcoderestrictor = new ZipcodeRestrictor(input);
        List<ZipcodeInterval> zipList = zipcoderestrictor.processInput();
        assertTrue(zipList.size() != 0);
    }

    public void testSwapBounds(){
        String input = "[94200,94299] [94780,94699] [94133,94133]";
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            ZipcodeRestrictor zipcoderestrictor = new ZipcodeRestrictor(input);
            List<ZipcodeInterval> zipList = zipcoderestrictor.processInput();
        } finally {
            System.setOut(console);
        }

        String message = "Lower bound should be less than upper bound. Swapped both the values for [94780,94699]\n";
        assertEquals(message, bytes.toString());
    }

   public void testIllegalArgument(){
       try {
           String input = "[92304,92302,92203] [92013,92014]";
           ZipcodeRestrictor zipcoderestrictor = new ZipcodeRestrictor(input);
           List<ZipcodeInterval> zipList = zipcoderestrictor.processInput();
       } catch (IllegalArgumentException e) {
           String message = "[92304,92302,92203] Zipcode ranges should contain only lower and upper bounds. The input length is more than two.";
           assertEquals(message, e.getMessage());
       }
    }

    public void testRangeLimit(){
        String input = "[94200,94299] [94780,100000]";
        PrintStream console = System.out;
        try {
            System.setOut(new PrintStream(bytes));
            ZipcodeRestrictor zipcoderestrictor = new ZipcodeRestrictor(input);
            List<ZipcodeInterval> zipList = zipcoderestrictor.processInput();
        } finally {
            System.setOut(console);
        }

        String message = "Zipcode ranges should not exceed 99999. Skipped the range[94780,100000]\n";
        assertEquals(message, bytes.toString());
    }

    public void testSortingList(){
        String input = "[94200,94299] [94600,94699] [94133,94133]";
        ZipcodeRestrictor zipcoderestrictor = new ZipcodeRestrictor(input);
        List<ZipcodeInterval> zipList = zipcoderestrictor.processInput();
        assertTrue(zipList.size() == 3);
        assertTrue(zipList.get(0).getLower() == 94200);
        assertTrue(zipList.get(0).getUpper() == 94299);
        zipcoderestrictor.sortZipCode(zipList);
        assertTrue(zipList.size() == 3);
        assertTrue(zipList.get(0).getLower() == 94133);
        assertTrue(zipList.get(0).getUpper() == 94133);
    }

    public void testActualOutput(){
        String input = "[94133,94133] [94200,94299] [94226,94399]";
        ZipcodeRestrictor zipcoderestrictor = new ZipcodeRestrictor(input);
        List<ZipcodeInterval> zipList = zipcoderestrictor.processInput();
        assertTrue(zipList.size() == 3);
        List<ZipcodeInterval> mergedList = zipcoderestrictor.mergeZipcodes(zipList);
        zipcoderestrictor.printZipCodes(mergedList);
        assertTrue(mergedList.size() == 2);
        assertTrue(mergedList.get(0).getLower() == 94133);
        assertTrue(mergedList.get(0).getUpper() == 94133);
        assertTrue(mergedList.get(1).getLower() == 94200);
        assertTrue(mergedList.get(1).getUpper() == 94399);
    }
}
