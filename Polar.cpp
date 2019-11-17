/*
 * Polar.cpp
 *
 *  Created on: Nov 16, 2019
 *      Author: williamgusmanov
 */
#include <cmath>
#include "Polar.h"
#include <iostream>
namespace std {

//default constructor
	Polar::Polar() {
		radius = 0.0f;
		angle = 0.0f;
	}
	Polar::Polar(float r, float a) : radius(r),angle(a) { }

	Polar::~Polar() {
	}

	Polar Polar::operator+(Polar& p2){

		//convert angle to radians first
		float x1 = radius * cos(angle * (M_PI/180));
		float y1 = radius * sin(angle * (M_PI/180));
		float x2 = p2.radius * cos(p2.angle * (M_PI/180));
		float y2 = p2.radius * sin(p2.angle * (M_PI/180));
		cout << "checking reverse conversion: "<< sqrt(std::pow(x1,2) + std::pow(y1,2));
		float newRadius = sqrt(std::pow(x1 + x2,2) + std::pow(y1 + y2,2));
		float newAngle = asin((y1+y2)/(x1+x2));
		cout << "x1:" << x1 << endl;
		cout << "y1:" << y1 << endl;
		cout << "x2:" << x2 << endl;
		cout << "y2:" << y2 << endl;
		newAngle = (newAngle*180)/M_PI;
		cout << "newRadius:" << newRadius << endl;
		cout << "newAngle" << newAngle << endl;
		return Polar(newRadius, newAngle);
	}
	void Polar::display(){
		cout << "(" << radius << "," << angle << ")" << endl;
	}
}
