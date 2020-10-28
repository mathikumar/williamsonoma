#ZipCodeRestrictor

###Problem Description 
Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:

[94133,94133] [94200,94299] [94600,94699]

Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

#####PROBLEM

Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

#####NOTES
- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

#####EXAMPLES:
```
If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399] 
Then the output should be = [94133,94133] [94200,94399]
```
#####Evaluation Guidelines:
Your work will be evaluated against the following criteria:
- Successful implementation
- Efficiency of the implementation
- Design choices and overall code organization
- Code quality and best practices
Java - problem statement - zip code range.txt
Displaying Java - problem statement - zip code range.txt.

------------------------------------------------------------------------------------------------------------------------
####Project Structure
- App.java : Main class to get the input and creating objects to process zipcodes.
- ZipcodeInterval : Data model to store the zipcode.
- ZipcodeRestrictor : Has all the functionalities to process and merge the input.
- AppTest : Has all the tests that run during maven build.

######Build and Execution
```
cd ZipcodeRestriction
mvn clean package
cd target
java -jar ZipCodeRestrictor.jar
```
[Build Maven Project](https://github.com/mathikumar/williamsonoma/blob/main/ZipcodeRestriction/Screen%20Shot%202020-10-28%20at%2012.05.06%20AM.png)

```
indhumathiashok@ZipcodeRestriction % cd target
indhumathiashok@target % java -jar ZipCodeRestrictor.jar
[94133,94133] [94200,94299] [94600,94699]

After merging, the minimum number of zipcode ranges are:
[94133,94133] [94200,94299] [94600,94699] 
indhumathiashok@target % java -jar ZipCodeRestrictor.jar
[94133,94133] [94200,94299] [94226,94399]

After merging, the minimum number of zipcode ranges are:
[94133,94133] [94200,94399] 
indhumathiashok@target % 
```

![alt text](https://github.com/mathikumar/williamsonoma/blob/main/ZipcodeRestriction/Screen%20Shot%202020-10-28%20at%2012.14.51%20AM.png)
  
