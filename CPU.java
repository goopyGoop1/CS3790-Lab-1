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

    private int accumulator;
    private int instructionCounter;
    private int instructionRegister;
    private int indexRegister;
    CPU cpu = new CPU();
    public CPU() {
       
    }

    public void exeSML() {

        try {

            while (true) {

                instructionRegister = memory[instructionCounter];
                int op_code = instructionRegister / 10000; // gives first 2 words
                int operand = instructionRegister % 10000; // gives last 4 words

                switch (op_code) {
                    case Operation_Instruction.READ:
                        read(operand);
                        break;
                    case Operation_Instruction.WRITE:
                        //write(operand);
                        break;
                    case Operation_Instruction.LOAD:
                        //load(operand);
                        break;
                    case Operation_Instruction.LOADIM:
                        //loadIM(operand);
                        break;
                    case Operation_Instruction.LOADX:
                        //loadX(operand);
                        break;
                    case Operation_Instruction.LOADIDX:
                        //loadIDX(operand);
                        break;
                    case Operation_Instruction.STORE:
                        //store(operand);
                        break;
                    case Operation_Instruction.STOREIDX:
                        //storeIDX(operand);
                        break;
                    case Operation_Instruction.ADD:
                        //add(operand);
                        break;
                    case Operation_Instruction.ADDX:
                        //addX(operand);
                        break;
                    case Operation_Instruction.SUBTRACT:
                        //subtract(operand);
                        break;
                    case Operation_Instruction.SUBTRACTX:
                        //subtractX(operand);
                        break;
                    case Operation_Instruction.DIVIDE:
                        //divide(operand);
                        break;
                    case Operation_Instruction.DIVIDEX:
                        //divideX(operand);
                        break;
                    case Operation_Instruction.MULTIPLY:
                        //multiply(operand);
                        break;
                    case Operation_Instruction.MULTIPLYX:
                        //multiplyx(operand);
                        break;
                    case Operation_Instruction.INC:
                        //inc(operand);
                        break;
                    case Operation_Instruction.DEC:
                        //dec(operand);
                        break;
                    case Operation_Instruction.BRANCH:
                        //branch(operand);
                        break;
                    case Operation_Instruction.BRANCHNEG:
                        //branchNEG(operand);
                        break;
                    case Operation_Instruction.BRANCHZERO:
                        //branchZERO(operand);
                        break;
                    case Operation_Instruction.SWAP:
                        //swap(operand);
                        break;
                    case Operation_Instruction.HALT:
                        //halt(operand);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid code)" + op_code);

                }

                instructionCounter++;

            }
        } catch (IllegalArgumentException e) {
            //dumpCore();
        }
    }

    
    
    public void read(int operand) {

        Scanner input = new Scanner(System.in);
        System.out.println("?");

        String word = input.next().trim();
        

    try {
        // Try to convert the input to an integer
        int value = Integer.parseInt(word);

        
        memory[operand] = value;

    } catch (NumberFormatException e){ 
        //dumpCore
    }

}
  public void write(int operand){
      System.out.println(memory[operand]);
  }  
    
    
    
    
    
}