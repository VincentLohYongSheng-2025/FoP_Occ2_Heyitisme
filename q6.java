package viva1;
import java.util.Scanner;

public class q6 {

    static void q6() {
        Scanner input = new Scanner(System.in);
        System.out.println("enter teh number of logs(between 1 and 100):");
        int T = input.nextInt();

        for (int t = 0; t < T; t++) {
            System.out.println("Enter the log(contain lowercase English letters and digits (0-9): ");
            String log = input.next();

            // convert entire string to lowercase
            log = log.toLowerCase();

            boolean isValid = true;
            int length = 0;

            // Rule A: first char cannot be digit
            if (Character.isDigit(log.charAt(0))) {
                System.out.println("Invalid Log");
                continue;
            }

            for (int i = 0; i < log.length(); i++) {

                char ch = log.charAt(i);

                if (Character.isLetter(ch)) {
                    length++;  // letters add 1
                } 
                else if (Character.isDigit(ch)) {

                    // Rule C: cannot contain '0' or '1'
                    if (ch == '0' || ch == '1') {
                        isValid = false;
                        break;
                    }

                    // Rule B: digit cannot follow another digit
                    else if (Character.isDigit(log.charAt(i - 1))) {
                        isValid = false;
                        break;
                    }

                    // decompression: digit repeats previous char
                    else{
                        int d = ch - '0';
                        length += (d - 1);  
                    }
                    
                }
            }

            if (!isValid) {
                System.out.println("Invalid Log");
            } else {
                System.out.println(length);
            }
        }
    }
}

