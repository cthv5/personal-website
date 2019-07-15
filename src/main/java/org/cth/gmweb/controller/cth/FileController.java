package org.cth.gmweb.controller.cth;

import org.cth.gmweb.controller.BaseController;
import org.cth.gmweb.utils.CommonUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * @author cth
 * @date 2019/06/25
 */
@RestController
@RequestMapping("v1/file")
public class FileController extends BaseController {
    @PostMapping("upload")
    public Map<String, Object> uploadFile(MultipartFile upFile) {
        String baseUrl = "/opt/download/";

        File targetFile = new File(baseUrl);
        if (!targetFile.exists()){
            if (targetFile.mkdirs()) {
                log.info(">>>{}路径不存在,新建文件夹", baseUrl);
            }
        }
        try {
            log.info(">>>start upload");
            FileOutputStream out = new FileOutputStream(baseUrl);
            out.write(upFile.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return CommonUtil.returnResponse();
    }
}
