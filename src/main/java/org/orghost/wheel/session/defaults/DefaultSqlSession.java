package org.orghost.wheel.session.defaults;

import org.orghost.wheel.executor.Executor;
import org.orghost.wheel.session.Configuration;
import org.orghost.wheel.session.SqlSession;

import java.util.List;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;
    private final Executor executor;

    private final boolean autoCommit;

    public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
        this.configuration = configuration;
        this.executor = executor;
        this.autoCommit = autoCommit;
    }

    @Override
    public void close() {
        try {
//            executor.close(isCommitOrRollbackRequired(false));
        } finally {
            System.out.println("reset");
        }
    }

    private boolean isCommitOrRollbackRequired(boolean force) {
        return (!autoCommit) || force;
    }


    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }
}
