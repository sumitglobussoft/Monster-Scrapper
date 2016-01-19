/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @Column(name = "JOB_TITLE")
    private String jobTitle;
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
    private String contactNumber;
    @Column(name = "ABOUT_JOB")
    private String aboutJob;
    @Column(name = "JOB_TYPE")
    private String jobType;
    @Column(name = "SALARY")
    private String salary;
    @Column(name = "JOB_REFERENCE_CODE")
    private String jobReferenceCode;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "INDUSTRIES")
    private String industries;
    @Column(name = "CONTACT_INFORMATION")
    private String contactInformation;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @JoinColumn(name = "JOB_ID", referencedColumnName = "JOB_ID")
    @ManyToOne(optional = false)
    private CrawledJobs jobId;

    public JobDetails() {
    }

    public JobDetails(Integer jobDetailsId) {
        this.jobDetailsId = jobDetailsId;
    }

    public Integer getJobDetailsId() {
        return jobDetailsId;
    }

    public void setJobDetailsId(Integer jobDetailsId) {
        this.jobDetailsId = jobDetailsId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndustries() {
        return industries;
    }

    public void setIndustries(String industries) {
        this.industries = industries;
    }

    public String getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public CrawledJobs getJobId() {
        return jobId;
    }

    public void setJobId(CrawledJobs jobId) {
        this.jobId = jobId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobDetailsId != null ? jobDetailsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobDetails)) {
            return false;
        }
        JobDetails other = (JobDetails) object;
        if ((this.jobDetailsId == null && other.jobDetailsId != null) || (this.jobDetailsId != null && !this.jobDetailsId.equals(other.jobDetailsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.monsterscrapper.entity.JobDetails[ jobDetailsId=" + jobDetailsId + " ]";
    }

}
