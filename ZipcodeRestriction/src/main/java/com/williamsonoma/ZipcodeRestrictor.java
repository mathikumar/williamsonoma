package com.williamsonoma;

import java.util.*;

public class ZipcodeRestrictor {
    private String zipInput;

    public ZipcodeRestrictor(String zipcode){
        this.zipInput = zipcode;
    }

    public List<ZipcodeInterval> processInput(){
        if(zipInput.isEmpty()){
            System.out.println("Requires input Zipcode in the format [lower-bound,upper-bound] with series of ranges separated by whitespace.");
            System.exit(1);
        }
        String[] zipcodeRanges = zipInput.split("\\s+");
        List<ZipcodeInterval> zipcodeList = new ArrayList<ZipcodeInterval>();
        for (int i = 0; i < zipcodeRanges.length; i++) {
            ZipcodeInterval zipinterval = validateInput(zipcodeRanges[i]);
            if(zipinterval != null)
                zipcodeList.add(zipinterval);
        }
        return zipcodeList;
    }

    public ZipcodeInterval validateInput(String zipcodeRange){

        String[] zipBounds = zipcodeRange.replaceAll("\\[|\\]", "").split(",");
        if(zipBounds.length != 2)
            throw new IllegalArgumentException(zipcodeRange + " Zipcode ranges should contain only lower and upper bounds. The input length is more than two.");

        int lowBound = Integer.parseInt(zipBounds[0]);
        int upBound = Integer.parseInt(zipBounds[1]);

        ZipcodeInterval zipinterval = null;
        if(validateLimit(lowBound) && validateLimit(upBound)) {
            if(lowBound <= upBound)
                return new ZipcodeInterval(lowBound, upBound);
            else{
                System.out.println("Lower bound should be less than upper bound. Swapped both the values for " + zipcodeRange);
                return new ZipcodeInterval(upBound, lowBound);
            }
        }
        else{
            System.out.println("Zipcode ranges should not exceed 99999. Skipped the range" + zipcodeRange);
        }
        return zipinterval;
    }

    public boolean validateLimit(int zipcode){
        return (zipcode <= 99999)? true : false;
    }

    public void sortZipCode(List<ZipcodeInterval> zlist){
        if(zlist.size() != 0){
            Collections.sort(zlist, new Comparator<ZipcodeInterval>() {
                public int compare(ZipcodeInterval zip1, ZipcodeInterval zip2) {
                    if(zip1.getLower() == zip2.getLower())
                        return zip1.getUpper() - zip2.getUpper();
                    else
                        return zip1.getLower() - zip2.getLower();
                }
            });
        }
    }

    public List<ZipcodeInterval> mergeZipcodes(List<ZipcodeInterval> zipCodeList){
        sortZipCode(zipCodeList);
        List<ZipcodeInterval> mergedZCList = new LinkedList<ZipcodeInterval>();
        ZipcodeInterval existingZipCode = zipCodeList.get(0);
        for (ZipcodeInterval currentZipCode : zipCodeList) {
            if(existingZipCode.getUpper() >= currentZipCode.getLower() || (existingZipCode.getUpper() + 1) == currentZipCode.getLower()){
                if(existingZipCode.getUpper() < currentZipCode.getUpper() && existingZipCode.getLower() != currentZipCode.getLower())
                    existingZipCode.setUpper(currentZipCode.getUpper());
            }
            else{
                mergedZCList.add(existingZipCode);
                existingZipCode = currentZipCode;
            }
        }
        mergedZCList.add(existingZipCode);
        return mergedZCList;
    }

    public void printZipCodes(List<ZipcodeInterval> zcList){
        for (ZipcodeInterval zipcode: zcList) {
            System.out.print(zipcode.toString());
            System.out.print(" ");
        }
    }
}
