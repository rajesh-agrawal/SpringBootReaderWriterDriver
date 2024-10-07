package com.action.reader.readerwriterreplica.datasource;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Aspect
@Component
public class DataSourceAspect {

    @Before("execution(* com.action.reader.readerwriterreplica.repository..*.save*(..)) || execution(* com.action.reader.readerwriterreplica.repository..*.insert*(..)) || execution(* com.action.reader.readerwriterreplica.repository..*.update*(..)) || execution(* com.action.reader.readerwriterreplica.repository..*.delete*(..))")
    public void setWriteDataSourceType() {
        System.out.println("Selecting writer database");
        DataSourceContextHolder.setDataSourceType(DataSourceType.WRITE);
    }

    public static AtomicBoolean reader=new AtomicBoolean(false);
    @Before("execution(* com.action.reader.readerwriterreplica.repository..*.find*(..)) || execution(* com.action.reader.readerwriterreplica.repository..*.get*(..)) || execution(* com.action.reader.readerwriterreplica.repository..*.read*(..))")
    public void setReadDataSourceType() {

        if(reader.get()) {
            System.out.println("Selecting reader  database ");
            DataSourceContextHolder.setDataSourceType(DataSourceType.READ);
            reader.set(!reader.get());
        }else{

            System.out.println("Selecting writer  database ");
            DataSourceContextHolder.setDataSourceType(DataSourceType.WRITE);
            reader.set(!reader.get());
        }
    }
}