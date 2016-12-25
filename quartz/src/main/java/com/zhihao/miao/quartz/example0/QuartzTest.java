package com.zhihao.miao.quartz.example0;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;


public class QuartzTest {
	
	public static void run() throws Exception {
		//��StdSchedulerFactory�����õ�Scheduler��ʵ��
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		//����һ�� job���󲢰�����д��  HelloJob�� 
		//����ִ�е����񲢲���Job�ӿڵ�ʵ���������÷���ķ�ʽʵ������һ��JobDetailʵ��  
        //��̬���룬��̬����
		JobDetail job = newJob(HelloJob.class)
			      .withIdentity("job1", "group1")
			      .build();

		//����job���񣬲���5s��ִ��һ��
		Trigger trigger = newTrigger()
				.withIdentity("trigger1", "group1")
			      .startNow()
			            .withSchedule(simpleSchedule()
			              .withIntervalInSeconds(5)
			              .repeatForever())            
			              .build();
        
		//������job�ʹ���ʱ����뵽scheduler����
		scheduler.scheduleJob(job, trigger);

		// ����
		scheduler.start();
		System.out.println("------- ��������Ѿ����� -----------------");
		try {
			// �ȴ�65�룬5sִ��һ�Σ�����ע�⣬������߳�ֹͣ�������ǲ���ִ�е�  
			Thread.sleep(60L * 1000L);
		} catch (Exception e) { }

		// �ر�scheduler
		scheduler.shutdown(true);
		System.out.println("------- �����ѹر� ---------------------");
	}
	
	public static void main(String[] args) throws Exception {
		run();
    }
}