; lets say we're using 3 inputs 1, ,2, -1 memory[50] = 55


220050 ; (IC = 0) in memory goes to a perloaded address  memory[50] = 55 to the IR = 55
100051 ; (IC = 1) read a input from the keyboard & store at memory[operand(= 1001)] 	
200051 ; (IC = 2) Load memory[operand(= 1001)] to th ACC = memory[1001] = the user input from 1001  
410007 ; (IC = 3) Branch to memory[operand(7)] if the acc is neg else IC++ {@ input 3 you will branch to memory[7]} 
260000 ; (IC = 4) Store ACC at memory[IR] = memory[IR] =  
380000 ; (IC = 5) INC the IR = IR++ {This is the starting point not an INC IR = 1005} { IR = 1006 } { IR = 1007}
400001 ; (IC = 6) Branch to IC = memory[operand] = IC = memory[1] this is making a loop to store numbers in an 
430000 ; (IC = 7) Swap ACC with IR {ACC = 1007 & IR = -1}     
320050 ; (IC = 8) Sub ACC-=memory[operand(=1000)] {ACC = 1007 - 1005 = 2 & IR = -1}  
420024 ; (IC = 9) Branch to memory[operand(23)] if ACC = 0 else IC++
300050 ; (IC = 10) Swap ACC with IR	{ACC = -1 & IR =2}
430000 ; (IC = 11) Add ACC += memory[Operand] 
210000 ; (IC = 12) ACC = operand 	ACC = 0  IR =(1005 + n(inputs)) - 1005 
390000 ; (IC = 13) DEC IR-- 	ACC = 0 	IR =(1005 + n(inputs)) - 1005 - 1
310000 ; (IC = 14) Addx ACC += memory[IR]	ACC = memory[(1005 + n(inputs)) - 1005 - 1] 
430000 ; (IC = 15) Swap ACC with IR		ACC = (1005 + n(inputs)) - 1005 - 1	IR = 0 
320050 ; (IC = 16)
420021 ; (IC = 17)
300050 ; (IC = 18)
430000 ; (IC = 19)
400013 ; (IC = 20)
430000 ; (IC = 21)
250051 ; (IC = 22)
110051 ; (IC = 23)
450001 ; (IC = 24)
-210001; (IC = 25)
250051 ; (IC = 26)
110051 ; (IC = 27)
450001 ; (IC = 28)