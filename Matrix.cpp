//
//  Matrix.cpp
//  LAB8
//
//  Created by William Gusmanov on 11/13/19.
//  Copyright © 2019 William Gusmanov. All rights reserved.
//

#include <stdio.h>
#include "Matrix.h"

Matrix::Matrix(int rowSize, int colSize){
    RowSize = rowSize;
    ColSize = colSize;
    ptr = new int* [rowSize]; //allocate memory for row size
    for (int i = 0; i < rowSize; i++){
        ptr[i] = new int[colSize];
    }
}
/*
Matrix::~Matrix(){
    for (int i = 0; i < RowSize; i++){
        delete [] ptr[i];
    }
    delete []ptr;
}
 */
void Matrix::input(){
    assert(RowSize > 0 && ColSize > 0);
    for (int i = 0; i < RowSize; i++){
        for (int j = 0; j < ColSize; j++){
            std::cout << "enter value for :[" << i <<"]["<<j<<"]";
            std::cin >> ptr[i][j];
        }
    }
}
//return a matrix
    Matrix Matrix::operator+(const Matrix &m2){
    assert(RowSize == m2.RowSize && ColSize == m2.ColSize);
    Matrix result(m2.RowSize,m2.ColSize);
    cout << "m2.ROWSIZE " << m2.RowSize << " " << m2.ColSize;
    cout << "result.ROWSIZE " << result.RowSize << " " << result.ColSize;
    for (int i = 0; i < RowSize; i++){
        for (int j = 0; j < ColSize; j++){
    cout << ptr[i][j] << " " << m2.ptr[i][j] << endl;
            result.ptr[i][j] = ptr[i][j] + m2.ptr[i][j];
            //cout << result.ptr[i][j];
        }
    }
    return result;
}
Matrix Matrix::operator+=(Matrix &m2){
    assert(RowSize == m2.RowSize && ColSize == m2.ColSize);
    for (int i = 0; i < RowSize; i++){
        for (int j = 0; j < ColSize; j++){
            ptr[i][j] = ptr[i][j] + m2.ptr[i][j];
        }
    }
    return *this;
}

Matrix Matrix::operator+=(const int &num){
    for (int i = 0; i < RowSize; i++){
        for (int j = 0; j < ColSize; j++){
            ptr[i][j] = ptr[i][j] + num;
        }
    }
    return *this;
}
    Matrix Matrix::operator*(const Matrix &m2){
    assert(ColSize == m2.RowSize);
    Matrix result(RowSize,m2.ColSize); //(n x m)<dot>(m x p) = n x p
    int sum;
    for (int i = 0; i < RowSize; i++) {
        for (int j = 0; j < m2.ColSize; j++){
            sum = 0;
            for (int k = 0; k < m2.RowSize; k++){
                sum = sum + ptr[i][k] * m2.ptr[k][j];
            }
            result.ptr[i][j] = sum;
        }
    }
    return result;
}
Matrix& Matrix::operator++(){
    cout << "operator ++ entered";
    for (int i = 0; i < RowSize; i++){
        for (int j = 0; j < ColSize; j++){
            this->ptr[i][j] = this->ptr[i][j] + 1;
            cout << ptr[i][j]<<endl;
        }
    }
    return *this;
}
Matrix operator +(const int &num, const Matrix &m){
    for (int i = 0; i < m.RowSize; i++){
        for (int j = 0; j < m.ColSize; j++){
            m.ptr[i][j] = m.ptr[i][j] + num;
        }
    }
    return m;
}
istream& operator>> (istream& in, const Matrix& m){
    assert(m.RowSize > 0 && m.ColSize);
    for (int i = 0; i < m.RowSize; i++ ){
        for (int j = 0; j < m.ColSize; j++){
            std::cout << "enter value for :[" << i << "]["<<j<<"]";
            std::cin >> m.ptr[i][j];
        }
    }
    return cin;
}
ostream& operator<< (ostream& out, const Matrix& m){
    std::cout << "\n- - - - - - - ";
    for (int i = 0; i < m.RowSize; i++){
        std::cout<<"\n|";
        for (int j = 0; j < m.ColSize; j++){
            std::cout<<m.ptr[i][j] << " ";
        }
        std::cout << "|";
    }
    return cout;
}
