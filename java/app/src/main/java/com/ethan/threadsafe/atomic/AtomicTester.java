package com.ethan.threadsafe.atomic;

import com.ethan.common.loop.Loop;
import java.util.ArrayList;
import java.util.List;

abstract class AtomicTester {

    /** 반복문 횟수 */
    private static final int LOOP_CNT = 10000;

    /** 각 쓰래드 생성 갯수 */
    private static final int THREAD_CNT = 10;

    private final List<Thread> primaryList = new ArrayList<>();
    private final List<Thread> atoimcList = new ArrayList<>();

    private VariableGetter primaryGetter;
    private VariableGetter atomicGetter;

    public AtomicTestResult executeTest(){
        try {

            long primaryCost = runAndGetCost(primaryList);
            long atomicCost = runAndGetCost(atoimcList);

            return AtomicTestResult.builder()
                    .primaryCost(primaryCost)
                    .primaryValue(primaryGetter.get())
                    .atomicCost(atomicCost)
                    .atomicValue(atomicGetter.get())
                    .build();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void setVariableGetter(VariableGetter primaryGetter, VariableGetter atomicGetter){
        this.primaryGetter= primaryGetter;
        this.atomicGetter = atomicGetter;
    }

    protected void addPrimaryWorkList(Loop.LoopWork worker){
         Loop.execute(THREAD_CNT, ()->primaryList.add(Loop.getExecutableThread(LOOP_CNT, worker)));
    }

    protected void addAtomicWorkList(Loop.LoopWork worker){
        Loop.execute(THREAD_CNT, ()->atoimcList.add(Loop.getExecutableThread(LOOP_CNT, worker)));
    }

    private long runAndGetCost(List<Thread> list) throws InterruptedException{
        long startTime = System.currentTimeMillis();
        list.forEach(Thread::start);
        wait(list);
        return System.currentTimeMillis() - startTime;
    }

    private void wait(List<Thread> list) throws InterruptedException {
        for (Thread thread : list) {
            thread.join();
        }
    }

}


