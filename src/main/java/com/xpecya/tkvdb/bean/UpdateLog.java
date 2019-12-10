package com.xpecya.tkvdb.bean;

import java.util.UUID;

/**
 * 数据更新log
 */
public class UpdateLog {

    /**
     * 更新操作id
     * 更新操作id规则是UUID去掉横线
     * 固定16位
     */
    private String updateId;

    /**
     * 所属的键值
     * 不包含数据库名称
     * 以\0结尾
     */
    private String key;

    /**
     * 修改后/创建后的数值
     * 以;结尾
     */
    private String value;

    public UpdateLog(String key, String value) {
        this.updateId = UUID.randomUUID().toString().replaceAll("-", "");
        this.key = key;
        this.value = value;
    }

    /**
     * 根据log日志数据获取结构体
     * @param log 日志内容
     */
    public UpdateLog(String log) {
        String[] split = log.split("\0");
        this.updateId = split[0].substring(0, 16);
        this.key = split[0].substring(16);
        this.value = split[1];
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return updateId + key + '\0' + value + ';';
    }
}
