Question 3

**Problem :** 

Write a program that can generate number patterns. The program will first read an integer T, which is the number of inquiries (test cases). The value of T will be between 1 and 50. For each inquiry, the program will read two inputs: a Height H and a Style S. The height H is an integer between 1 and 9. The style S is a single uppercase character, either 'A' or 'P'. If the style is 'A', the program must generate an angled staircase pattern. For style 'A', the program will print H rows. On row i (from 1 to H), it will print the digit i, repeated i times. If the style is 'P', the program must generate a centered pyramid pattern. For style 'P', the program will also print H rows. Then it must print the numbers in ascending order from 1 up to i.\
` `After that, it must print the numbers in descending order from i − 1 back down to 1.

If the input is a Height (H) of 4 and Style (S) of 'P':  

Row 1 (i=1): 3 spaces + 1 -> 1 

Row 2 (i=2): 2 spaces + 12 + 1 -> 121  

Row 3 (i=3): 1 space + 123 + 21 -> 12321  

Row 4 (i=4): 0 spaces + 1234 + 321 -> 1234321

**Solution :** 

This program generates number patterns based on a given height and style code.\
` `It supports two styles:

- Style 'A' (Angled) – prints a right-angled number staircase.
- Style 'P' (Pyramid) – prints a centered palindromic number pyramid.

The program first reads how many test cases (inquiries) there are, then for each test case it reads the height and the style, builds the corresponding pattern, and finally prints all patterns at once.



Part 1: import Scanner and get the inputs ( number of inquiries)

**package viva1;**

**import java.util.Scanner;**

