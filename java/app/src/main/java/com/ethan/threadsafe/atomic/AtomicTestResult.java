package com.ethan.threadsafe.atomic;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


/**
 * 테스트 실행 결과
 * @see AtomicTester#executeTest
 */
@Getter
@Builder
@ToString
public class AtomicTestResult {

    /** 원시 타입 값 */
    private Object primaryValue;

    /** Atomic 타입 값 */
    private Object atomicValue;

    /** 원시 타입 연산 시간 */
    private long primaryCost;

    /** Atomic 타입 연산 시간 */
    private long atomicCost;

}
