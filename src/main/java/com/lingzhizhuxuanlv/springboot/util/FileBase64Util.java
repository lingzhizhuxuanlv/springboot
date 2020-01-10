package com.lingzhizhuxuanlv.springboot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Base64;

public class FileBase64Util {

    public static String fileToBase64(String filePath) {
        try {
            FileInputStream in = new FileInputStream(filePath);
            byte[] fileByte = new byte[in.available()];
            in.read(fileByte);
            in.close();
            return Base64.getUrlEncoder().encodeToString(fileByte);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    public static void base64ToFile(String base64Code, String targetPath){
        try{
            File targetFile = new File(targetPath);
            if(!targetFile.getParentFile().exists()){
                targetFile.getParentFile().mkdirs();
            }
            byte[] fileByte = Base64.getUrlDecoder().decode(base64Code);
            FileOutputStream out = new FileOutputStream(targetPath);
            out.write(fileByte);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
