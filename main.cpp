#include "Matrix.h"
#include <iostream>
using namespace std;
int main(){
    cout << "Matrix 1:" << endl;
    Matrix matrix1 (2, 2);
    cin>>matrix1;
    cout<<matrix1<<endl;
    ///*
    cout << "Matrix 2:" << endl;
    Matrix matrix2 (2, 2);
    cin>>matrix2;
    cout<<matrix2<<endl;
    // Instantiation and setup of matrix3
    cout << "Matrix 3:" << endl;
    Matrix matrix3 (2, 2);
    cin>>matrix3;
    cout<<matrix3<<endl;
    cout << "Result of matrix1 + matrix2: " << endl;
    Matrix addResult (2, 2);
    addResult = matrix1 + matrix2;
    cout<<addResult<<endl;
    cout << "Result of matrix1 * matrix3: " << endl; //works
    Matrix product(2,2);
    product = matrix1 * matrix3;
    cout<<product<<endl;
    cout<<"++ operator: "<<endl;
    Matrix matrix4(2,2);
    matrix4 = ++matrix1; //no dummy variable WORKS
    cout<<"Matrix 4:"<<endl;
    cout<<matrix4<<endl;
    cout<<"Matrix 1 after increment by one:"<<endl; //WORKS
    cout<<matrix1<<endl;
    matrix1 = 2 + matrix1; //WORKS
    cout<<"Matrix 1 after increment by constant:"<<endl;
    cout<<matrix1<<endl;
    cout<<"Use += with a matrix"<<endl;
    matrix1 += matrix2; //WORKS
    cout<<matrix1<<endl;
    cout<<"Use += with a constant"<<endl;
    matrix1 += 5;
    cout<<matrix1<<endl; //WORKS
    return 0;
}
