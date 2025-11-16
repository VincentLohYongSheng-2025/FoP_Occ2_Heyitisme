/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viva1;
import java.util.Scanner;

public class q3 {
    static void q3(){
        Scanner input = new Scanner(System.in);
        System.out.println("enter the number of inquiries (between 1 - 50):");
        int T = input.nextInt();
        int[] heights = new int[T];
        char[] styles = new char[T];
        
        for(int i = 0 ;i < T ; i++){
            System.out.println("enter the height (from 1 to 9): ");
            heights[i]= input.nextInt();
            System.out.println("enter the style ('A' for Angled , 'P' for Pyramid): ");
            styles[i] = input.next().charAt(0);
        }
        for(int i = 0 ; i < T ; i++){
            int H = heights[i];
            char S = styles[i];
            //check the condition of the variables , H and T
            if((H<=9 && H >=1)&&(T <= 50 && T >=1)){
                if(S == 'A'){
                    for(int a = 1 ; a <= H ; a++){
                        for(int b = 1; b<=a;b++){
                            System.out.print(a);
                        }
                        System.out.println();
                        
                    }
                }
                
                if(S=='P'){
                    //print the spacing 
                    for (int r = 1; r <= H; r++) {
                        // Print leading spaces
                        for (int s = 1; s <= H - r; s++) {
                            System.out.print(" ");
                        }

                        // Print ascending numbers
                        for (int d = 1; d <= r; d++) {
                            System.out.print(d);
                        }

                        // Print descending numbers
                        for (int d = r - 1; d >= 1; d--) {
                            System.out.print(d);
                        }

                        System.out.println();
                    }
                }
                
            }
            
        }
    }
} 
