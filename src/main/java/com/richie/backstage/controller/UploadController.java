package com.richie.backstage.controller;

import com.richie.backstage.handler.Result;
import com.richie.backstage.service.StoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author richie on 2018.06.28
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${upload.dir}")
    private String uploadDir;


    private StoreService storeService;

    @Autowired
    public void setStoreService(StoreService storeService) {
        this.storeService = storeService;
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Result uploadImage(@RequestParam(value = "file") MultipartFile file, @RequestParam("store_id") int storeId) {
        if (file.isEmpty()) {
            return Result.createNoResult(Result.ErrorCode.UPLOAD_IMAGE_EMPTY);
        }

        logger.info("storeId:{}", storeId);

        File dest = getDestFile(file, uploadDir);
        logger.info("上传成功后的文件路径：" + dest);
        try {
            file.transferTo(dest);
            storeService.updateStoreLogo(storeId, "/upload/" + file.getOriginalFilename());
            return Result.createYesResult();
        } catch (Exception e) {
            logger.error("upload fail", e);
        }
        return Result.createNoResult(Result.ErrorCode.UPLOAD_IMAGE_FAILED);
    }

    private File getDestFile(MultipartFile file, String uploadDir) {
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("fileName:{}", fileName);
        // 文件上传后的路径
        File dest = new File(uploadDir, fileName);
        // 检测是否存在目录
        File parentFile = dest.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        return dest;
    }

}
