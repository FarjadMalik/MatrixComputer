# MatrixComputer
A Matrix compute class which has the functionality to add, multiply, subtract or any combination of these.


Project Documentation:
This project is a simple Matrix computer which computer Addition, subtraction and multiplication on a matrix or a mixture of these operations using a subclass called matrix (created by us). Github link is given in the end.



FileData.txt : This is our sample file in which the data for all the matrices is stored along with the number of rows and number of cols of each matrix.
its format is very simple. It contains digits and either a ','(comma) or ' '(space) or ';'(colin) and ofcourse a new line character
line can only contain a space if it is a line describing the noOfRows and noOfCols for a matrix and these defintions should precede all the Matrix data
line with , and ; are matrix data lines where ; is for row separation and , is for each column separation. e.g
2 2
1,2;3,4
these lines are a format for a matrix (2x2) and it will be stored as 
1 2
3 4



Unit Tests : Our program tests the user for an addition of 3 matrices, multiplication of 2 matrices and addition of two matrices followed by the addition into its result. Also we are testing for the subtraction of 2 matrices.
More tests can be created in the same way as given in the tester. Each Test function requires a matrix index/position in the array starting from 0.

Github: Project has been shared on github and can be acessed publicly using the following link and can also be found in the separate file name GithubLink:
