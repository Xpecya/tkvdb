package com.xpecya.tkvdb.bean;

import java.util.UUID;

/**
 * 事务日志
 */
public class TransactionLog {

    /**
     * 事务id
     * 事务id规则是UUID去掉横线
     * 固定16位
     */
    private String transactionId;

    /**
     * 事务状态
     * 0 已创建 未提交 未回滚
     * 1 提交
     * 2 回滚
     * 固定1位
     */
    private Integer status;

    public TransactionLog() {
        this.transactionId = UUID.randomUUID().toString().replaceAll("-", "");
        this.status = 0;
    }

    public TransactionLog(String log) {
        this.transactionId = log.substring(0, 16);
        this.status = Integer.valueOf(log.substring(16));
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return transactionId + status + ';';
    }
}
