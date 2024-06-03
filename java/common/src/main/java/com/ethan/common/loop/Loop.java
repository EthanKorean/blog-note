package com.ethan.common.loop;

/**
 * 반복문을 쉽게 구현하기 위해 만든 util
 */
public class Loop {

    private Loop(){};

    @FunctionalInterface
    public interface LoopWork {
        void process();
    }
    @FunctionalInterface
    public interface LoopWorkWithIdx {
        void process(int index);
    }

    public static void execute(int count, LoopWork worker){
        for(int  i = 0; i < count; i++){
            worker.process();
        }//end for
    }//execute

    public static void execute(int count, LoopWorkWithIdx worker){
        for(int  i = 0; i < count; i++){
            worker.process(i);
        }//end for
    }//execute

}//class
