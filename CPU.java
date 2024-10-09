/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CS3790SimpletronV2;

/**
 *
 * @author cnorr
 */
import java.util.Scanner;

public class CPU extends CS3790SimpletronV2 {
// registers
    int accumulator = 0;
    int instructionCounter = 0;
    int instructionRegister = 0;
    int indexRegister = 0;

    public CPU() {

    }

    public void exeSML() {

        try {

            while (true) {

                instructionRegister = memory[instructionCounter];

                int op_code = Math.abs(instructionRegister / 10000); // gives first 2 words

                int operand = instructionRegister % 10000; // gives last 4 words

                switch (op_code) {
                    case Operation_Instruction.READ:
                        read(operand);
                        break;
                    case Operation_Instruction.WRITE:
                        write(operand);
                        break;
                    case Operation_Instruction.LOAD:
                        load(operand);
                        break;
                    case Operation_Instruction.LOADIM:
                        loadIM(operand);
                        break;
                    case Operation_Instruction.LOADX:
                        loadX(operand);
                        break;
                    case Operation_Instruction.LOADIDX:
                        loadIDX(operand);
                        break;
                    case Operation_Instruction.STORE:
                        store(operand);
                        break;
                    case Operation_Instruction.STOREIDX:
                        storeIDX(operand);
                        break;
                    case Operation_Instruction.ADD:
                        add(operand);
                        break;
                    case Operation_Instruction.ADDX:
                        addX(operand);
                        break;
                    case Operation_Instruction.SUBTRACT:
                        subtract(operand);
                        break;
                    case Operation_Instruction.SUBTRACTX:
                        subtractX(operand);
                        break;
                    case Operation_Instruction.DIVIDE:
                        divide(operand);
                        break;
                    case Operation_Instruction.DIVIDEX:
                        divideX(operand);
                        break;
                    case Operation_Instruction.MULTIPLY:
                        multiply(operand);
                        break;
                    case Operation_Instruction.MULTIPLYX:
                        multiplyX(operand);
                        break;
                    case Operation_Instruction.INC:
                        inc();
                        break;
                    case Operation_Instruction.DEC:
                        dec();
                        break;
                    case Operation_Instruction.BRANCH:
                        branch(operand);
                        break;
                    case Operation_Instruction.BRANCHNEG:
                        branchNEG(operand);
                        break;
                    case Operation_Instruction.BRANCHZERO:
                        branchZERO(operand);
                        break;
                    case Operation_Instruction.SWAP:
                        swap(operand);
                        break;
                    case Operation_Instruction.HALT:
                        halt(operand);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid code)" + op_code);

                }

            }
        } catch (IllegalArgumentException e) {
            dumpCore(01); // dump core if bad op code 
        }
    }

    public void read(int operand) {
        
        Scanner input = new Scanner(System.in);
        System.out.print("?");

        String word = input.next().trim();

        try {
            // Try to convert the input to an integer
            int value = Integer.parseInt(word);

            memory[operand] = value;

        } catch (NumberFormatException e) {

        }

        instructionCounter++;

    }

    public void write(int operand) {

        System.out.println(memory[operand]);
        instructionCounter++;

    }

    public void load(int operand) {

        accumulator = memory[operand];

        instructionCounter++;

    }

    public void loadIM(int operand) {

        accumulator = operand; // Load the immediate operand into the accumulator
        instructionCounter++;

    }

    public void loadX(int operand) {

        indexRegister = memory[operand];
        instructionCounter++;

    }

    public void loadIDX(int operand) {

        if (indexRegister >= 0 && indexRegister < memory.length) {
            accumulator = memory[indexRegister];

        } else {
            dumpCore(operand);  // Invalid memory access
        }
        instructionCounter++;

    }

    public void store(int operand) {

        memory[operand] = accumulator;
        instructionCounter++;
        
    }

    public void storeIDX(int operand) {

        if (indexRegister >= 0 && indexRegister < memory.length) {
            memory[indexRegister] = accumulator;
        } else {
            dumpCore(operand);  // Invalid memory access
        }
        instructionCounter++;

    }

    public void add(int operand) {

        accumulator += memory[operand];
        instructionCounter++;

        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1; // Checking for overflow 
            dumpCore(operand);
        }

    }

    public void addX(int operand) {

        accumulator += memory[indexRegister];
        instructionCounter++;
        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1; // Checking for overflow
            dumpCore(operand);
        }

    }

    public void subtract(int operand) {

        accumulator -= memory[operand];
        instructionCounter++;

        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1;// Checking for overflow
            dumpCore(operand);
        }

    }

    public void subtractX(int operand) {

        accumulator -= memory[indexRegister];
        instructionCounter++;

        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1;// Checking for overflow
            dumpCore(operand);
        }

    }

    public void divide(int operand) {

        if (memory[operand] == 0) {
            accumulator = -2; // divide by 0 check 

            dumpCore(operand);
        } else {
            accumulator /= memory[operand];
            instructionCounter++;

        }
    }

    public void divideX(int operand) {

        if (memory[indexRegister] == 0) {
            accumulator = -2; // didvide by 0 check 
            dumpCore(operand);
        } else {
            accumulator /= memory[indexRegister];
            instructionCounter++;

        }

    }

    public void multiply(int operand) {

        accumulator *= memory[operand];
        instructionCounter++;
        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1; // Checking for overflow
            dumpCore(operand);

        }
    }

    public void multiplyX(int operand) {

        accumulator *= memory[indexRegister];
        instructionCounter++;

        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1; // Checking for overflow
            dumpCore(operand);
        }
    }

    public void inc() {

        indexRegister++;
        instructionCounter++;

    }

    public void dec() {

        indexRegister--;
        instructionCounter++;

    }

    public void branch(int operand) {

        instructionCounter = operand;

    }

    public void branchNEG(int operand) {

        if (accumulator < 0) {
            instructionCounter = operand;
        } else {
            instructionCounter++;

        }
    }

    public void branchZERO(int operand) {

        if (accumulator == 0) {
            instructionCounter = operand;

        } else {
            instructionCounter++;

        }
    }

    public void swap(int operand) {

        int temp = accumulator;
        accumulator = indexRegister;
        indexRegister = temp;

        instructionCounter++;

    }

    public void halt(int operand) {
        dumpCore(operand);
        System.exit(0);
    }

    public void dumpCore(int operand) {

        int startPage = operand / 1000; // frist two numbers 
        int endPage = operand % 1000; // last two page  

        System.out.printf("\n\n");
        System.out.printf("Accumulator: %06d\n", accumulator);
        System.out.printf("Instruction Counter %06d\n", instructionCounter);
        System.out.printf("Instruction Register %06d\n", instructionRegister);
        System.out.printf("Index Register %06d\n", indexRegister);
        System.out.println();

        System.out.printf("MEMORY\n\n");

        //  this is used to print the page numbers going from start to end 
        // Loop over the pages from startPage to endPage
        for (int page = startPage; page <= endPage; page++) {
            System.out.printf("Page %02d:\n", page);

            // Print column headers (0 to 9)
            System.out.print("        "); // Indent for row headers
            for (int col = 0; col < 10; col++) {
                System.out.printf("%4s    ", col);
            }
            System.out.println(); // Move to the next line after the headers

            // Loop over the 100 memory addresses for this page
            for (int i = 0; i < 100; i++) {
                if (i % 10 == 0) {
                    // Print row headers (0, 10, 20, ..., 90)
                    System.out.printf("%01d0    ", i / 10);
                }

                int address = page * 100 + i; // Calculate memory address
                System.out.printf("%06d  ", memory[address]);

                // Print a newline every 10 words so when i is 9,19,29... it will make a new line. 
                if (i % 10 == 9) {
                    System.out.println();
                }
            }

            // Print a newline after each page for readability
            System.out.println();
        }
    }
}
