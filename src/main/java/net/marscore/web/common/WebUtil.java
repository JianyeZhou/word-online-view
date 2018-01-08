package net.marscore.web.common;

import net.marscore.common.AliUploader;
import net.marscore.common.LocalUploader;
import net.marscore.common.PdfConverter;
import net.marscore.common.Uploader;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Eden
 */
@Component
public class WebUtil {
    public PdfConverter getPdfConverter(){
        InputStream inStream = WebUtil.class.getResourceAsStream("/config.properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
        } catch (IOException e) {
            throw new RuntimeException("配置文件加载错误",e);
        }
        String openOfficePath = prop.getProperty("openOfficePath");
        return new PdfConverter(openOfficePath);
    }

    public Uploader getUploader(HttpServletRequest request){
        InputStream inStream = WebUtil.class.getResourceAsStream("/config.properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
        } catch (IOException e) {
            throw new RuntimeException("配置文件加载错误",e);
        }
        String uploadModel = prop.getProperty("uploadModel");
        if (Uploader.UPLOAD_MODEL_LOCAL.equals(uploadModel)) {
            return getLocalUploader(request);
        }
        return getAliUploader();
    }

    public LocalUploader getLocalUploader(HttpServletRequest request){
        InputStream inStream = WebUtil.class.getResourceAsStream("/config.properties");
        String uploadPath = "upload/";
        Properties prop = new Properties();
        try {
            prop.load(inStream);
        } catch (IOException e) {
            throw new RuntimeException("配置文件加载错误",e);
        }
        String host = prop.getProperty("localHost");
        String endWith = "/";
        if (!host.endsWith(endWith)){
            host = host+'/';
        }
        try {
            inStream.close();
        } catch (IOException e) {
            // do nothing
        }
        String rootDir = request.getServletContext().getRealPath("/")+'/'+uploadPath;
        rootDir = FilenameUtils.separatorsToSystem(rootDir);
        return new LocalUploader(rootDir, host+uploadPath);
    }

    public AliUploader getAliUploader(){
        InputStream inStream = WebUtil.class.getResourceAsStream("/config.properties");
        Properties prop = new Properties();
        try {
            prop.load(inStream);
        } catch (IOException e) {
            throw new RuntimeException("配置文件加载错误",e);
        }
        String endpoint = prop.getProperty("endpoint");
        String accessKeyId = prop.getProperty("accessKeyId");
        String accessKeySecret = prop.getProperty("accessKeySecret");
        String bucketName = prop.getProperty("bucketName");
        String host = prop.getProperty("ossHost");
        String endWith = "/";
        if (!host.endsWith(endWith)){
            host = host+'/';
        }
        try {
            inStream.close();
        } catch (IOException e) {
            // do nothing
        }
        return new AliUploader(endpoint, accessKeyId, accessKeySecret, bucketName, host);
    }
}
