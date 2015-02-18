package com.advancedProgramming.lab1;

public class Tester {

	public static void main(String[] args) {
		//Creating a new MatrixCompute object and initializing it with 10
		// User can pass the number of matrices he wants in the constructor as an integer
		MatrixCompute computer = new MatrixCompute(10);
		
		//the compute object now reads and loads all the matrices from the FileData.txt present in the project root
		computer.fileRead("FileData.txt");
		
		//User Tests
		
		//Test for addition of 3 matrices. User passes the index of the matrices as given in the file (FileData.txt) starting 
		//from 0.
		computer.AddTest(0,1,2);
		
		//Test for multiplication. User, similarly, passes the index of the matrices to be multiplied starting from 0
		computer.MultiplyTest(0,1);
		
		//Third Test. User passes index of three matrices and the first two are multiplied and the result is stored in the third
		computer.MixedTest(0,1,2);
		
		//Test for subtraction (Extra). User, similarly, passes the index of the matrices to be subtracted starting from 0
		computer.SubTest(0,1);
		
		

	}

}

