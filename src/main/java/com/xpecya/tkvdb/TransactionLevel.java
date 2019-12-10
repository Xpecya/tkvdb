package com.xpecya.tkvdb;

/**
 * 事务隔离级别
 */
public enum TransactionLevel {

    /**
     * 读未提交
     */
    READ_UNCOMMITTED,

    /**
     * 读已提交
     */
    READ_COMMITTED,

    /**
     * 可重复读
     */
    REPEATABLE_READ,

    /**
     * 串行化
     */
    SERIALIZABLE;

    public static TransactionLevel getInstance(int level) {
        switch(level) {
            case 0: {
                return READ_UNCOMMITTED;
            }
            case 1: {
                return READ_COMMITTED;
            }
            case 2: {
                return REPEATABLE_READ;
            }
            case 3: {
                return SERIALIZABLE;
            }
            default: {
                throw new IllegalArgumentException("no such level: " + level);
            }
        }
    }
}
