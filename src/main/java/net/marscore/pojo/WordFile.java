package net.marscore.pojo;

import java.util.Date;
import java.util.Objects;

/**
 * @author Eden
 */
public class WordFile {
    private Long id;
    private String url;
    private String pdfUrl;
    private String name;
    private Date gmtCreated;
    private Date gmtModified;

    @Override
    public String toString() {
        return "WordFile{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", pdfUrl='" + pdfUrl + '\'' +
                ", name='" + name + '\'' +
                ", gmtCreated=" + gmtCreated +
                ", gmtModified=" + gmtModified +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WordFile wordFile = (WordFile) o;
        return Objects.equals(id, wordFile.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
