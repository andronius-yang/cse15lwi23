# CSE 15L Blog
## Lab Report 5
### Author: Andrew Yang
## Setting up the environment
For the last lab, I decided to revisit lab 3 and inquire about a different command, namely the `find` command. However, this time, instead of inquiring about options, I will be inquiring about the predicates that are used, which are of similar format to options except a parameter has to be supplied after it.

As usual, I downloaded the dataset `written_2` again from the GitHub [link](https://github.com/ucsd-cse15l-w23/skill-demo1-data) and placed it under a new path `cse15lwi23/report5files/written_2/`. All commands below are done in the working directory `report5files/`.
## Command `find`
### Predicate `-maxdepth`
Sample input 1:
```
$ find . -maxdepth 1
.
./written2
```
The `-maxdepth` predicate allows us to limit the max levels for the command to search before halting. It prints out all files and directories with depths lesser or equal to the supplied argument after `-maxdepth`

Sample input 2:
```
$ find . -maxdepth 10
...
...
./written_2/travel_guides/berlitz2/Portugal-WhereToGo.txt
```
In the case that the argument supplied with `-maxdepth` is greater than the maximum depth of the path supplied, `find` simply outputs all available files in the directory.
### Predicate `-mindepth`
Sample input 1:
```
$ find . -mindepth 5
./written_2/non-fiction/OUP/Abernathy/ch1.txt
...
./written_2/non-fiction/OUP/Rybczynski/ch3.txt
```
The `-mindepth` predicate is similar to the `-maxdepth` predicate in that it limits the output to files and directories that have at least `x` minimum depth, where `x` is the supplied integer after the predicate. From its behavior here, we can see that `-maxdepth` and `-mindepth` begins its depth indexing from 0 (the supplied path `.` being depth 0) and counts from there.

Sample input 2:
```
$ find . -mindepth 2 -maxdepth 2
./written_2/non-fiction
./written_2/travel_guides
```
By supplying both predicates at the same time, we can tightly bound the depth of our `find`. As seen in the sample input here, I restricted it to search only the files and directories two levels deep from the current working directory, of which it returned two paths.

### Predicate `-name`
Sample input 1:
```
$ find . -name "ch*"
./written_2/non-fiction/OUP/Abernathy/ch1.txt
...
./written_2/non-fiction/OUP/Rybczynski/ch3.txt
```
The `-name` predicate filters the results of the `find` command to match a specified string. In the previous example, I wished to find all files and repositories that began with `"ch"`, so I used the `-name` predicate with the bash wildcard `*` to tell `find` to filter all paths that match this.

Sample input 2:
```
$ find . -name "*.txt"
./written_2/non-fiction/OUP/Abernathy/ch1.txt
...
./written_2/travel_guides/berlitz2/Vallarta-WhereToGo.txt
$
```
Since file names also contain their extension, I can use the wildcard with the extension to tell `find` to exclusively search for files of a specific type, which in the example was all text (`.txt`) files.
### Predicate `-type`
The `-type` predicate can simplify the workflow of the previous example. Instead of supplying a specific file extension, this predicate takes in a specific file type to search for.

Sample input 1:
```
$ find . -type d
.
./written_2
...
./written_2/travel_guides/berlitz2
```
In the previous example, only directories were outputted from the find since we specified the file type to be `d`. Combined with previous predicates, the `-type` predicate allows the `find` command to quickly narrow down possible output. Other possible inputs to `-type` include: `b, c, d, p, f, l, s, D`, but most commonly `d,f` are used for directory and file searching.

Sample input 2:
```
$ find . -mindepth 2 -type d -name "*z*"
./written_2/non-fiction/OUP/Rybczynski
./written_2/travel_guides/berlitz1
./written_2/travel_guides/berlitz2
```
By combining previous predicates, with the `-type` predicate, we can filter and search for directories of minimum depth 2 with the letter "e" in it. As seen from this example, `find` only matches the `-name` and `-type` predicate to the target file, not the entire path, as other directories with `e` included in their relative paths (`./written_2/non-fiction/OUP/Abernathy`) are not included in the output.