package org.orghost.wheel.session.defaults;

import org.orghost.wheel.executor.Executor;
import org.orghost.wheel.mapping.Environment;
import org.orghost.wheel.session.*;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return openSessionFromDataSource(configuration.getDefaultExecutorType(), null, false);
    }

    private SqlSession openSessionFromDataSource(ExecutorType execType, TransactionIsolationLevel level, boolean autoCommit) {
        try {
            final Environment environment = configuration.getEnvironment();
            final Executor executor = configuration.newExecutor(execType);
            return new DefaultSqlSession(configuration, executor, autoCommit);
        } catch (Exception e) {
            throw new RuntimeException("Error opening session.  Cause: " + e, e);
        } finally {
            //
        }
    }
}
