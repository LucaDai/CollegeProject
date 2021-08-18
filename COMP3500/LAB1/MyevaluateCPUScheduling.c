
/*****************************************************************************\
* Laboratory Exercises COMP 3500                                              *
* Author: Saad Biaz                                                           *
* Date  : May 09, 2021                                                        *
\*****************************************************************************/

/*****************************************************************************\
*                             Global system headers                           *
\*****************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <sys/select.h>
#include <sys/time.h>
#include <math.h>
#include <limits.h>
#include <signal.h>
#include <sys/wait.h>
#include <string.h>

#include <unistd.h>   /* for getpid, read, write, fork                       */



/*****************************************************************************\
*                             Global data types                               *
\*****************************************************************************/
typedef int             Identifier;
typedef short           Flag;
typedef int             Status;
typedef double          Timestamp;
typedef double          TimePeriod;
typedef unsigned int    Quantity;
typedef double          Average;




/*****************************************************************************\
*                             Global definitions                              *
\*****************************************************************************/
#define  MAXNUMBERPROCESSES 1000    /* Maximum number of processes           */
#define  MINTIMEEXPERIMENT   120.0  /* Minimum Time (in seconds) To Evaluate */
#define  FALSE                 0    /* False condition                       */
#define  TRUE                  1    /* True condition                        */

/*****************************************************************************\
*                            Global data structures                           *
\*****************************************************************************/




/*****************************************************************************\
*                                  Global data                                *
\*****************************************************************************/
Quantity   NumberProcesses;      /* Number of Competing Processes          */
Identifier ProcessIDs[MAXNUMBERPROCESSES];
                                   // Array of Process IDs                   
TimePeriod ExperimentDuration;   /* Time in seconds experiment lasts       */
Flag       Show;                 /* If TRUE, displays extra information    */
Flag       GreenLight;           /* If true, Process starts its task       */
Timestamp  StartTime;            /* Start time of emulation                */
Timestamp  GreenLightTime;       /* Time process gets green light to start */
Identifier ParentID;             /* Process ID of Parent (Control)         */


/*****************************************************************************\
*                               Function prototypes                           *
\*****************************************************************************/
Flag        Initialization(int argc, char **argv);
Timestamp   Now(void);     
Flag        LaunchExperiment(void);
static void StartProcessTask(int signo);


/*****************************************************************************\
* function: main()                                                            *
* usage:    Launches n processes that execute the same task                   *
*           Starts all processes almsot a the same time                       *
*           Let all processes to run for some time                            *
*           When time is over, record the percentage of task completed        *
*******************************************************************************
* Inputs: ANSI flat C command line parameters                                 *
* Output: None                                                                *
\*****************************************************************************/

int main (int argc, char **argv) {
   if (Initialization(argc,argv)){
     LaunchExperiment();
   }
} /* end of main function */


