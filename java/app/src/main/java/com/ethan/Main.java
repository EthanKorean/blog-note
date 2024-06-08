package com.ethan;


import com.ethan.entity.Employee;
import com.ethan.service.EmployeeService;
import com.ethan.threadsafe.atomic.AtomicExample;
import org.apache.log4j.Logger;


public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {

        Employee employee = new Employee("노을","Our home protector");
        EmployeeService.getInstance().addEmployee(employee);
        System.out.println(EmployeeService.getInstance().getList());
    }

    public static void runAtomicTest(){
        logger.info(AtomicExample.getInteger().executeTest());
        logger.info(AtomicExample.getDouble().executeTest());
        logger.info(AtomicExample.getString().executeTest());
        logger.info(AtomicExample.getBoolean().executeTest());
    }

}