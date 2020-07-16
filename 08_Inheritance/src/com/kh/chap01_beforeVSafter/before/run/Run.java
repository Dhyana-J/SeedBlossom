package com.kh.chap01_beforeVSafter.before.run;

import com.kh.chap01_beforeVSafter.before.model.vo.Desktop;
import com.kh.chap01_beforeVSafter.before.model.vo.Tv;
import com.kh.chap01_beforeVSafter.before.model.vo.SmartPhone;

public class Run {

	public static void main(String[] args) {

		Desktop d = new Desktop("»ï¼º","d-01","Â¯Â¯µ¥½ºÅ©Å¾",1500000,true);
		
		Tv t = new Tv("LG","t-01","¾âºÎ¸®Æ¼ºñ",3500000,60);
		
		SmartPhone s = new SmartPhone("Apple","s-01","¾ÆÀÌÆùSE2",1000000,"SKT");
		
		System.out.println(d.information());
		System.out.println(t.information());
		System.out.println(s.information());
		
	}

}
