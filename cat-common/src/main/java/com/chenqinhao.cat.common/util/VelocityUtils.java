package com.chenqinhao.cat.common.util;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by chenqinhao on 2017/7/21.
 */
public class VelocityUtils {

    public static void generate(String inputPath, String outputPath,
                                VelocityContext context) throws IOException {
        try {
            Properties properties = new Properties();
            properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, getPath(inputPath));
            Velocity.init(properties);
            Template template = Velocity.getTemplate(getFile(inputPath), "utf-8");
            File outputFile = new File(outputPath);
            FileWriterWithEncoding writer = new FileWriterWithEncoding(outputFile, "utf-8");
            template.merge(context, writer);
            writer.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public static String getPath(String filePath) {
        String path = "";
        if (StringUtils.isNoneBlank(filePath)) {
            path = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        }
        return path;
    }

    public static String getFile(String filePath) {
        String file = "";
        if (StringUtils.isNoneBlank(filePath)) {
            file = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return file;
    }
}
