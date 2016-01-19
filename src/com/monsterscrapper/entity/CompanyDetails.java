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
@Table(name = "company_details")
public class CompanyDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "COMPANY_ID")
    private Integer companyId;
    @Column(name = "LOCATION")
    private String location;
    @Column(name = "INDUSTRIES")
    private String industries;
    @Column(name = "CONTACT_INFORMATION")
    private String contactInformation;
    @Column(name = "PHONE_NO")
    private String phoneNo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "companyId")
    private Collection<JobDetails> jobDetailsCollection;

    public CompanyDetails() {
    }

    public CompanyDetails(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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

    @XmlTransient
    public Collection<JobDetails> getJobDetailsCollection() {
        return jobDetailsCollection;
    }

    public void setJobDetailsCollection(Collection<JobDetails> jobDetailsCollection) {
        this.jobDetailsCollection = jobDetailsCollection;
    }

}
