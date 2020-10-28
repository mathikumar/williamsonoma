package com.williamsonoma;

import java.util.List;
import java.util.Scanner;
//import org.apache.log4j.Logger;

public class App {
    //private static final Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        ZipcodeRestrictor zipcoderestrictor = new ZipcodeRestrictor(input.trim());
        System.out.println("");
        List<ZipcodeInterval> zipList = zipcoderestrictor.processInput();
        List<ZipcodeInterval> mergedList = zipcoderestrictor.mergeZipcodes(zipList);
        System.out.println("After merging, the minimum number of zipcode ranges are:");
        zipcoderestrictor.printZipCodes(mergedList);
        System.out.println("");
    }
}
