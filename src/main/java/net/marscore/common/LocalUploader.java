package net.marscore.common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @author Eden
 */
public class LocalUploader extends Uploader {
    private String localFileRoot;
    private String host;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LocalUploader that = (LocalUploader) o;
        return Objects.equals(localFileRoot, that.localFileRoot) &&
                Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {

        return Objects.hash(localFileRoot, host);
    }

    public LocalUploader(String localFileRoot, String host) {
        this.localFileRoot = localFileRoot;
        this.host = host;
    }

    public String getLocalFileRoot() {
        return localFileRoot;
    }

    public void setLocalFileRoot(String localFileRoot) {
        this.localFileRoot = localFileRoot;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String upload(InputStream inputStream, String uploadPath) {
        if (uploadPath.startsWith("/") ||uploadPath.startsWith("\\") ) {
            uploadPath = uploadPath.substring(1,uploadPath.length());
        }
        String uploadUrl = host + uploadPath;
        uploadPath = localFileRoot + uploadPath;
        uploadPath = FilenameUtils.separatorsToSystem(uploadPath);
        File file = new File(uploadPath);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new RuntimeException("位置不可读/写");
            }
        }
        try {
            FileUtils.copyToFile(inputStream, new File(uploadPath));
            return uploadUrl;
        } catch (IOException e) {
            throw new RuntimeException("上传文件到本地路径失败" ,e);
        }
    }
}
