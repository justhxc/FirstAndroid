package com.example.a.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class ContentHandler extends DefaultHandler {

    private static final String TAG ="Ctag";

    private String nodeName;

    private StringBuffer id;

    private StringBuffer name;

    private StringBuffer version;

    @Override
    public void startDocument() throws SAXException {
        /**开始解析XML时初始化节点元素对象*/
        id = new StringBuffer();
        name = new StringBuffer();
        version = new StringBuffer();
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        /**开始解析节点记录当前节点名称*/
        nodeName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        /**解析节点内容,根据上个方法记录的当前节点名,判断将内容添加到那个StringBuffer中*/
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        } else if ("name".equals(nodeName)){
            name.append(ch,start,length);
        } else if ("version".equals(nodeName)){
            version.append(ch,start,length);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       /**判断app节点解析结束后打印解析出的内容,去除空格和回车换行*/
        if ("app".equals(localName)){
            Log.d(TAG, "endElement: id is "+ id.toString().trim());
            Log.d(TAG, "endElement: name is "+ name.toString().trim() );
            Log.d(TAG, "endElement: version is " + version.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
