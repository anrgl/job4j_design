package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class AlertRabbit {
    public static void main(String[] args) {
        Properties config = new Properties();
        try (InputStream in =
                     ClassLoader.getSystemClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (Connection cn = getConnection()) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDataMap data = new JobDataMap();
            data.put("cn", cn);
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(
                            Integer.parseInt(config.getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection()
            throws IOException, ClassNotFoundException, SQLException {
        Properties cfg = new Properties();
        try (InputStream in =
                     ClassLoader.getSystemClassLoader().getResourceAsStream("rabbit.properties")) {
            cfg.load(in);
        }
        Class.forName(cfg.getProperty("rabbit.driver"));
        return DriverManager.getConnection(
                cfg.getProperty("rabbit.url"),
                cfg.getProperty("rabbit.login"),
                cfg.getProperty("rabbit.password")
        );
    }

    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here...");
            Connection cn =
                    (Connection) context.getJobDetail().getJobDataMap().get("cn");
            try (PreparedStatement statement = cn.prepareStatement(
                    "insert into rabbit(created_date) values (?)")) {
                statement.setLong(1, System.currentTimeMillis());
                statement.execute();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }
}
