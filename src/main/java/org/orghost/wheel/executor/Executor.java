package org.orghost.wheel.executor;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public interface Executor {
    void close(boolean forceRollback);

    boolean isClosed();

    public <T> T query(String statement,Object parameter);
}
