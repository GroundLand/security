package com.cl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by evel on 2018/1/31.
 */
public class PropertiesUtil {
    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    public static Properties formLocation(String location) throws IOException{
        Properties properties = new Properties();
        InputStreamReader in=null;
        FileInputStream inStream=null ;
        try {
            inStream = new FileInputStream(new File(location));
            in = new InputStreamReader(inStream,("UTF-8"));    // 处理中文字符流
            properties.load(in);
        } catch (Exception e) {
            log.error("无法找到并使用配置文件: [ " + location+" ]");
        }
        finally {
            if (inStream!=null) {
                inStream.close();
            }
            if (in!=null) {
                in.close();
            }
        }

        return properties;
    }
}
