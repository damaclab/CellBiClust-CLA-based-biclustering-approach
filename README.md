# CellBiClust
Implementation of CellBiClust Algorithm

### Prerequisites

1. Installed Java version should be >= 8
2. Dependency used : [guava-29.0.jar](https://mvnrepository.com/artifact/com.google.guava/guava/29.0-jre) (Has been included in this project)

## HOW TO EXECUTE

First <b>download</b> the <b>CellBiClust.jar</b> file.

For <b>Java</b> Users Execution command --
```
CellBiClust.jar "input file path" "delimiter" "output file path" "output filename" minrows" "mincols" "rule_gen" "rule threshold" "similarity"
```

For <b>R</b> Users Execution command --
1. Make sure you have installed R and RStudio 
2. Open RStudio and inside a shell do the next steps
3. Install <b>rJava</b> package using : ```install.packages("rJava")``` 
4. Import <b>rJava</b> package using :```library(rjava)```
5. Initialize the JVM : ```.jinit()```
6. Add the jar file : ```.jaddClassPath('path_to_CellBiClust.jar')```
7. Create an object of the main class : ```ob=.jnew('MainCellBiClust')```
8. Call the main function using : ```.jcall(ob,'V','main',an_array_of_arguments)```
The order of arguments in an_array_of_arguments will the same as mentioned in the next section. 
Example : ```.jcall(ob,'V','main', c("D:/Eclipse_Workspace/CellBiClust/src/sample.csv" ,",", "src" ,"sample_out", 2, 2, "true", 0.7, 0.75) )```

##### Command Line Arguments 
<b>"input file path"</b>            : the path to an input file containing a transaction database.<br>
<b>"delimiter"</b>                  : the delimiter between each item in the input file.For example it will be "," for csv files<br>
<b>"output file path"</b>           : the output file path for saving the biclusters obtained (if null, the biclusters will be printed).<br>
<b>"output filename"</b> : the filename only. Example "output". File extensions will be added automatically<br>
<b>"minrows"</b> : the minimum number of rows in the bicluster.<br>
<b>"mincols"</b> : the minimum number of columns in the bicluster.<br>
<b>"rule_gen"</b> : the value should be "true" or "false" if the user wants to generate rules or not<br>
<b>"rule threshold"</b> : the minimum confidence for each rule to be generated<br>
<b>"similarity"</b> : the minimum similarity between two clusters<br>
 <br>


##### For using it in editors 
<ol>
<li>Clone this repository </li>
<li>Open it in your desired editor</li>
<li>Build it using maven with goals clean and install</li>
<li>As an example execute MainTest.java in main package.</li>
</ol>
##### Output Files
The algorithm will generate three files one each for : biclusters, rules and predictions.<br>
Let the filename be "sample_output".<br>
The file containing biclusters will be named as "sample_output_biclusters.txt".<br>
The file containing rules will be named as "sample_output_rule.txt".<br>
The file containing predictions will be named as "sample_output_prediction.txt".<br>

## EXAMPLE
To execute it download the <b>CellBiClust.jar</b> file , <b>sample.csv</b> and type the following command --<br>
```  
CellBiClust.jar "sample.csv" "," "src" "sample_out" 2 2 "true" 0.7 0.75
```
The sample outputs are available in src folder<br>

<b>Note</b> : You can use src/dataGenerator.m to generate a binary matrix of dimenation m * n with k 1's . However it won't assign any column or row names. In order to execute the generated file using CellBiClust.jar , one has to add those row names and column names externally. 
## ORIGINAL PAPER
* **Author** - *Initial work* - [Link](link)
