/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author GLB-214
 */
@Entity
@Table(name = "crawled_jobs")
public class CrawledJobs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOB_ID")
    private Integer jobId;
    @Column(name = "JOB_URL")
    private String jobUrl;
    @Basic(optional = false)
    @Column(name = "IS_CRAWLED")
    private int isCrawled;
    @Column(name = "KEYWORD")
    private String keyword;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "POSTED_DATE")
    private String postedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jobId")
    private Collection<JobDetails> jobDetailsCollection;

    public CrawledJobs() {
    }

    public CrawledJobs(Integer jobId) {
        this.jobId = jobId;
    }

    public CrawledJobs(Integer jobId, int isCrawled) {
        this.jobId = jobId;
        this.isCrawled = isCrawled;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public int getIsCrawled() {
        return isCrawled;
    }

    public void setIsCrawled(int isCrawled) {
        this.isCrawled = isCrawled;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    @XmlTransient
    public Collection<JobDetails> getJobDetailsCollection() {
        return jobDetailsCollection;
    }

    public void setJobDetailsCollection(Collection<JobDetails> jobDetailsCollection) {
        this.jobDetailsCollection = jobDetailsCollection;
    }
    
}
