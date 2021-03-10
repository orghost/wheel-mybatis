package org.orghost.wheel.builder.xml;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.orghost.wheel.builder.BaseBuilder;
import org.orghost.wheel.mapping.Environment;
import org.orghost.wheel.session.Configuration;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author zhongjinbin 2021/3/10
 * @since 1.0-SNAPSHOT
 */
public class XMLConfigBuilder extends BaseBuilder {
    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    private boolean parsed;
    private Document document;

    public XMLConfigBuilder(String resource) {
        super(new Configuration());
        this.parsed = false;
        read(resource);
    }

    private void read(String resource) {
        try {
            InputStream stream = loader.getResourceAsStream(resource);
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            this.document = document;
        } catch (Exception e) {
            throw new RuntimeException("error occured while evaling xml " + resource);
        }
    }

    public Configuration parse(){
        if (parsed) {
            throw new RuntimeException("Each XMLConfigBuilder can only be used once.");
        }
        parsed = true;
        try {
            parseConfiguration();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("XMLConfigBuilder parseConfiguration" + e.getMessage());
        }
        return configuration;
    }

    private void parseConfiguration() throws ClassNotFoundException {
        Environment environment = new Environment(evalDataSourceConnection());
        configuration.setEnvironment(environment);
    }

    private  Connection evalDataSourceConnection() throws ClassNotFoundException {
        Element node = document.getRootElement();
        if (!node.getName().equals("database")) {
            throw new RuntimeException("root should be <database>");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        //获取属性节点
        for (Object item : node.elements("property")) {
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null) {
                throw new RuntimeException("[database]: <property> should contain name and value");
            }
            //赋值
            switch (name) {
                case "url" : url = value; break;
                case "username" : username = value; break;
                case "password" : password = value; break;
                case "driverClassName" : driverClassName = value; break;
                default : throw new RuntimeException("[database]: <property> unknown name");
            }
        }

        Class.forName(driverClassName);
        Connection connection = null;
        try {
            //建立数据库链接
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    //获取property属性的值,如果有value值,则读取 没有设置value,则读取内容
    private  String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }
}
