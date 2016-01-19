/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author GLB-214
 */
@Entity
@Table(name = "job_details")
public class JobDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JOB_DETAILS_ID")
    private Integer jobDetailsId;
    @Column(name = "TOB_TITLE")
    private String tobTitle;
    @Column(name = "KEYWORD")
    private String keyword;
    @Column(name = "COMPANY")
    private String company;
    @Column(name = "JOB_LOCATION")
    private String jobLocation;
    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;
    @Column(name = "WEB_ADDRESS")
    private String webAddress;
    @Column(name = "CONTACT_NUMBER")
    private Integer contactNumber;
    @Column(name = "URL")
    private String url;
    @Basic(optional = false)
    @Column(name = "POSTED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date postedDate;
    @Column(name = "ABOUT_JOB")
    private String aboutJob;
    @Column(name = "JOB_TYPE")
    private String jobType;
    @Column(name = "SALARY")
    private String salary;
    @Column(name = "JOB_REFERENCE_CODE")
    private String jobReferenceCode;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID")
    @ManyToOne(optional = false)
    private CrawledJobs jobId;
    @JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")
    @ManyToOne(optional = false)
    private CompanyDetails companyId;

    public JobDetails() {
    }

    public JobDetails(Integer jobDetailsId) {
        this.jobDetailsId = jobDetailsId;
    }

    public JobDetails(Integer jobDetailsId, Date postedDate) {
        this.jobDetailsId = jobDetailsId;
        this.postedDate = postedDate;
    }

    public Integer getJobDetailsId() {
        return jobDetailsId;
    }

    public void setJobDetailsId(Integer jobDetailsId) {
        this.jobDetailsId = jobDetailsId;
    }

    public String getTobTitle() {
        return tobTitle;
    }

    public void setTobTitle(String tobTitle) {
        this.tobTitle = tobTitle;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public Integer getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public String getAboutJob() {
        return aboutJob;
    }

    public void setAboutJob(String aboutJob) {
        this.aboutJob = aboutJob;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJobReferenceCode() {
        return jobReferenceCode;
    }

    public void setJobReferenceCode(String jobReferenceCode) {
        this.jobReferenceCode = jobReferenceCode;
    }

    public CrawledJobs getJobId() {
        return jobId;
    }

    public void setJobId(CrawledJobs jobId) {
        this.jobId = jobId;
    }

    public CompanyDetails getCompanyId() {
        return companyId;
    }

    public void setCompanyId(CompanyDetails companyId) {
        this.companyId = companyId;
    }

}
