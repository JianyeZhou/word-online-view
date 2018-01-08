package net.marscore.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.*;

/**
 * @author Eden
 */
public class FileStreamUtil {
    public static File streamToTempFile(InputStream inputStream, String extension, String usage) {
        String workDir = FileUtils.getTempDirectoryPath()+"/marscore/temp/"+usage+"/";
        workDir = FilenameUtils.separatorsToSystem(workDir);
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
        return inputFile;
    }
    public static void closeStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    /**
     * 获得文件扩展名
     * @param filename
     * @return 返回扩展名不包括点 .
     */
    public static String getExtension(String filename) {
        return filename.substring(filename.lastIndexOf('.')+1, filename.length());
    }
}
