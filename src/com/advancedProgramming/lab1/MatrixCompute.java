package com.advancedProgramming.lab1;

//imports needed for different built in functions we used e.g reading from a file
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class MatrixCompute {
	public static Matrix[] mMatrices;	//an array of the sub-class Matrix. We dynamically initialize it based on user data
	
	public MatrixCompute(int number){	//where number is the no of matrices user wants
		mMatrices = new Matrix[number];
		for(int i = 0; i < number ; i++){	// loop for dynamicaly assinging each index of array with a matrix
			mMatrices[i] = new Matrix();
		}
	}
	
	
	
	public void fileRead(String filename) { //File read function
		int currentNumOfMatrices=0;	//keeps track of the number of matrix being filled currently
				
		try {
			FileReader r = new FileReader(filename);
			BufferedReader br = new BufferedReader(r);
			String line; //each new line read is stored here
			boolean isFirst = true;	//to check whether the current line we are reading is telling us the no of rows or cols 
									// or the actual values in it
			
			int rows = 0;	//initializing variables
			int cols = 0;
			
		    while((line = br.readLine()) != null){ //reads a line until end of file
		    	
		    	if(isFirst){	//if the line we just read is  the line about no of rows and cols in the matrix about to be added
		    		
		    		String[] tokens = line.split(" "); //splits the line on basis of spaces and stores in token array
		    		rows = Integer.parseInt(tokens[0]);	//first part of the token is no of rows
		    		cols = Integer.parseInt(tokens[1]);	//second part is the no of cols
		    		mMatrices[currentNumOfMatrices].mValues = new int [rows][cols];	//initializing the matrix 2d matrix mValues 
		    		
		    		isFirst = false;	//as the no of rows and cols line is read the next line we read will be the actual data of the matrix
		    	}
		    	else{	//if the line is actual data of the line
		    		String[] tokens = line.split(";");	//split the data on the basis of row. Each rows starts from ; 	    			
	    			for(int i = 0; i < rows; i++){	//for the number of rows defined earlier for this matrix 
	    				String[] tokens2 = tokens[i].split(",");	//we split each row into columns which are separated by ,
	    				for(int j = 0; j < cols; j++){	//and for each col we add the value to the row.
	    					mMatrices[currentNumOfMatrices].mValues[i][j] = Integer.parseInt(tokens2[j]);	//i is the row and j is the col
	    				}
	    			}
		    		
		    		mMatrices[currentNumOfMatrices].mNumOfRows=rows;	//define the numOfRows for that matrix
		    		mMatrices[currentNumOfMatrices].mNumOfCols=cols;	//define the numOfcols for that matrix
		    		currentNumOfMatrices++;	//increment the current matrix we are gonna fill
		    		
		    		isFirst = true;	//next line we read is gonna be about the no of rows and no of cols in the following matrix
		    	}
		    	
		    }
		    br.close();
		    display();
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(NullPointerException e){
			System.out.println("Null Pointer Exception Error: Your file is probably not formatted properly. Read the ProjectDocumentation file. ");
			
		}
	    
		
	}
	
	private void display() {	//displays all the matrices in the matrices array defined as the member variable of  this class
		for(int i = 0;i < mMatrices.length ; i++){
			System.out.println("Matrix "+ (i+1));
			for(int j=0;j < mMatrices[i].mNumOfRows;j++){
				for(int k=0;k<mMatrices[i].mNumOfCols;k++){
					System.out.print(mMatrices[i].mValues[j][k]+" ");
				}
				System.out.println();
			}
		}
		
	}

	public Matrix Add(Matrix m1,Matrix m2){	//Adds. m1 + m2
		if(m1.mNumOfRows!=m2.mNumOfRows || m2.mNumOfCols!=m2.mNumOfRows){ //condition for addition holds or not
			System.out.println("Adding requires same order.");	//if not display and exit the program.
			System.exit(0);
		}
		Matrix temp = new Matrix();	//a new matrix to store the result
		temp.mNumOfRows = m1.mNumOfRows;	//defining the rows 
		temp.mNumOfCols = m1.mNumOfCols;	//defining the cols
		temp.mValues = new int [temp.mNumOfRows][temp.mNumOfCols];	//initializing the dynamic space
		for(int i=0;i < temp.mNumOfRows;i++){	//adding by doing two for loops. i for rows. j for cols
			for(int j = 0; j < temp.mNumOfCols;j++){
				temp.mValues[i][j] = m1.mValues[i][j] + m2.mValues[i][j]; 
			}
		}
		
		return temp;
		
	}
	
	public Matrix Subtract(Matrix m1,Matrix m2){	//Subtracts. m1 - m2
		if(m1.mNumOfRows!=m2.mNumOfRows || m2.mNumOfCols!=m2.mNumOfRows){	//condition for subtraction holds or not
			System.out.println("Subtracting requires same order.");	//if not display and exit the program.
			System.exit(0);
		}
		Matrix temp = new Matrix();	//a new matrix to store the result
		temp.mNumOfRows = m1.mNumOfRows;	//defining the rows
		temp.mNumOfCols = m1.mNumOfCols;	//defining the cols
		temp.mValues = new int [temp.mNumOfRows][temp.mNumOfCols];	//initializing the dynamic space
		for(int i=0;i < temp.mNumOfRows;i++){
			for(int j = 0; j < temp.mNumOfCols;j++){
				temp.mValues[i][j] = m1.mValues[i][j] - m2.mValues[i][j]; 	//subtracting by doing two for loops. i for rows. j for cols
			}
		}
		
		return temp;
		
	}
	
	public Matrix Multiply(Matrix m1,Matrix m2){
		/*to multiply A(lxn) and B(mxp) : n and m should be equal and if they are the resultant would be of the order (lxp)*/
		if(m1.mNumOfCols != m2.mNumOfRows){	//checking the condition
			System.out.println("A*B requires A(columns) == B(rows). Unfulfilled");
			System.exit(0);
		}
		Matrix temp = new Matrix();	//a new matrix to store the result
		temp.mNumOfRows = m1.mNumOfRows;	//defining the rows which is equal to the rows of the first matrix
		temp.mNumOfCols = m2.mNumOfCols;	//defining the cols which is equal to the cols of the second matrix
		temp.mValues = new int [temp.mNumOfRows][temp.mNumOfCols];
		//We use three loops to multiply a matrix first two for the rows and cols respectively and third one determines the values in the dot product to be added
		for(int i=0;i < temp.mNumOfRows;i++){
			for(int j = 0; j < temp.mNumOfCols;j++){
				temp.mValues[i][j] = 0;
				for(int k =0;k < m1.mNumOfCols;k++ ){
					temp.mValues[i][j] += m1.mValues[i][k] * m2.mValues[k][j];
				}
			}
		}
		
		return temp;
		
	}

	public void AddTest(int a,int b,int c) {
		//Add Unit test. first adds the first two matrices and then adds the third in the result of first two 
		//and then displays the matrix using two for loops
		
		Matrix result = this.Add(mMatrices[a],mMatrices[b]);
		result = this.Add(result,mMatrices[c]);
		
		System.out.println("Add Result Matrix:");
		display(result);
		
	}



	public void display(Matrix result) { //displays a specific matrix result using two for loops
		for(int j=0;j <result.mNumOfRows;j++){
			for(int k=0;k<result.mNumOfCols;k++){
				System.out.print(result.mValues[j][k]+" ");
			}
			System.out.println();
		}
	}

	public void MultiplyTest(int a, int b) {
		//Test for multiplication of two matrices and storing the result in the result matrix and then displaying
		Matrix result = this.Multiply(mMatrices[a],mMatrices[b]);
		
		System.out.println("Multiply Result Matrix:");
		display(result);
		
	}

	public void MixedTest(int a, int b, int c) {
		//Third test. in this we first multiply the first two matrices and then add the third in the result of the first and then display
		
		Matrix result = this.Multiply(mMatrices[a],mMatrices[b]);
		result = this.Add(result,mMatrices[c]);
		
		System.out.println("Mixed Result Matrix:");
		display(result);
		
		
	}

	public void SubTest(int a, int b) {
		//Subtractiong test and storing it in the result and displaying
		Matrix result = this.Subtract(mMatrices[a],mMatrices[b]);
				
		System.out.println("Subtract Result Matrix:");
		display(result);
		
	}
	
	//Sub-class of a matrix. you can call it a blueprint of each matrix. We will use its array in the MatrixComputer class.
	public class Matrix{
		public int mNumOfRows;//represents the no. of rows
		public int mNumOfCols;//represents the no. of columns
		public int[][] mValues;//Contains the values of the matrix as a 2d array.
	}

}
