package net.marscore.common;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 * @author Eden
 */
public class PdfConverter {
    private final String openOfficeHome;

    public PdfConverter(String openOfficeHome) {
        this.openOfficeHome = openOfficeHome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PdfConverter that = (PdfConverter) o;
        return Objects.equals(openOfficeHome, that.openOfficeHome);
    }

    @Override
    public int hashCode() {

        return Objects.hash(openOfficeHome);
    }

    public File officeToPDF(InputStream inputStream, String type) {
        return officeToPDF(inputStream, openOfficeHome, type);
    }

    public static File officeToPDF(InputStream inputStream, String openOfficeHome, String extension) {
        String workDir = FileUtils.getTempDirectoryPath()+"/marscore/temp/pdfConverter/";
        File workPath = new File(workDir);
        if (!workPath.exists()) {
            if (!workPath.mkdirs()) {
                throw new RuntimeException("无当前用户文件下的读写权限");
            }
        }
        String inputFilePath = FilenameUtils.separatorsToSystem(workDir+"/inputFile-"+CommonUtil.getRandomString(10)+"."+extension);
        File inputFile = new File(inputFilePath);
        try {
            FileUtils.copyToFile(inputStream, inputFile);
        } catch (IOException e) {
            throw new RuntimeException("无法创建原始文档的临时文件", e);
        }
        String outputFilePath = FilenameUtils.separatorsToSystem(workDir+"/outputFile-"+CommonUtil.getRandomString(10)+".pdf");
        File outputFile = new File(outputFilePath);
        officeToPDF(inputFile, outputFile, openOfficeHome);
        inputFile.delete();
        return outputFile;
    }

    public void officeToPDF(File inputFile, File outputFile) {
        officeToPDF(inputFile, outputFile, openOfficeHome);
    }
    public static void officeToPDF(File inputFile, File outputFile, String openOfficeHome) {
        Process pro = null;
        OpenOfficeConnection connection = null;
        try {
            // 3  如果目标路径不存在, 则新建该路径
            if (!outputFile.getParentFile().exists()) {
                if (outputFile.getParentFile().mkdirs()){
                    throw new RuntimeException("无读写权限");
                }
            }
            // 4 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
            if (!openOfficeHome.endsWith("/") && !openOfficeHome.endsWith("\\")) {
                openOfficeHome = openOfficeHome+"/";
            }
            openOfficeHome = openOfficeHome+"program/";
            // 5 转换为当前系统的路径格式
            openOfficeHome = FilenameUtils.separatorsToSystem(openOfficeHome);
            // 7 启动OpenOffice的服务 , 注意端口不要冲突
            String command = openOfficeHome
                    + "soffice -headless -accept=\"socket,host=127.0.0.1,port=8300;urp;\" -nofirststartwizard";
            pro = Runtime.getRuntime().exec(command);
            // 8 连接到OpenOffice ，注意端口要与上面一致
            connection = new SocketOpenOfficeConnection(
                    "127.0.0.1", 8300);
            connection.connect();
            // 8 转换pdf
            DocumentConverter converter = new OpenOfficeDocumentConverter(
                    connection);
            converter.convert(inputFile, outputFile);
        } catch (IOException e) {
            throw new RuntimeException("转换失败",e);
        } finally {
            if (connection!=null) {
                // 9 关闭连接
                connection.disconnect();
            }
            if (pro!=null) {
                // 10  关闭OpenOffice服务的进程 ， 避免占用CPU
                pro.destroy();
            }
        }
    }

    public void officeToPDF(String sourceFile, String destFile) {
        officeToPDF(sourceFile, destFile);
    }
    public static void officeToPDF(InputStream inputStream, String sourceFile, String destFile, String openOfficeHome) {
        // 1  找不到源文件, 则返回false
        File inputFile = new File(sourceFile);
        if (!inputFile.exists()) {
            throw new RuntimeException("需转换的文件不存在");
        }
        File outputFile = new File(destFile);
        // 2 已经存在pdf文件，返回成功
        if (outputFile.exists()) {
            return;
        }
        officeToPDF(inputFile, outputFile, openOfficeHome);
    }
}