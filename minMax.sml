; Assume all inputs are positive. The array starting point is saved at memory[50] = 55.  Min is saved at memory[52]. Max is saved at memory[53]. Memory[54] is used to save the ending
; of the array. When -1 is entered the inputs to the array will end. 

220050 ; (IC = 00) Loadx IR will be loaded with the starting address of the array
100051 ; (IC = 01) Read an input from the from the keyboard and save it at memory[51] as a temp holding spot
200051 ; (IC = 02) Load the ACC with input 
410007 ; (IC = 03) BranchNEG this will break the loop when -1 is entered. 
260000 ; (IC = 04) StoreIDX store the ACC at memory[IR] 
380000 ; (IC = 05) Inc IR++
400001 ; (IC = 06) Branch to 1 to loop for user inputs 
390000 ; (IC = 07) Dec  IR-- this is needed to get back to last input of the array 
430000 ; (IC = 08) Swap 
250054 ; (IC = 09) Store the last address of the array at memory[54]
320050 ; (IC = 10) Subtract ACC-= memory[50] to check for 0 inputs
420044 ; (IC = 11) BranchZERO if no inputs Halt
200054 ; (IC = 12) Load memory[54](last address of the array) into the ACC.
430000 ; (IC = 13) Swap 
230000 ; (IC = 14) LoadIDX ACC will hold last number of the array
250052 ; (IC = 15) Store ACC at memory[52] that holds the current min
390000 ; (IC = 16) Dec IR-- 
430000 ; (IC = 17) Swap 
320050 ; (IC = 18) Subtract ACC-=memory[50] 
420027 ; (IC = 19) BranchZERO if at the last number in array 
300050 ; (IC = 20) Add memory[50] 
430000 ; (IC = 21) Swap
330000 ; (IC = 22) SubX 
410016 ; (IC = 23) BranchNEG loop back IC = 16 
230000 ; (IC = 24) LoadIDX load current IR in ACC 
250052 ; (IC = 25) Store new min in ACC (memory[52])
400016 ; (IC = 26) Branch back up to IC = 16 to loop 
200054 ; (IC = 27) Load ACC with last position of array (memory[54])  
430000 ; (IC = 28) Swap 
230000 ; (IC = 29) LoadIDX ACC now holds last number in array
250053 ; (IC = 30) Store as max at memory[53] 
390000 ; (IC = 31) Dec IR-- 
430000 ; (IC = 32) Swap
320050 ; (IC = 33) Subtract memory[50] checking for last position in the array 
420042 ; (IC = 34) BranchZERO when no numbers left in array 
300050 ; (IC = 35) Add memory[50] 
430000 ; (IC = 36) Swap 
330000 ; (IC = 37) SubX 
410031 ; (IC = 38) BranchNEG to IC= 31 to loop the array 
230000 ; (IC = 39) LoadIDX ACC = memory[IR] 
250053 ; (IC = 40) Store new largest at memory[53] 
400031 ; (IC = 41) Branch back to check if at last number of the array
110052 ; (IC = 42) Print min 
110053 ; (IC = 43) Print max 
450001 ; (IC = 44) Halt and dump core pages 0-1  

