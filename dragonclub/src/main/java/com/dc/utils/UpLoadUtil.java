package com.dc.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class UpLoadUtil {

    public static String upload(MultipartFile file, int id , String address , String filePath) throws IOException{

        String fileRealName = file.getOriginalFilename();
        //后缀名 .jpg等
        String sufix = fileRealName.substring(fileRealName.lastIndexOf("."));
        String oldName = fileRealName.substring(0,fileRealName.lastIndexOf("."));

        //删除旧图片
        String delPath = filePath +"/"+ address.substring(address.lastIndexOf("/") + 1);
        boolean delete = new File(delPath).delete();
        //上传新的图片
        String savePath = filePath.substring(filePath.lastIndexOf("/")+1);
        File savedFile = new File(filePath);
        if (!savedFile.exists()) {
            savedFile.mkdirs();
        }
        //保存图片
        String newName = new Date().getTime()+ oldName + sufix;
        savePath = "/"+savePath + "/"+ newName;
        savedFile = new File(filePath+"/" ,newName);
        boolean isCreated = savedFile.createNewFile();
        if (isCreated == true){
            file.transferTo(savedFile);
        }

        return savePath;
    }
}
