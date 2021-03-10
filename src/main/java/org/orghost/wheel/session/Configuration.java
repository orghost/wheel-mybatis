package org.orghost.wheel.session;

import org.orghost.wheel.executor.Executor;
import org.orghost.wheel.mapping.Environment;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public class Configuration {
    protected Environment environment;
    protected ExecutorType defaultExecutorType = ExecutorType.SIMPLE;

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ExecutorType getDefaultExecutorType() {
        return defaultExecutorType;
    }

    public void setDefaultExecutorType(ExecutorType defaultExecutorType) {
        this.defaultExecutorType = defaultExecutorType;
    }

    public Executor newExecutor(ExecutorType executorType) {
        executorType = executorType == null ? defaultExecutorType : executorType;
        executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
        Executor executor;
        // 根据executorType返回executor
        executor = null;
        return executor;
    }
}
