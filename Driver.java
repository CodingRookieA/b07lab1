import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.util.*;
public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,1,1,5};
		int[] c1_exp = {0,1,2,3};
		Polynomial p1 = new Polynomial(c1,c1_exp);
		for(int i = 0; i<4;i++){
			System.out.println("coefficient"+ c1[i] + "exponential" + c1_exp[i]);
		}
		File poly = new File("poly.txt");
		Polynomial p2 = new Polynomial(poly);
		for(int i = 0; i<5;i++){
			System.out.println(p2.coe[i] + p2.exp[i]);
		}
		Polynomial s = p1.add(p2);
		System.out.println("s(0.1) = " + s.evaluate(0.1));
		if(s.hasRoot(1))
		System.out.println("1 is a root of s");
		else
		System.out.println("1 is not a root of s");
		s.saveToFile("poly.txt");
	}
}