/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CS3790SimpletronV2;

/**
 *
 *
 */
public class Memory {

    
    private int[] memory; // make memory private so the getter/setters is the only way to change memory 
    
    
    
    
    public Memory(int size) { // this constructor sets the size of the memory 
        memory = new int[size];
        System.out.println("Mem 1");
        
    }

    public void setWord(int address, int word) { // takes the address and input places it in memory
        memory[address] = word;

    }

    public int getMemory(int address) { // returns the contents of address. 
        return memory[address];
    }

    
    
    // prints the whats in memory by page. 
    public void printMemory(int first_page, int last_page) {
        for (int page = first_page; page <= last_page; page++) {
            System.out.println("PAGE # " + String.format("%02d", page));
            for (int i = page * 100; i < (page + 1) * 100; i += 10) {
                for (int j = i; j < i + 10; j++) {
                    System.out.printf("%06d ", memory[j]);
                }
                System.out.println();

            }

        }
    
}
}