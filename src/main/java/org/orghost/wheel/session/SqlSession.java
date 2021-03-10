package org.orghost.wheel.session;

import java.io.Closeable;
import java.util.List;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public interface SqlSession extends Closeable {
    public <T> T selectOne(String statement,Object parameter);

}
