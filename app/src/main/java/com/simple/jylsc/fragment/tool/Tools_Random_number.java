package com.simple.jylsc.fragment.tool;

import java.util.Date;
import java.util.Random;

import android.R.integer;

public class Tools_Random_number
{
	private static long seed;//种子
	private static int size;//随机数范围
	private static Random random;
	//构造函数
	public Tools_Random_number(int x,int y)
	{
		size=y;
		Date dt= new Date();
		Long time= dt.getTime();
		seed=time+x;
		//设置随机种子
		random=new Random(seed);
	}

	public Tools_Random_number(int x)
	{
		size=x;
		Date dt= new Date();
		Long time= dt.getTime();
		//设置随机种子
		random=new Random(seed);
	}

	public Random get_random()
	{
		return random;
	}
	public static int get_rand()
	{
		return(random.nextInt(size));
	}

}
