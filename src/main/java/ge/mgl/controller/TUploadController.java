package ge.mgl.controller;

import ge.mgl.service.StorageService;
import ge.mgl.utils.MGLIOUtils;
import ge.mgl.utils.MGLStringUtils;
import ge.mgl.utils.RequestResponseWithSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class TUploadController {

    @Autowired
    StorageService storageService;

    @RequestMapping(value = "/file", method = RequestMethod.POST, headers = "content-type=multipart/*")
    @ResponseBody
    public RequestResponseWithSource<List<String>> uploadMultipleFileHandler(@RequestParam("file") MultipartFile[] files,
                                                                             @RequestParam("type") String object) {
        RequestResponseWithSource<List<String>> response = new RequestResponseWithSource<>(true);
        if (files.length == 0 || MGLStringUtils.IsNullOrBlank(object)) {
            response.setMessage("File size is null or empty.");
            response.setSuccess(false);
            return response;
        }
        if (!MGLIOUtils.limitFileSize(files)) {
            response.setMessage("Wrong file size.");
            response.setSuccess(false);
            return response;
        }
        if (!MGLIOUtils.isValidObjectPath(object)) {
            response.setMessage("This file path is not valid.");
            response.setSuccess(false);
            return response;
        }

        List<String> fileList = new ArrayList<>();
        for (MultipartFile file : files) {
            String extension = "";
            int index = file.getOriginalFilename().lastIndexOf('.');
            if (index >= 0) {
                extension = file.getOriginalFilename().substring(index + 1);
            }
            if (!MGLIOUtils.isValidExtension(extension.toLowerCase())) {
                response.setMessage("Wrong File Type.");
                response.setSuccess(false);
                return response;
            }

            String fileName = storageService.storeEncoded(file);
            fileList.add(fileName);
            //String f = storageService.getRootLocation().toAbsolutePath().toString() + File.separator + fileName;
            //storageService.deleteFile(f);
        }

        response.setSource(fileList);
        return response;
    }
}
