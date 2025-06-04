package ro.ase.acs.main;

//Problem description: 
//Implement the following tasks using Java Multithreading API:
//7.1 Parallel addition for two large vectors - benchmark with running time on various configurations 
//(e.g. millis consumed in 8 threads on 2 cores, 4 threads on 4 cores, etc.)
//7.2 Parallel dot product between two large vectors - benchmark
//7.3 Parallel addition for two large matrixes - the two input matrix are read from file stream 
//(text file) and the output is written also in file stream (text file) + benchmark
//7.4 Parallel addition of the items from each row of a matrix object - benchmark
//7.5 Read a matrix CONCURRENTLY from a file with diffrent Java threads.

import ro.ase.acs.classes.*;

public class Main {
 public static void main(String[] args) {
     final int NUMBER_OF_ELEMENTS = 500_000_000;
     final int NUMBER_OF_THREADS = 4; // i will change in order to find optimal number

     int first_array[] = new int[NUMBER_OF_ELEMENTS];
     int second_array[] = new int[NUMBER_OF_ELEMENTS];

     //7.1 Parallel addition for two large vectors - benchmark with running time on various configurations 
     //(e.g. millis consumed in 8 threads on 2 cores, 4 threads on 4 cores, etc.)
     
     // initialize first array
     for(int i = 0; i < NUMBER_OF_ELEMENTS; i++){
         first_array[i] = i + 1;
         second_array[i] = i + 1;
     }

     // sequential add for first_array
     long sum = 0;
     long startTime = System.currentTimeMillis();
     
     for(int i = 0; i < NUMBER_OF_ELEMENTS; i++){
         sum += first_array[i];
     }

     long endTime = System.currentTimeMillis();

     System.out.println("Both arrays store the same value: 500 000 000\r\n");
     System.out.println("Benchmark 7.1 parallel addition for two large vectors:");
     System.out.printf("First array: Sequential sum = %d computed in %d ms\r\n", sum, endTime - startTime);

     // test for second_array multi-threading
     sum = 0;
     startTime = System.currentTimeMillis();
     ArraySumThread[] threads = new ArraySumThread[NUMBER_OF_THREADS];

     for(int i = 0; i < NUMBER_OF_THREADS; i++){
         threads[i] = new ArraySumThread(second_array, 
        		  i * NUMBER_OF_ELEMENTS / NUMBER_OF_THREADS, 
        		 (i + 1) * NUMBER_OF_ELEMENTS / NUMBER_OF_THREADS);
         threads[i].start();
     }

     for(int i = 0; i < NUMBER_OF_THREADS; i++){
         try {
			threads[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
     }

     for(int i = 0; i < NUMBER_OF_THREADS; i++){
         sum += threads[i].getSum();
     }

     endTime = System.currentTimeMillis();

     System.out.printf("Second array: Multi-threading sum on 4 threads = %d computed in %d ms\r\n", sum, endTime - startTime);
     
     //7.2 Parallel dot product between two large vectors - benchmark
 	}
}
