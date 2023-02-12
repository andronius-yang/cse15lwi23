# CSE 15L Blog
## Lab Report 3
### Author: Andrew Yang
## Setting up the environment
I downloaded the dataset `written_2` from the GitHub [link](https://github.com/ucsd-cse15l-w23/skill-demo1-data) provided in Skill Demonstration 1 and placed it under this repository with path `cse15lwi23/report3files/written_2/`. All command options are searched using the command `man grep` in the terminal, and all commands below are done in the working directory `report3files/`
## Command `grep`
### Option `-l`
Sample input 1:
```
$ grep -l "reporting" writen_2/non-fiction/OUP/Abernathy/ch15.txt
written_2/non-fiction/OUP/Abernathy/ch15.txt
```
The `-l` option tells the `grep` command to output the names of the file that contains a match to the string provided. In the above sample, I knew that `ch15.txt` contained the word "reporting" and hence the command outputted the exact path of the file.

Sample input 2:
```
$ grep -l "reporting" writen_2/non-fiction/OUP/Abernathy/ch14.txt

$
```
When no corresponding expression is found, in this case in the `ch14.txt` file, the option simply doesn't output anything.
### Option `-L`
Sample input 1:
```
$ grep -L "reporting" writen_2/non-fiction/OUP/Abernathy/ch14.txt
written_2/non-fiction/OUP/Abernathy/ch14.txt
```
The `-L` option is the exact opposite of the previous option. It tells the `grep` command to output the file paths of files who do not match the string provided. Hence, this explains the output in sample input 1.
An interesting behavior is seen when both options are supplied to the command.

Sample input 2,3:
```
$ grep -l -L "reporting" writen_2/non-fiction/OUP/Abernathy/ch14.txt writen_2/non-fiction/OUP/Abernathy/ch15.txt
written_2/non-fiction/OUP/Abernathy/ch14.txt

$ grep -L -l "reporting" writen_2/non-fiction/OUP/Abernathy/ch14.txt writen_2/non-fiction/OUP/Abernathy/ch15.txt
written_2/non-fiction/OUP/Abernathy/ch15.txt
```
Here, the only difference between sample input 2 and 3 is the order in which the options are supplied to teh command. In both cases, it seems that the last option supplied to the command was actually the one evaluated in the end. It didn't process both options, nor did it choose the first option.
### Option `-n`
Sample input 1:
```
$ grep -n "reporting" written_2/non-fiction/OUP/Abernathy/ch15.txt
16:In any case, since the 1970s[...]
```
This option provides the specific line number of where the matching spring was found in the file. In this case, it pointed to the 16th line in the file `ch15.txt`.

Sample input 2:
```
$ grep -n "reporting" written_2/non-fiction/OUP/Abernathy/ch14.txt

$
```
Similar to previous options, when supplied with files that do not contain the expression, the option doesn't output anything.
### Option `r`
The `-r` option allows for recursive searching in a given path combined with other options. This makes it especially useful since previously this was achieved through using `find` and `xargs` commands.

Sample input 1:
```
$ grep -r -l "reporting"
written2/non-fiction/OUP/Abernathy/ch15.txt
written2/non-fiction/OUP/Abernathy/ch8.txt
written2/non-fiction/OUP/Berk/CH4.txt
written2/non-fiction/OUP/Kauffman/ch3.txt
written_2/travel_guides/berlitz2/Barcelona-WhereToGo.txt
written_2/travel_guides/berlitz2/China-WhereToGo.txt
```
Here, since no path was supplied to the `grep` command, it default used the current working directory `cse15lwi23/report3files/` as the path and recursively searched the files within it to match `"reporting"`, and outputted the paths of these files using the `-l` tag.

Sample input 2:
```
$ grep -r -n "test" ../report2files
../report2files/ArrayTests.java:7:  public void testReversedInPlace() {
../report2files/ArrayTests.java:14:  public void testReversePalindrome() {
```
Here, I supplied the path `../report2files` which contained the Java files used for the last report and asked `grep` to search for the string `"test"`. Since I supplied the `-r` option, it searched recursively through all files under that directory, and since I supplied the `-n` option, it was able to tell me the line number where the match was found.