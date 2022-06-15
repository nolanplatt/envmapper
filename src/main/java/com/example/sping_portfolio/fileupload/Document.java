package com.example.sping_portfolio.fileupload;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documents")
public class Document {
    // encapsulated instance variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 512, nullable = false, unique = true)
    private String name;
    
    private long size;

    @Column(name = "upload_time")
    private Date uploadTime;
    private byte[] content;

    //constructors
    public Document() {}
    public Document(Long id, String name, long size) {
        this.id = id;
        this.name = name;
        this.size = size;
    }

    // getter methods
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public long getSize() {
        return size;
    }
    public Date getUploadTime() {
        return uploadTime;
    }
    public byte[] getContent() {
        return content;
    }

    // setter methods
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
    public void setContent(byte[] content) {
        this.content = content;
    }
}
