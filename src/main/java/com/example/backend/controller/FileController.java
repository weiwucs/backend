package com.example.backend.controller;

import com.example.backend.utils.ResultUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileController {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

    @PostMapping("/uploads")
    public ResultUtil uploads(MultipartFile[] uploadFiles, HttpServletRequest request){
        String result = "";

        for(MultipartFile uploadFile : uploadFiles){
            String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
            System.out.println(realPath);

            String format = sdf.format(new Date());
            File folder = new File(realPath + format);
            if(!folder.isDirectory()){
                folder.mkdirs();
            }

            String oldName = uploadFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."), oldName.length());

            try {
                uploadFile.transferTo(new File(folder, newName));

                String filePath = request.getScheme() + "://" + request.getServerName() + ":" +
                        request.getServerPort() + "/uploads/" + format + "/" + newName;
                result += filePath;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ResultUtil.success("upload success", result);
    }

    @GetMapping("download")
    public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String realPath = request.getSession().getServletContext().getRealPath("uploads");
        System.out.println(realPath);
        FileInputStream is = new FileInputStream(new File(realPath, fileName));
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        PrintWriter writer = response.getWriter();
        IOUtils.copy(is, writer);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(writer);
    }
}
