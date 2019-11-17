/*
 * Polar.h
 *
 *  Created on: Nov 16, 2019
 *      Author: williamgusmanov
 */

#ifndef POLAR_H_
#define POLAR_H_

namespace std {

class Polar {
private:
	float radius;
	float angle;
public:
	Polar(); // default constructor
	Polar(float radius, float angle); //constructor
	virtual ~Polar(); //destructpr
	/**
	 * converts polar back to rectangle, creates new x and y, back to polar
	 * then returns new polar object
	 */
	Polar operator+(Polar& p2);
	void display(); //member function
};

} /* namespace std */

#endif /* POLAR_H_ */
