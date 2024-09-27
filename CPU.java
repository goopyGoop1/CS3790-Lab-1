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

    int accumulator = 0;
    int instructionCounter = 0;
    int instructionRegister = 0;
    int indexRegister = 0;

    public CPU() {

    }

    public void exeSML() {

        try {

            while (true) {

               // System.out.println("This is the IC  " + instructionCounter);
                instructionRegister = memory[instructionCounter];
                // System.out.println(instructionCounter);

                int op_code = instructionRegister / 10000; // gives first 2 words
                //System.out.println("This is the OP CODE "+ op_code);
                int operand = instructionRegister % 10000; // gives last 4 words
                //System.out.println("This is the Operand  " + operand);
                // System.out.println("This is the indexREG b4 the switch "+ indexRegister);

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
            //dumpCore(operand);
        }
    }

    public void read(int operand) {
       // System.out.println("This is read the operand is " + operand);
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
       // System.out.println("This is write the operand is " + operand);
        System.out.println(memory[operand]);
        instructionCounter++;
        // dumpCore(0);
    }

    public void load(int operand) {
       // System.out.println("This is load the operand is " + operand);
        accumulator = memory[operand];

        //System.out.println("This is the accumulator " + accumulator);
        instructionCounter++;
        //dumpCore(0);
    }

    public void loadIM(int operand) {
        //System.out.println("This is loadIM the operand is " + operand);
        accumulator = operand; // Load the immediate operand into the accumulator
        instructionCounter++;
        //dumpCore(0);
    }

    public void loadX(int operand) {
       // System.out.println("This is loadX the operand is " + operand);
        indexRegister = memory[operand];
        instructionCounter++;
       // dumpCore(0);

    }

    public void loadIDX(int operand) {
        //System.out.println("This is loadIDX the operand is " + operand);
        if (indexRegister >= 0 && indexRegister < memory.length) {
            accumulator = memory[indexRegister];
          //dumpCore(0);
        } else {
            dumpCore(operand);  // Invalid memory access
        }
        instructionCounter++;
       //dumpCore(0);
    }

    public void store(int operand) {
       // System.out.println("This is store the operand is " + operand);
        memory[operand] = accumulator;
        instructionCounter++;
        //dumpCore(0);
    }

    public void storeIDX(int operand) {
        //System.out.println("This is storeIDX the operand is " + operand);
        if (indexRegister >= 0 && indexRegister < memory.length) {
            memory[indexRegister] = accumulator;
        } else {
            dumpCore(operand);  // Invalid memory access
        }
        instructionCounter++;
        //dumpCore(0);

    }

    public void add(int operand) {
        //System.out.println("This is add the operand is " + operand);
        accumulator += memory[operand];
        instructionCounter++;
        //dumpCore(10);
        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1;
            dumpCore(operand);
        }

    }

    public void addX(int operand) {
       // System.out.println("This is addX the operand is " + operand);
        accumulator += memory[indexRegister];
        instructionCounter++;
        //dumpCore(0);

    }

    public void subtract(int operand) {
       // System.out.println("This is sub the operand is " + operand);
        accumulator -= memory[operand];
        instructionCounter++;
        //dumpCore(10);
        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1;
            dumpCore(operand);
        }

    }

    public void subtractX(int operand) {
        //System.out.println("This is subX the operand is " + operand);
        accumulator -= memory[indexRegister];
        instructionCounter++;
        //dumpCore(0);
        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1;
            dumpCore(operand);
        }

    }

    public void divide(int operand) {
       // System.out.println("This is divide the operand is " + operand);
        if (memory[operand] == 0) {
            accumulator = -2;

            dumpCore(operand);
        } else {
            accumulator /= memory[operand];
            instructionCounter++;
           // dumpCore(0);
        }
    }

    public void divideX(int operand) {
        //System.out.println("This is diviedX the operand is " + operand);
        if (memory[indexRegister] == 0) {
            accumulator = -2;
            dumpCore(operand);
        } else {
            accumulator /= memory[indexRegister];
            instructionCounter++;
           // dumpCore(0);
        }

    }

    public void multiply(int operand) {
       // System.out.println("This is multi the operand is " + operand);
        accumulator *= memory[operand];
        instructionCounter++;
        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1;
            dumpCore(operand);
          //  dumpCore(0);
        }
    }

    public void multiplyX(int operand) {
        //System.out.println("This is MultiX the operand is " + operand);
        accumulator *= memory[indexRegister];
        instructionCounter++;
      // dumpCore(0);
        if (accumulator < -999999 || accumulator > 99999) {
            accumulator = -1;
            dumpCore(operand);
        }
    }

    public void inc() {
       // System.out.println("This is INC");
        indexRegister++;
        instructionCounter++;
        //dumpCore(0);
    }

    public void dec() {
        //System.out.println("This is DEC");
        indexRegister--;
        instructionCounter++;
        //dumpCore(0);
    }

    public void branch(int operand) {
       // System.out.println("This is branch the operand is " + operand);
        instructionCounter = operand;
       // dumpCore(0);

    }

    public void branchNEG(int operand) {
//        System.out.println("This is branchNEG the operand is " + operand);
//        System.out.println("Accumulator: " + accumulator);
//        System.out.println("This is the IR" + indexRegister);
        if (accumulator < 0) {
            instructionCounter = operand;
        } else {
            instructionCounter++;
           // dumpCore(0);
        }
    }

    public void branchZERO(int operand) {
       // System.out.println("This is branchZERO the operand is " + operand);
        if (accumulator == 0) {
            instructionCounter = operand;
        } else {
            instructionCounter++;
           // dumpCore(0);
        }
    }

    public void swap(int operand) {
//        System.out.println("This is swap the operand is " + operand);
//        System.out.println("Before swap: Accumulator = " + accumulator + ", IndexRegister = " + indexRegister);
        int temp = accumulator;
        accumulator = indexRegister;
        indexRegister = temp;
//        System.out.println("After swap: Accumulator = " + accumulator + ", IndexRegister = " + indexRegister);
        instructionCounter++;
       // dumpCore(0);
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
