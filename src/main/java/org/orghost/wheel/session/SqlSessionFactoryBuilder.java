package org.orghost.wheel.session;

import org.orghost.wheel.builder.xml.XMLConfigBuilder;
import org.orghost.wheel.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;
import java.util.Properties;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(String resource) {
        XMLConfigBuilder parser = new XMLConfigBuilder(resource);
        return new DefaultSqlSessionFactory(parser.parse());
    }

}
