package com.htrj.common.page;

import java.io.File;

public class Template {
    public static String getRootPath() {

        String rootPath = "";
        try {
            File file = new File(Template.class.getResource("/").getFile());
            rootPath = file.getParentFile().getParent() + "\\";
            rootPath = java.net.URLDecoder.decode(rootPath, "utf-8");
            // rootPath = System.getProperty("user.dir") + "\\";
            return rootPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rootPath.trim();
    }
}
