package org.orghost.wheel.session;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public interface SqlSessionFactory {

    SqlSession openSession();
}
