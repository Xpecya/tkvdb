package com.xpecya.tkvdb;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 数据库类
 * 包含数据库操作
 *
 * 执行器包含两个线程池
 * 一个负责多线程的执行java逻辑
 * 一个负责单线程的处理文件io
 * 单线程的目的是避免各种线程锁的消耗
 * 一般而言，这个线程处理的是事务日志的读写
 * 除非单线程池中没有其他任务，否则不会执行实际数据的读写
 * 而实际数据多数时候是在内存中多线程读取的，性能应该是可观的
 *
 * 线程池过期时间约等于无穷大，因为数据库的任务是不可以过期的
 */
public class Database {

    private static final int QUEUE_LENGTH = 1024;
    private static final Runtime RUNTIME = Runtime.getRuntime();
    private static final int LOGIC_POOL_SIZE = RUNTIME.availableProcessors() - 1;

    private static final Map<String, String> DATA_MAP = new HashMap<>(4096);

    private static final String DATABASE_MANAGEMENT_DB = "tkvdb.tr_level.%s";

    /**
     * 线程池容量控制为1024条，以控制内存消耗
     * 如果日志读写任务超过1024条，多余的任务会在LOGIC_EXECUTOR中写入任务记录日志文件记录下来
     */
    private static final ThreadPoolExecutor FILE_EXECUTOR = new ThreadPoolExecutor(
            1, 1, Long.MAX_VALUE, TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(QUEUE_LENGTH)
    );

    /**
     * 线程池容量控制为1024条，以控制内存消耗
     * 如果多线程任务超过1024条，那就停止推入executor
     * 当然 这个停止过程，挺吃性能的
     */
    private static final ThreadPoolExecutor LOGIC_EXECUTOR = new ThreadPoolExecutor(
            LOGIC_POOL_SIZE, LOGIC_POOL_SIZE, Long.MAX_VALUE,
            TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(QUEUE_LENGTH)
    );

    /**
     * 创建数据库，并指定事务隔离级别
     * @param databaseName 数据库名称
     * @param level 隔离级别
     * @return 创建结果
     */
    public boolean createDatabase(String databaseName, TransactionLevel level) {
        // TODO 实现
        return false;
    }

    /**
     * 创建数据库，使用默认的隔离级别
     * @param databaseName 数据库名称
     * @return 创建结果
     */
    public boolean createDatabase(String databaseName) {
        return createDatabase(databaseName, TransactionLevel.READ_UNCOMMITTED);
    }

    /**
     * 根据key获取数据
     * key值的格式为库名.对象名.属性名
     * 例如：github.xpecya.tkvdb
     * 数据库为github
     * 对象为xpecya
     * 属性为tkvdb
     * 相当于是创建一个事务，执行read命令，然后提交
     * @return 数据
     */
    public String get(String key) {
        // TODO 实现
        return null;
    }

    /**
     * 将键值对写入数据库
     * 键值格式参考get
     * 如果访问到了不存在的database/object
     * 就自动建一个新的
     * 相当于创建一个事务，执行set命令，然后提交
     * @param key 键
     * @param value 值
     * @return 写入结果
     */
    public boolean set(String key, String value) {
        // TODO 实现
        return false;
    }

    /**
     * 开启一个事务
     * @return 事务id
     */
    public String start() {
        // TODO 实现
        return null;
    }

    /**
     * 提交一个事务
     * @param transactionId 事务id
     * @return 提交结果
     */
    public boolean commit(String transactionId) {
        // TODO 实现
        return false;
    }

    /**
     * 回滚一个事务
     * @param transactionId 事务id
     * @return 回滚结果
     */
    public boolean rollback(String transactionId) {
        // TODO 实现
        return false;
    }

    /**
     * 带有事务的get请求
     * 格式参考get方法备注
     * @param transactionId 事务id
     * @param key 数据键
     * @return 数据
     */
    public String tget(String transactionId, String key) {
        // TODO 实现
        return null;
    }

    /**
     * 带有事务的set请求
     * @param transactionId 事务id
     * @param key 键
     * @param value 值
     * @return 写入结果
     */
    public boolean tset(String transactionId, String key, String value) {
        // TODO 实现
        return false;
    }
}
