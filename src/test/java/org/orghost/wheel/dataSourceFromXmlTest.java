package org.orghost.wheel;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.orghost.wheel.session.SqlSession;
import org.orghost.wheel.session.SqlSessionFactory;
import org.orghost.wheel.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public class dataSourceFromXmlTest {
    private static SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    static void setUp() throws Exception {
        // create an SqlSessionFactory
        String resource = "mybatis-config.xml";
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(resource);
        System.out.println();
    }

    @Test
    void queryForElement() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            // 获取mapper
            // 查询数据
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