**public class q3 {**

`    `**static void q3(){**

`        `**Scanner input = new Scanner(System.in);**

`        `**System.out.println("enter the number of inquiries (between 1 - 50):");**

`        `**int T = input.nextInt();**

`        `**int[] heights = new int[T];**

`        `**char[] styles = new char[T];**

- The Scanner is imported and given variable name input 
- The program will ask for the number of inquiries ( T ) 
- As the question requested to collect all inputs and then proceed to printing patterns according to the input collected , arrays is applied.
- Two arrays is initialized , with the variable name heights (int)  and styles(char)

Part 2: Collecting all the input ( height , style based on the number of inquiries )

**for(int i = 0 ;i < T ; i++){**

`            `**System.out.println("enter the height (from 1 to 9): ");**

`            `**heights[i]= input.nextInt();**

`            `**System.out.println("enter the style ('A' for Angled , 'P' for Pyramid): ");**

`            `**styles[i] = input.next().charAt(0);**

`        `**}**

- For loop is created and should be run to collect all the sets of inputs ( consisting height and style as 1 set ) based on the number of inquiries
- The inputs , heights is then stored in the array (heights) with the index i and same for styles ( stored in styles with index i)

Part 3: printing the pattern using for loop 

**for(int i = 0 ; i < T ; i++){**

`            `**int H = heights[i];**

`            `**char S = styles[i];**

`            `**//check the condition of the variables , H and T**

`            `**if((H<=9 && H >=1)&&(T <= 50 && T >=1)){**

`                `**if(S == 'A'){**

`                    `**for(int a = 1 ; a <= H ; a++){**

`                        `**for(int b = 1; b<=a;b++){**

`                            `**System.out.print(a);**

`                        `**}**

`                        `**System.out.println();**
**\


`                    `**}**

`                `**}**
**\


`                `**if(S=='P'){**

`                    `**//print the spacing** 

`                    `**for (int r = 1; r <= H; r++) {**

`                        `**// Print leading spaces**

`                        `**for (int s = 1; s <= H - r; s++) {**

`                            `**System.out.print(" ");**

`                        `**}**

`                        `**// Print ascending numbers**

`                        `**for (int d = 1; d <= r; d++) {**

`                            `**System.out.print(d);**

`                        `**}**

`                        `**// Print descending numbers**

`                        `**for (int d = r - 1; d >= 1; d--) {**

`                            `**System.out.print(d);**

`                        `**}**

`                        `**System.out.println();**

`                    `**}**

`                `**}**
**\


`            `**}**


Part 3.1 (checking the condition )

- If statement and logic operators are used to check whether the input entered is align with our condition stated , if yes , the program continues to generate and print the pattern

Part 3.2: Generate and print (‘A’  right-angled staircase)

- Outer loop is setup with variable ‘a’ to control the rows throughout the printing process. When a = 1 , the program will print row 1 , when a = 2 , the program will print row 2…
- Inner loop is setup with variable ‘b’ to control how many times the number will print in that row
- The condition b <=a is used , so that the number of repetitions depends on the row (a) and can align the current row number with the number printing

  When a = 1 , b runs from 1 to 1 = prints 1 time (1)

  When a = 2 , b runs from 1 to 2 = prints 2 times  (22)

- System.out.println(); is used to move from one row to the other

Part 3.3: Generate and print (‘P’ pyramid)

- Printing the pyramid is separated into three inner loops : spacing loops , ascending loops and descending loops
- The outer loop is setup with variable ‘r’ to control the rows throughout the printing process. When r = 1, the program will edit row 1 , when r = 2 , the program will edit row 2
- Spacing inner loop is setup using variable ‘s’ to print spaces before the numbers , the number of spaces depends on the row H-r. 

  When r =1: H-r = 3 spaces

  When r =2 : H-r=2 spaces

- Ascending inner loops is setup using variable ‘d’ to print increasing number from 1 up to r for the left side of the row until the peak number

  When r = 1 : prints 1 

  When r = 2: prints 2

- Descending inner loops is setup using variable ‘d’ to print decreasing number from (r-1) to r for the right side of the row after the peak number
- Starting from r-1 instead of r is to avoid repeating the peak number twice

When r=1: from r-1=0 :  loop does not run → no descending numbers\
`	`When r=2: prints 1

When r=3: prints 21

When r=4: prints 321

- After running all the inner loops , System.out.println(); is executed to move to the next row and generate the output














**Sample Input & Output :** 

|<p>**enter the number of inquiries (between 1 - 50):**</p><p>**3**</p><p>**enter the height (from 1 to 9):** </p><p>**4**</p><p>**enter the style ('A' for Angled , 'P' for Pyramid):** </p><p>**P**</p><p>**enter the height (from 1 to 9):** </p><p>**5**</p><p>**enter the style ('A' for Angled , 'P' for Pyramid):** </p><p>**A**</p><p>**enter the height (from 1 to 9):** </p><p>**3**</p><p>**enter the style ('A' for Angled , 'P' for Pyramid):** </p><p>**P**</p><p>`   `**1**</p><p>`  `**121**</p><p>` `**12321**</p><p>**1234321**</p><p>**1**</p><p>**22**</p><p>**333**</p><p>**4444**</p><p>**55555**</p><p>`  `**1**</p><p>` `**121**</p><p>**12321**</p><p></p>|
| :- |












**Source Code :** 

package viva1;

import java.util.Scanner;

public class q3 {

`    `static void q3(){

`        `Scanner input = new Scanner(System.in);

`        `System.out.println("enter the number of inquiries (between 1 - 50):");

`        `int T = input.nextInt();

`        `int[] heights = new int[T];

`        `char[] styles = new char[T];



`        `for(int i = 0 ;i < T ; i++){

`            `System.out.println("enter the height (from 1 to 9): ");

`            `heights[i]= input.nextInt();

`            `System.out.println("enter the style ('A' for Angled , 'P' for Pyramid): ");

`            `styles[i] = input.next().charAt(0);

`        `}

`        `for(int i = 0 ; i < T ; i++){

`            `int H = heights[i];

`            `char S = styles[i];

`            `//check the condition of the variables , H and T

`            `if((H<=9 && H >=1)&&(T <= 50 && T >=1)){

`                `if(S == 'A'){

`                    `for(int a = 1 ; a <= H ; a++){

`                        `for(int b = 1; b<=a;b++){

`                            `System.out.print(a);

`                        `}

`                        `System.out.println();



`                    `}

`                `}



`                `if(S=='P'){

`                    `//print the spacing 

`                    `for (int r = 1; r <= H; r++) {

`                        `// Print leading spaces

`                        `for (int s = 1; s <= H - r; s++) {

`                            `System.out.print(" ");

`                        `}

`                        `// Print ascending numbers

`                        `for (int d = 1; d <= r; d++) {

`                            `System.out.print(d);

`                        `}

`                        `// Print descending numbers

`                        `for (int d = r - 1; d >= 1; d--) {

`                            `System.out.print(d);

`                        `}

`                        `System.out.println();

`                    `}

`                `}



`            `}



`        `}

`    `}

}

