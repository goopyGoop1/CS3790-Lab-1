/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package CS3790SimpletronV2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

/**
 *
 * @author cnorr
 */
public class CS3790SimpletronV2 {

    public int[] memory = new int[10000];
    //  int accumulator, instruction_counter, instruction_registerm, index_register;

    public CS3790SimpletronV2() {
        print("*** Welcome to Simpletron V2! ***");
        print("***");

//        this.instruction_counter = instruction_counter;
//        this.instruction_registerm = instruction_register;
    }

    public static void print(String x) {
        System.out.println(x);
    }

    /*this function will ask if you have a file or not. 
  it takes it in, trims the white space and makes sure the anwers is in upper case
  if they do have a file (y) it calls load_PRG_file that takes in a string of the filename
    if no file (n) then it calls read_keyboard()  to read inputs from the user. 
    
     */
    public void load_program() {
        Scanner input = new Scanner(System.in);
        print("Do you have a file that contains your SML program (Y/N) ?");
        String anwser = input.next().toUpperCase().trim();
        System.out.println();

        if (anwser.equals("Y")) {
            print("What is the filename?");
            input.nextLine();
            String filename = input.nextLine().trim();
            load_PRG_file(filename);

        } else if (anwser.equals("N")) {
            System.out.println("*** Please enter your program one instruction( or data word ) at a time        ***");
            System.out.println("*** I will type the location number and a question mark (?). You then          ***");
            System.out.println("*** type the word for that location. Type the word GO to execute  your program ***");
            read_keyboard();
        }

    }

    /* this function takes in the file name and saves it to the memory while the file has a next line
looks for a semicolon that marks the comment in the SML file. if found gives index to be used for the substring of only numbers
takes in the input in as a string and convertes it to an int with Integer.parseInt(line)   
     */
    public void load_PRG_file(String filename) {
        try {
            Scanner file_scan = new Scanner(new File(filename));
            int address = 0;
            while (file_scan.hasNextLine()) {

                String line = file_scan.nextLine().trim();

                int index_semicolon = line.indexOf(";");
                if (index_semicolon != -1) {
                    line = line.substring(0, index_semicolon).trim();

                }
                if (!line.isEmpty()) {
                    try {
                        int sml_code = Integer.parseInt(line);
                        memory[address] = sml_code;
                        address++;
                    } catch (NumberFormatException e) {
                        //dumpCore(); 

                    }

                }

            }
            file_scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dump");
        }

    }

    /*
    the function is set to read the inputs from the user
    the printf("%05d ? ", address) ensures that the print will always have 5 numbers in it padding with 0
    if the user enters go(equalsIgnoreCase) it will break out of the loop. 
    the input is read as a string so i've used Integer.parseInt(keys) to convert. if it's not a int it will dump core
    
     */
    public void read_keyboard() {
        Scanner input = new Scanner(System.in);
        String keys;
        int address = 0;
        while (true) {

            System.out.printf("%05d ? ", address);

            keys = input.nextLine().trim();
            if (keys.equalsIgnoreCase("GO")) {
                break;
            }

            try {
                int sml_code = Integer.parseInt(keys);
                memory[address] = sml_code;
                address++;
            } catch (NumberFormatException e) {
                //dumpCore(); 

            }

        }
    }

    public static int string_int(String string) {
        int value = 0;
        try {
            value = Integer.parseInt(string);

        } catch (NumberFormatException e) {
            //dumpCore(); 

        }
        return value;
    }

    public static void main(String[] args) {
        CS3790SimpletronV2 SimpletronV2 = new CS3790SimpletronV2();
        SimpletronV2.load_program();
//        CPU cpu = new CPU();
//        cpu.exeSML();

        for (int i = 0; i < 10; i++) {
            System.out.println(SimpletronV2.memory[i]);
        }
    }

}
