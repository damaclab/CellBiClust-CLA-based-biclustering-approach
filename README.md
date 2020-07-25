# CellBiClust
Implementation of CellBiClust Algorithm

### Prerequisites

1. Installed Java version should be >= 8
2. Dependency used : [guava-29.0.jar](https://mvnrepository.com/artifact/com.google.guava/guava/29.0-jre) (Has been included in this project)

## HOW TO EXECUTE

First <b>download</b> the <b>CellBiClust.jar</b> file.

Execution command --
```
CellBiClust.jar "input file path" "delimiter" "output file path" "minsup value as a fraction" "minrows" "mincols"
```
<br>
Each of these parameters represent -- <br>
<b>"input file path"</b>            : the path to an input file containing a transaction database.<br>
<b>"delimiter"</b>                  : the delimiter between each item in the input file.For example it will be "," for csv files<br>
<b>"output file path"</b>           : the output file path for saving the result (if null, the result will also be printed).<br>
<b>"minsup value as a fraction"</b> : the minimum support threshold.<br>
<b>"minrows"</b> : the minimum number of rows in the bicluster.<br>
<b>"minrows"</b> : the minimum number of columns in the bicluster.<br>
 <br>

For using it in editors -<br>
1.Clone this repository <br>
2.Open it in your desired editor<br>
3.Build it using maven with goals clean and install<br>
4.As an example execute MainTest.java in main package.<br>

## EXAMPLE
To execute it download the <b>CellBiClust.jar</b> file , <b>sample.txt</b> and type the following command --<br>
```  
CellBiClust.jar "sample.txt" "," "output.txt"  0.2 2 2
```
## ORIGINAL PAPER
* **Author** - *Initial work* - [Link](link)
