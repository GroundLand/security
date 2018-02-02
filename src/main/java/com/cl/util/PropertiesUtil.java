package com.cl.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by evel on 2018/1/31.
 */
public class PropertiesUtil {
    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    public static Properties formClassPath(String name) throws IOException {
        Properties properties = new Properties();
        InputStream in = null;
        try {
            in = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
            properties.load(in);
        } catch (Exception e) {
        } finally {
            if (in != null) {
                in.close();
            }
        }
        return properties;
    }
}
