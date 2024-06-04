package com.ethan.threadsafe.atomic;

/**
 *  자식에게만 선언된 멤버 변수의 값을
 *  부모에게 전달하기 위한 인터페이스
 */
@FunctionalInterface
public interface VariableGetter {
        Object get();
}
