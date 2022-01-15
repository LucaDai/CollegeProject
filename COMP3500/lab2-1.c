#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int nloop = 50;

/**********************************************************\
 * Function: increment a counter by some amount one by one *
 * argument: ptr (address of the counter), increment       *
 * output  : nothing.                                       *
 **********************************************************/
void add_n(int *ptr, int increment){
  int i,j;

  for (i=0; i < increment; i++){
    *ptr = *ptr + 1;
    for (j=0; j < 1000000;j++);
  }
}

int main(){
  int pid;        /* Process ID                     */

  int *countptr;  /* pointer to the counter         */

  /* allocate memory for my counter */
  countptr = (int *) malloc(sizeof(int));


 
  if (!countptr) {
    printf("Memory allocation failed\n");
    exit(1);
  }
  *countptr = 0;


  setbuf(stdout,NULL);

  pid = fork();
  if (pid < 0){
    printf("Unable to fork a process\n");
    exit(1);
  }

  if (pid == 0) {
    /* The child increments the counter by two's */
    while (*countptr < nloop){
      add_n(countptr,2);
      printf("Child process -->> counter= %d\n",*countptr);
    }
  }
  else {

    /* The parent increments the counter by twenty's */
    while (*countptr < nloop){
      add_n(countptr,20);
      printf("Parent process -->> counter = %d\n",*countptr);
    }

  }
  exit(0);
}









