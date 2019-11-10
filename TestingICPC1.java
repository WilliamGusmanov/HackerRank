package hwpackage;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class TestingICPC1 {
	private int baseInput; //the input 
	private long numberInBase10; //the 2nd input 
	
	private String output; //expected output  
	private ICPCProblem1 Prob1; //the thing we are testing
	
	@Before
	public void initialize() {
		Prob1 = new ICPCProblem1();
	}
	
	//Constructor for Parametertest
	public TestingICPC1(int input1, long input2, String expectedResult) {
		this.baseInput = input1;
		this.numberInBase10 = input2; 
		this.output = expectedResult; 
	}
	
	@Parameterized.Parameters
	public static Collection Testing() {
		return Arrays.asList(new Object[][]{
			{10, 56L, "1 3"},
			{10, 4L, "0 1"},
			{10, 89L, "24 13"},
			{10, 10911L,"55 28"},
			{10, 1997L, ">500"},
			{12, 58972L, "7 7"},
			{12, 105789413L, ">500"},
			{12, 100100L, "9 8"},
			{2, 196L, "1 8"},
			{2, 22L, ">500"},
			{13, 2048L, "2 4"},
			{13, 209847L, "3 6"},
			{127, 86060307891903L, "6 9"},
			{128, 999999999999999999L, ">500"},
			{123, 999999999999999999L, "2 9"},
		});
	}
	
	@Test
	public void testCountA() {
		String result = ICPCProblem1.ICPCProblem(baseInput,  numberInBase10);
		System.out.println("Checking: " + baseInput + " " + numberInBase10);
		System.out.println("Expected: " + output + "\tOutput: " + result);
		assertEquals(output, result);
		
	}
}

