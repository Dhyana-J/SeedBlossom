package com.kh.chap01_beforeVSafter.before.run;

import com.kh.chap01_beforeVSafter.before.model.vo.Desktop;
import com.kh.chap01_beforeVSafter.before.model.vo.Tv;
import com.kh.chap01_beforeVSafter.before.model.vo.SmartPhone;

public class Run {

	public static void main(String[] args) {

		Desktop d = new Desktop("�Ｚ","d-01","¯¯����ũž",1500000,true);
		
		Tv t = new Tv("LG","t-01","��θ�Ƽ��",3500000,60);
		
		SmartPhone s = new SmartPhone("Apple","s-01","������SE2",1000000,"SKT");
		
		System.out.println(d.information());
		System.out.println(t.information());
		System.out.println(s.information());
		
	}

}
