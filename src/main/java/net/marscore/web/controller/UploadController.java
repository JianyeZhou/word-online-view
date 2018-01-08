package net.marscore.web.controller;

import net.marscore.common.CommonUtil;
import net.marscore.common.FileStreamUtil;
import net.marscore.common.PdfConverter;
import net.marscore.common.Uploader;
import net.marscore.pojo.WordFile;
import net.marscore.service.WordFileService;
import net.marscore.web.common.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Eden
 */
@Controller
public class UploadController {
    @Autowired
    WordFileService wordFileService;
    @Autowired
    WebUtil webUtil;

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public ModelAndView uploadSubmit() {
        return new ModelAndView("upload");
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public ModelAndView uploadSubmit(String name, MultipartFile file, HttpServletRequest request) {
        Uploader uploader = webUtil.getUploader(request);
        if (file==null) {
            return new ModelAndView("error", "message", "未选中文件");
        }
        String extension = FileStreamUtil.getExtension(file.getOriginalFilename());
        if (!extension.equals("doc") && !extension.equals("docx") && !extension.equals("ppt") && !extension.equals("pptx") && !extension.equals("xls") && !extension.equals("xlsx")) {
            return new ModelAndView("error", "message", "文件上传格式不正确");
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            if (CommonUtil.isEmpty(name)) {
                name = file.getOriginalFilename();
            } else {
                name = name+'.'+FileStreamUtil.getExtension(file.getOriginalFilename());
            }
            String url = uploader.uploadAsMd5Path(inputStream, extension);
            PdfConverter pdfConverter = webUtil.getPdfConverter();
            File pdfFile = pdfConverter.officeToPDF(file.getInputStream(), extension);
            String pdfUrl = uploader.uploadAsMd5Path(pdfFile);
            WordFile wordFile = new WordFile();
            wordFile.setName(name);
            wordFile.setPdfUrl(pdfUrl);
            wordFile.setUrl(url);
            pdfFile.delete();
            wordFileService.insert(wordFile);
            return new ModelAndView("redirect:/");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            FileStreamUtil.closeStream(inputStream);
        }
    }
}
