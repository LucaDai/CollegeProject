;Luca Dai
;mzd0108
;Using Dr. Li's template to complete this project.
;A simple MASM program to create a queue for input
;which place the first variable in the place of shift

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword

.data
	shift dword 3
	input byte 1,2,3,4,5,6,7,8
	output byte LENGTHOF input DUP(?)

.code
	main proc

		;This  loop will iterate length -shift times. 
		mov ecx, LENGTHOF input 
		sub ecx, shift
		;Start reading from input at 0 
		mov esi, 0
		;Start writing at shift
		mov edi, shift

		l1:
			;Move the value from input into al
			mov al, input[esi]
			;Move the value from al into output
			mov output[edi], al
			;Increment the input index
			inc esi
			;Increment the output index
			inc edi

		loop l1
		;This  loop will iterate shift times
		mov ecx, shift
		;Start writing at index 0
		mov edi, 0
		;Start reading at index length -shift. 
		mov esi, LENGTHOF input
		sub esi, shift

		;(this loop will place the 'wrapped' values)
		l2:
			;Move the value from input into al
			mov al, input[esi]
			;Move the value from al into output
			mov output[edi], al
			;Increment the input index
			inc esi
			;Increment the output index
			inc edi
		loop l2
		
		invoke ExitProcess, 0
	main endp
end main

