# CSE 15L Blog
### Author: Andrew Yang
## Lab Report 2
Remote access for CSE 15L is fairly straightforward.

### Installing Visual Studio Code
Firstly, access to a shell is required. Installing Visual Studio Code (abbreviated VSCode) is preferred for this step since it also serves as a text editor / IDE that is suitable for many later tasks in this course.

To install, navigate to the official [website](https://code.visualstudio.com/download) and follow the instructions there to install VSCode for various operating systems.

![Image](/img/1.png)

This is a screenshot of VSCode installed onto my computer. It will look different for different users upon installation. Simply follow the "get started" instructions on VSCode to finish initialization. The specifics of configuring VSCode is out of scope for this current lab session.

### Setting Up Remote Access
Most operating systems have a default shell to execute commands. As my operating systems is Windows, I will be using `cmd.exe` for remote access.

Remote access with the school's computers using a student account requires the command `ssh`. Once `cmd.exe` is opened from the search bar, type in `ssh sampleAccount@ieng6.ucsd.edu`. Here make sure to replace `sampleAccount` with the account name acquired from the school's [Educational Technology Services](https://sdacs.ucsd.edu/~icc/index.php). 

Once the command runs, it will eventually prompt you to enter your password. It should be noted here that entering passwords in the shell does not display dots or asterisks to indicate anonymous input. Nothing will show up on the terminal. Simply enter the password and press enter to log in.

It should also be noted here that logging in the first time will also require you to enter confirmation (in the form of entering `yes` or `no` into the terminal), and will most likely result in failure due to the password not being reset. To fix this, simply go to [Educational Technology Services](https://sdacs.ucsd.edu/~icc/index.php), look up your CSE 15L account, and traverse to Global Password Reset to reset the password. Here is a helpful [tutorial link](https://docs.google.com/document/d/1hs7CyQeh-MdUfM9uv99i8tqfneos6Y8bDU0uhn1wqho/edit) for resetting passwords.

Once the password is successfully entered, remote access is now established.

![Image](/img/2.png)

### Testing Out Commands
The last step is to confirm that commands work. For my personal account, I was able to look around my directory (which has nothing but the `perl5` folder) and create my own directory with a file in it.

![Image](/img/3.png)

I used the `ls` and `mkdir` commands to create the directory `lab1` and also created the text file `data.txt` with the words `hello world!` in it. I then used `cat` to ensure that text was successfully inputted into the file.

To end the remote session, I entered `exit` and finished the first lab.