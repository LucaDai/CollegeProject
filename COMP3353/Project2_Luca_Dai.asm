;Project 2
;Luca Dai
;2/12/2021
;reference: Dr. Li's Template

.386
.model flat,stdcall
.stack 4096
ExitProcess proto,dwExitCode:dword
;Setup the input array and the shift variable
.data
	input byte 1,2,3,4,5,6,7,8
	shift byte 2
.code
	main PROC
		; clear up the registers to make sure there is no old values inside them
		mov eax,0
		mov ebx,0
		mov ecx,0
		mov edx,0 

		; Setup EAX register with first and second values from the input array
		mov ah,input
		add ah,shift

		mov al,input+1
		add al,shift

		; Setup EBX register with 3rd and 4th values from the input array
		mov bh,input+2
		add bh,shift

		mov bl,input+3
		add bl,shift

		; Setup ECX register with 5th and 6th values from the input array
		mov ch,input+4
		add ch,shift

		mov cl,input+5
		add cl,shift

		; Setup EDX register with 7th and 8th values from the input array
		mov dh,input+6
		add dh,shift

		mov dl,input+7
		add dl,shift

		; exit the program
		invoke ExitProcess, 0
	main endp
end main