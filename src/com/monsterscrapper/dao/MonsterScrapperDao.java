/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.dao;

//import com.monsterscrapper.entity.CompanyDetails;
import com.monsterscrapper.entity.CrawledJobs;
import com.monsterscrapper.entity.JobDetails;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author GLB-214
 */
public interface MonsterScrapperDao {

    public void setDataSource(DataSource ds);

    public void createTables();

    public void insertCrawledJobsUrlData(CrawledJobs objCrawledJobs);

    public void insertJobDetailsCrawledData(JobDetails objJobDetails);

//    public void insertCompanyDetailsData(CompanyDetails objCompanyDetails);
    
    public List<CrawledJobs> getCrawledJobsDataList();

}
