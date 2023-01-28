# CSE 15L Blog
### Author: Andrew Yang
## Lab Report 2
### Part 1

Writing the `StringServer` class for this lab requires a web server to be deployed. I began by cloning the fork of the `wavelet` [server](https://github.com/andronius-yang/wavelet) used in lab 2 to the my local development environment.

![Cloned local development](/img/lab2/1.png)

Here, the important file is `StringServer.java`. The code is as follows:
```java
import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    String displayString;

    public Handler() {
        displayString = new String();
    }

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add-message")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                displayString += parameters[1] + "\n";
            }
        }
        return displayString;
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);
        Server.start(port, new Handler());
    }
}
```

To run the server locally, I compiled files in the folder and ran `StringServer.java` wth the port 8000.
```
javac *.java
java StringServer 8000
```
![Running the server](/img/lab2/2.png)

Visiting the server gives an empty page, which is to be expected. This is because `displayString` is initialized to be empty, which is then returned by the Handler.

![Visiting the server](/img/lab2/3.png)

When I added the path `/add-message?s=Hello World!` to the end of the previous URL, the screen now displays "Hello World!"

![Adding Hello World](/img/lab2/4.png)

When I visited the path, the `handleRequest(URI url)` method is called. The parameter `url` is equal to `new URI("http://localhost:8000/add-message?s=Hello World!")`. Then, the first statement separates the path from the url, which is `/add-message`. This if statement passes, and then the method handles the query, which is the string behind the question mark in the url. The query is first split based on the equals sign and assigned to the string array `parameters`, which stores `{"s", "Hello World!"}` from the split. The if statement then sees that `parameters[0]` is equal to `"s"`, and thus adds the string stored in the first index to `displayString`. It is worth noting here that the endline character `\n` is also appended to `displayString` so that the next appended string can be displayed on a new line. By the end of this, `displayString = "Hello World!\n"` and this is returned by the method to be displayed on screen.

Accessing `http://localhost:8000/add-message?s=This is the second addition.` now displays these two messages on string.

![Second input](/img/lab2/5.png)

The flow is almost entirely similar to the first. The only difference is that the URI passed to the `handleRequest(URI url)` is now equal to `new URI("http://localhost:8000/add-message?s=This is the second addition.")`. Thus, `parameters = {"s", "This is the second addition."}`, meaning that a different string is appended to `displayString`. By the end of this call, `displayString = "Hello World!\nThis is the second addition.\n`, and this is what is being displayed on screen.

### Part 2
The bug I will be exploring will be in method 'reverseInPlace(int[] arr)' in file `ArrayExamples.java`. I cloned the files `ArrayExamples.java` and `ArrayTests.java` from my [fork](https://github.com/andronius-yang/lab3) to the local development environment.
I also included the two files used for JUnit testing, `hamcrest-core-1.3.jar` and `junit-4.13.2.jar` in the path `/cse15lwi23/report2files/lib`.
![Local Development Environment](/img/lab2/6.png)

The two tests I decided to test are as follows:
```java
import static org.junit.Assert.*;

import org.junit.*;

public class ArrayTests {
  @Test
  public void testReversedInPlace() {
    int[] input = {1,2,3};
    ArrayExamples.reverseInPlace(input);
    assertArrayEquals(new int[] {3,2,1}, input);
  }

  @Test
  public void testReversePalindrome() {
    int[] input = {0,1,0};
    ArrayExamples.reverseInPlace(input);
    assertArrayEquals(new int[] {0,1,0}, input);
  }
}
```
To run the tests, I entered the following into my command line:
```
javac -cp ".;lib/hamcrest-core-1.3.jar;lib/junit-4.13.2.jar" *.java
java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore ArrayTests
```
Which resulted in the following errors:
![Testing errors](/img/lab2/7.png)
It seems like the actual array after reversing was `{3,2,3}` instead of the expected `{1,2,3}`. The faulty code before is:
```java
static void reverseInPlace(int[] arr) {
    for(int i = 0; i < arr.length; i += 1) {
        arr[i] = arr[arr.length - i - 1];
    }
}
```
Fixing the code gives:
```java
static void reverseInPlace(int[] arr) {
    for(int i = 0; i < arr.length/2; i += 1) {
        int tmp = arr[i];
        arr[i] = arr[arr.length - i - 1];
        arr[arr.length-i-1] = tmp;
    }
}
```
The previous bug was caused by the code directly overwriting elements in the array instead of swapping them, and by the fact that the for loop ran for the entire length of the array, which would've caused it to double swap.
To fix it, firstly the for loop was adjusted so that it only ran half of the array so that only one swap is done. A temporary variable was introduced to hold the value of one of the swapped variables being overwritten and is later used to provide the value for the other variable.
Running the tests again gave no errors.
![Fixed tests](/img/lab2/8.png)

### Part 3
Something new I learnt this week was how Java servers can handle queries and paths. The method `URI.getPath()` separates not only the path from the URL, but also from the queries that follow it. Then, to process queries, I simply needed to use the `String.split()` function to split based on the equals sign to get not only the query name, but also the query value.