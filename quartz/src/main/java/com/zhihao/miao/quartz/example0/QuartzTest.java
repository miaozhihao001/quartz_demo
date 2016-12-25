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
		//从StdSchedulerFactory工厂得到Scheduler的实例
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		//定义一个 job对象并绑定我们写的  HelloJob类 
		//真正执行的任务并不是Job接口的实例，而是用反射的方式实例化的一个JobDetail实例  
        //静态导入，静态方法
		JobDetail job = newJob(HelloJob.class)
			      .withIdentity("job1", "group1")
			      .build();

		//触发job任务，并且5s钟执行一次
		Trigger trigger = newTrigger()
				.withIdentity("trigger1", "group1")
			      .startNow()
			            .withSchedule(simpleSchedule()
			              .withIntervalInSeconds(5)
			              .repeatForever())            
			              .build();
        
		//将任务job和触发时间放入到scheduler当中
		scheduler.scheduleJob(job, trigger);

		// 启动
		scheduler.start();
		System.out.println("------- 任务调度已经启动 -----------------");
		try {
			// 等待65秒，5s执行一次，这里注意，如果主线程停止，任务是不会执行的  
			Thread.sleep(60L * 1000L);
		} catch (Exception e) { }

		// 关闭scheduler
		scheduler.shutdown(true);
		System.out.println("------- 调度已关闭 ---------------------");
	}
	
	public static void main(String[] args) throws Exception {
		run();
    }
}