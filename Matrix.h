//
//  Matrix.h
//  LAB8
//
//  Created by William Gusmanov on 11/13/19.
//  Copyright © 2019 William Gusmanov. All rights reserved.
//

#ifndef Matrix_h
#define Matrix_h
#include <stdio.h>
#include <iostream>
#endif /* Matrix_hpp */
using namespace std; 
class Matrix {
    private:
        int RowSize;
        int ColSize;
        int **ptr;  //unknown row & col size;
    public:
    //constructor
        Matrix(int rowSize, int colSize);
        //allocate memory for 2d array here
        //2 step deletion process, delete cols then delete rows?
       // ~Matrix(); //Destructor, if object contains pointers, need a deconstructor
        void input();
                //RESULT = M1 + M2
        //addition of two matrices, m3 = m1 + m2
        Matrix operator + (const Matrix & m);
        //this = this + matrix
        Matrix operator += (Matrix & m);
        //this = this + int
        Matrix operator += (const int &num);
        //m3 = m1 * m2
        Matrix operator * (const Matrix & m);
        //this = this + 1
        Matrix& operator ++();
        //non member function for (int + matrix)
        friend Matrix operator +(const int &num, const Matrix &m);
        //input stream for matrix
        friend istream& operator>> (istream& in, const Matrix& m);
        //output stream for matrix
        friend ostream &operator<<(ostream &os, const Matrix &m);
};
