package com.ethan.threadsafe.atomic;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicExample {
    private AtomicExample(){};


    public static IntegerTester getInteger(){
        return new IntegerTester();
    }
    public static DoubleTester getDouble(){
        return new DoubleTester();
    }
    public static StringTester getString(){
        return new StringTester();
    }

    public static BooleanTester getBoolean(){
        return new BooleanTester();
    }


    public static class IntegerTester extends AtomicTester {
        private int primary;

        IntegerTester() {
            AtomicInteger atomic = new AtomicInteger();
            setVariableGetter(()->primary, atomic::get);
            addPrimaryWorkList(()-> primary++);
            addAtomicWorkList(atomic::incrementAndGet);
        }
    }//class


    public static class DoubleTester extends AtomicTester {
        private int primary;
        DoubleTester() {
            AtomicReference<Double> atomic = new AtomicReference<>(0.0);
            setVariableGetter(()->primary, atomic::get);
            addPrimaryWorkList(()-> primary++);
            addAtomicWorkList(()-> atomic.getAndUpdate(v->v+1.0));
        }
    }//class

    public static class StringTester extends AtomicTester{

        private String primary;

        StringTester(){
            primary="";
            AtomicReference<String> atomic = new AtomicReference<>("");
            setVariableGetter(()-> primary.length(), ()-> atomic.get().length());
            addPrimaryWorkList(()-> primary+=" ");
            addAtomicWorkList(()-> atomic.getAndUpdate(v->new StringBuilder(v).append(" ").toString()));
        }
    }


    public static class BooleanTester extends AtomicTester {
        private boolean primary;

        BooleanTester() {
            AtomicBoolean atomic = new AtomicBoolean(false);
            setVariableGetter(()->primary, atomic::get);
            addPrimaryWorkList(()-> primary = !primary);
            addAtomicWorkList(()-> {
                boolean currentValue;
                boolean newValue;
                do {
                    currentValue = atomic.get();
                    newValue = !currentValue;
                } while (!atomic.compareAndSet(currentValue, newValue));
            });
        }
    }//class


}//class
