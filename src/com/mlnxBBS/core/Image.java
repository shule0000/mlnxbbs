package com.mlnxBBS.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Image entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="image"
    ,catalog="mlnx"
)

public class Image  implements java.io.Serializable {


    // Fields    

     private Integer iid;
     private String imgUrl;
     private String imgSize;
     private String imgDetail;
     private String imgType;
     private Integer imgStatus;
     private Integer imgPosition;


    // Constructors

    /** default constructor */
    public Image() {
    }

    
    /** full constructor */
    public Image(String imgUrl, String imgSize, String imgDetail, String imgType, Integer imgStatus, Integer imgPosition) {
        this.imgUrl = imgUrl;
        this.imgSize = imgSize;
        this.imgDetail = imgDetail;
        this.imgType = imgType;
        this.imgStatus = imgStatus;
        this.imgPosition = imgPosition;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="iid", unique=true, nullable=false)

    public Integer getIid() {
        return this.iid;
    }
    
    public void setIid(Integer iid) {
        this.iid = iid;
    }
    
    @Column(name="imgUrl", nullable=false, length=50)

    public String getImgUrl() {
        return this.imgUrl;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    @Column(name="imgSize", nullable=false, length=50)

    public String getImgSize() {
        return this.imgSize;
    }
    
    public void setImgSize(String imgSize) {
        this.imgSize = imgSize;
    }
    
    @Column(name="imgDetail", nullable=false, length=2000)

    public String getImgDetail() {
        return this.imgDetail;
    }
    
    public void setImgDetail(String imgDetail) {
        this.imgDetail = imgDetail;
    }
    
    @Column(name="imgType", nullable=false, length=50)

    public String getImgType() {
        return this.imgType;
    }
    
    public void setImgType(String imgType) {
        this.imgType = imgType;
    }
    
    @Column(name="imgStatus", nullable=false)

    public Integer getImgStatus() {
        return this.imgStatus;
    }
    
    public void setImgStatus(Integer imgStatus) {
        this.imgStatus = imgStatus;
    }
    
    @Column(name="imgPosition", nullable=false)

    public Integer getImgPosition() {
        return this.imgPosition;
    }
    
    public void setImgPosition(Integer imgPosition) {
        this.imgPosition = imgPosition;
    }
   








}