/*****************************************************************************\
* function: LaunchExperiment()                                                *                                 
* usage:    Launches the experiment                                           *                             
*******************************************************************************                              
* Input    : None                                                             *                             
* Output   : None                                                             *                        
* Function : Fork NumberProcesses processes to execute a task, collect some   *                                
*            measure of task, computes mean and variance for all processes.   *                       
\*****************************************************************************/
Flag LaunchExperiment(void){
  int i;
  Identifier pid;
  Status     status;
  Quantity   numberTerminatedChildren;
  Timestamp NowRecord;
  int count;
// _Student declares own variables, file where to store data
// ................
  unsigned long long c;
  FILE* fp;
  fp = fopen("ListC.txt", "w");

  for (i=0; i < NumberProcesses; i++){
    pid = fork(); // Create a process

    if (pid < 0){
      printf("Cannot fork Process %d - Abort\n", i);
      exit(1);

    }
    if (pid == 0) { //Child process code

      while (!GreenLight); // Child waits until green light

      GreenLightTime = Now();
      printf("Process %d start at %f\n",getpid(),GreenLightTime);

      /***************************************************************************/
      // _Student implements here some task execute for ExperimentDuration seconds
      //  ..............
      //  ........
      NowRecord = Now();
      // The proccesses total run experimentDuration, so each run ExperimentDuration / NumberProcesses
      c = 0;
      while (NowRecord - GreenLightTime < ExperimentDuration) {
          //printf("PID: %d£º ExperimentDuration: %f s Ran so far %f s\n",
              //getpid(), ExperimentDuration, NowRecord - GreenLightTime);
          c++;
          NowRecord = Now();
      }
      
      fprintf(fp, "%lld\n", c);
      printf("fp is written.\n");
      printf("The Process %d computed for c = %lld times\n",
          getpid(), c);

      // _Student end of task
      /***************************************************************************/      

      printf("Now: %f: Process %d computed for %f seconds\n",
	     Now(),getpid(), Now() - GreenLightTime);
      exit(1); // Just by precaution exits without creating grandchildren

    } else { // Parent
      ProcessIDs[i] = pid; // Parent records the process ID of child just created
    }
  }

  // Parent process "green lights" all children processes
  for (i=0; i < NumberProcesses; i++){
    // printf("Process ID is %d\n",ProcessIDs[i]);
    kill(ProcessIDs[i],SIGUSR1); // Green Light Processes
  }

  //Wait for all children to terminate
  numberTerminatedChildren = 0;
  while (numberTerminatedChildren < NumberProcesses){
    wait(NULL);
    numberTerminatedChildren++;
  }
  printf("Experiment Done! Parent Process exists\n");
  // _Student closes the file where you store c, the measure produced by your Task
  fclose(fp);
  // _Student processes data in File to compute the mean and variance of the measure c
  FILE* file;
  
  file = fopen("ListC.txt", "r");
  if (file == NULL) {
      printf("unable to open ListC.txt\n");
      return;
  }
  
  long long readc = 0;
  
  long long list[MAXNUMBERPROCESSES];
  
  i = 0;
  
  while (!feof(file))
  {
      fscanf(file, "%lld", &readc);
      list[i] = readc;
      i++;
      printf("%lld", readc);
      
  } 
  printf("file will be closed!\n");
  
  fclose(file);
  
  long long mean = 0;
  
  int j;
  
  for (j = 0; j < NumberProcesses; j++) {
      mean += list[j];
      
  }
  mean = mean / NumberProcesses;
  long long variance = 0;
  
  for (j = 0; j < NumberProcesses; j++) {
      variance += (list[j] - mean) * (list[j] - mean);
      printf("d");
  }
  variance = variance / NumberProcesses;
  // over all processes
  //_Student displays the mean and the variance for all processes
  printf("Over all in %u processes:\n mean = %lld, variance = %lld\n",
      NumberProcesses, mean, variance);

  exit(0); //Parent exists
}

/***********************************************************************\                               
 * Input    : Standard command line parameters                           *                               
 * Output   : Returns a flag TRUE if everything went ok                  *                               
 * Function : Initialize global variables, structures.                   *                               
\***********************************************************************/

Flag Initialization(int argc, char **argv){

  if (argc != 3) {
    printf("usage: command NumberProcesses NumberMinutes\n");
    return(FALSE);
  }

  NumberProcesses    = (Quantity) atoi(argv[1]);
  if (NumberProcesses > MAXNUMBERPROCESSES){
    printf("No more than %d processes\n",MAXNUMBERPROCESSES);
    return(FALSE);
  }

  ExperimentDuration = (TimePeriod) 60.0 * atoi(argv[2]);
  if (ExperimentDuration < MINTIMEEXPERIMENT){
    printf("Experiment must run at least at least %f seconds\n", MINTIMEEXPERIMENT);
    return(FALSE);
  }

  StartTime           = Now();
  ParentID            = getpid();
  GreenLight          = FALSE;
  signal(SIGUSR1,StartProcessTask);

  printf("Now is %f,  Number of processes is %d, Experiment Duration is %f seconds\n", 
	Now(),NumberProcesses, ExperimentDuration);
  
  return(TRUE);

 }


/***********************************************************************\                                     
 * Input : signal number signo (we do not use signo)                     *                                     
 * Output: None                                                          *                                     
 * Function: Turns on Greenlight to allow Process to start its task      *                                     
\***********************************************************************/
static void StartProcessTask(int signo){
  signal(SIGUSR1, StartProcessTask);
  // printf("Green Light Process %d at %f\n",getpid(), Now());
  GreenLight = TRUE;
}


/***********************************************************************\                                     
 * Input    : None                                                       *                                     
 * Output   : Returns the time is seconds since this program starts      *                                     
\***********************************************************************/
Timestamp Now(){
  struct timeval tv_CurrentTime;
  gettimeofday(&tv_CurrentTime,NULL);
  return( (Timestamp) tv_CurrentTime.tv_sec + (Timestamp) tv_CurrentTime.tv_usec / 1000000.0 - StartTime);
}
