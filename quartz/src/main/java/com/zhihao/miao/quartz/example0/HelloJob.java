package com.zhihao.miao.quartz.example0;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

	
public class HelloJob implements Job {

	/**
	 * ʵ�����Լ��Ķ�ʱ���� ,��������дʲô,��˵����!<br>
	 * ����ֻ��� HelloWorld!
	 */
	@Override
	public void execute(JobExecutionContext context){
		//��� HelloWorld !
		System.out.println("Hello World! - " + new Date());
	}

}

