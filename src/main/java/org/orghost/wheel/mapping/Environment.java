package org.orghost.wheel.mapping;

import java.sql.Connection;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public final class Environment {

    public Environment(Connection connection) {
        this.connection = connection;
    }

    Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
