package com.ryf.mybatis.builder.xml;

import com.ryf.mybatis.builder.BaseBuilder;
import com.ryf.mybatis.io.Resources;
import com.ryf.mybatis.mapping.MappedStatement;
import com.ryf.mybatis.mapping.SqlCommandType;
import com.ryf.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ryf
 * @version 1.0
 * @project mini-mybatis
 * @description XML 解析处理
 * @date 2023/7/12
 */
public class XMLConfigBuilder extends BaseBuilder {

    private Element root;

    public XMLConfigBuilder(Reader reader) {
        // 1.调用父类初始化Configuration
        super(new Configuration());
        // 2.dom4j 处理 xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(new InputSource(reader));
            // 获取根节点即configuration
            root = document.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration parse() {
        try {
            mapperElement(root.element("mappers"));
            return configuration;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void mapperElement(Element mappers) throws Exception {
        //获取xml配置文件中mappers标签下所有的mapper标签
        List<Element> elements = mappers.elements("mapper");
        for (Element element : elements) {
            // 解析处理，具体参照源码
            // 添加解析 SQL
            // configuration.addMappedStatement();
            String resource = element.attributeValue("resource");
            Reader reader = Resources.getResourceAsReader(resource);
            SAXReader saxReader = new SAXReader();
            Document read = saxReader.read(reader);
            Element root = read.getRootElement();
            // 命名空间
            String namespace = root.attributeValue("namespace");
            // select
            List<Element> selectElements = root.elements("select");
            for (Element node : selectElements) {
                String id = node.attributeValue("id");
                String parameterType = node.attributeValue("parameterType");
                String resultType = node.attributeValue("resultType");
                String sql = node.getText();
                // 匹配#{id}内的参数 替换为？占位符
                Map<Integer, String> parameter = new HashMap<>();
                Pattern pattern = Pattern.compile("(#\\{(.*?)})");
                Matcher matcher = pattern.matcher(sql);
                if (matcher.find()) {
                    for (int i = 1; i < matcher.groupCount(); i++) {
                        String group = matcher.group(i);
                        parameter.put(i, group);
                        sql = sql.replace("?", group);
                    }
                }
                String msId = namespace + "." + id;
                String nodeName = node.getName();
                SqlCommandType sqlCommandType = SqlCommandType.valueOf(nodeName.toUpperCase());
                MappedStatement mappedStatement = new MappedStatement.Builder(configuration, sqlCommandType, msId, parameterType, resultType, sql, parameter).build();
                // 添加解析 SQL
                configuration.addMappedStatement(mappedStatement);
            }
            //添加映射mapper的dao接口到核心配置中
            configuration.addMapper(Resources.classForName(namespace));
        }
    }
}
