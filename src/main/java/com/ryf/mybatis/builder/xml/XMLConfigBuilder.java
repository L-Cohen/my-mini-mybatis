package com.ryf.mybatis.builder.xml;

import com.ryf.mybatis.builder.BaseBuilder;
import com.ryf.mybatis.session.Configuration;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.Reader;
import java.util.List;

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
            root = document.getRootElement();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public Configuration parse() {
        mapperElement(root.element("mappers"));
        return configuration;
    }

    private void mapperElement(Element mappers) {
        List<Element> elements = mappers.elements();
        for (Element element : elements) {
            // 解析处理，具体参照源码
            // 添加解析 SQL
//            configuration.addMappedStatement();
        }
    }
}
