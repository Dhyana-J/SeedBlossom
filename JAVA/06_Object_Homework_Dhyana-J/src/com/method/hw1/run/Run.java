package com.method.hw1.run;
import com.method.hw1.controller.*;

public class Run {

	public static void main(String[] args) {
		
		NonStaticSample n = new NonStaticSample();
		
		n.printLottoNumbers();
		n.outputChar(10, 'Â§');
		System.out.print("3. ·£´ý ¿µ¹®ÀÚ Ãâ·Â : "+n.alphabette());
		System.out.println();
		System.out.println(n.mySubstring("", 0, 3));
		
	}

}
