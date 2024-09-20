/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CS3790SimpletronV2;

import java.util.Scanner;

/**
 *
 * @author cnorr
 */
public class IO {

    public IO() {
        System.out.println("I0");
    }

    private Scanner input = new Scanner(System.in);

    public String input_prompt(String prompt) {
        System.out.println(prompt);
        return input.nextLine().trim();
    }

    public int read_int(String prompt) {
        System.out.print(prompt);
        while (!input.hasNextInt()) {
            System.out.println("?");
            input.next();
        }
        return input.nextInt();
    }

    public void printError(String message) {
        System.out.println("Error: " + message);
    }

    public void printOutput(String message) {
        System.out.println(message);
    }

}
