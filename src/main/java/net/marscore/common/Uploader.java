package net.marscore.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Eden
 */
public abstract class Uploader {
    //获得文件扩展名，返回值包括点 .

    public static final String UPLOAD_MODEL_LOCAL = "local";
    public static final String UPLOAD_MODEL_OSS = "oss";

    public static String getMd5Path(InputStream inputStream, String fileExtension) {
        Calendar date = Calendar.getInstance();
        date.setTime(new Date());
        int year = date.get(Calendar.YEAR);
        int month = 1+date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        String md5 = CommonUtil.calculateMD5(inputStream);
        StringBuffer sb = new StringBuffer();
        sb.append("/");
        sb.append(year);
        sb.append("/");
        sb.append(month);
        sb.append("/");
        sb.append(day);
        sb.append("/");
        sb.append(md5);
        sb.append(".");
        sb.append(fileExtension);
        return sb.toString();
    }

    /**
     * 上传输入流到指定路径
     * @param inputStream
     * @param uploadPath
     * @return 上传后所在的路径
     */
    public abstract String upload(InputStream inputStream, String uploadPath);

    /**
     * 上传文件到指定路径
     * @param filePath
     * @param uploadPath
     * @return 上传后所在的路径
     */
    public String upload(String filePath, String uploadPath) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            return upload(inputStream, uploadPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件打开失败", e);
        } finally {
            FileStreamUtil.closeStream(inputStream);
        }
    }

    /**
     * 上传输入流到当前日期路径，并以文件的MD5值命名
     * @param inputStream
     * @param fileExtension
     * @return 上传后所在的路径
     */
    public String uploadAsMd5Path(InputStream inputStream, String fileExtension) {
        File file = FileStreamUtil.streamToTempFile(inputStream, fileExtension, "uploader");
        InputStream inputStreamTemp1 = null;
        InputStream inputStreamTemp2 = null;
        try {
            inputStreamTemp1 = new FileInputStream(file);
            inputStreamTemp2 = new FileInputStream(file);
            String uploadPath = getMd5Path(inputStreamTemp1, fileExtension);
            return upload(inputStreamTemp2, uploadPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("打开上传临时转化文件失败", e);
        } finally {
            FileStreamUtil.closeStream(inputStreamTemp1);
            FileStreamUtil.closeStream(inputStreamTemp2);
            file.delete();
        }
    }

    /**
     * 上传文件到当前日期路径，并以文件的MD5值命名
     *
     * @param outputFile
     * @return 上传后所在的路径
     */
    public String uploadAsMd5Path(File outputFile) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(outputFile);
            String uploadPath = getMd5Path(inputStream, FileStreamUtil.getExtension(outputFile.getName()));
            return upload(outputFile.getPath(), uploadPath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件打开错误",e);
        } finally {
            FileStreamUtil.closeStream(inputStream);
        }
    }
}
