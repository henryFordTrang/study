package com.study;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class test {
	public static void main(String[] args){
		String x=new Sha256Hash("888888").toHex();
		System.out.println(x);
	}
}
