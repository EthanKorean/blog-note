package com.ethan;


import com.ethan.common.loop.Loop;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {

        Loop.execute(5, ()-> logger.info("잘되고 있는지 확인") );
        Loop.execute(5, (idx)-> logger.info("잘되고 있는지 확인!"+idx) );
    }
}