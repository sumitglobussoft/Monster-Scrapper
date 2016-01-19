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
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author GLB-214
 */
public class MonsterScrapperDaoImpl implements MonsterScrapperDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    @Override
    public void createTables() {
        try {

//            String companySQL = "CREATE TABLE IF NOT EXISTS `company_details` (\n"
//                    + "  `COMPANY_ID` integer primary key autoincrement,\n"
//                    + "  `LOCATION` varchar(200) DEFAULT NULL,\n"
//                    + "  `INDUSTRIES` varchar(200) NOT NULL,\n"
//                    + "  `CONTACT_INFORMATION` varchar(300) NOT NULL,\n"
//                    + "  `PHONE_NO` varchar(12) DEFAULT NULL)";
//
//            jdbcTemplateObject.execute(companySQL);
//            System.out.println("company Table create");
            String crawledJobsSQL = "CREATE TABLE IF NOT EXISTS `crawled_jobs` (\n"
                    + "  `JOB_ID` integer primary key autoincrement,\n"
                    + "  `JOB_URL` varchar(500) DEFAULT NULL,\n"
                    + "  `IS_CRAWLED` integer NOT NULL DEFAULT '0',\n"
                    + "  `KEYWORD` varchar(200) DEFAULT NULL,\n"
                    + "  `COUNTRY` varchar(100) DEFAULT NULL,\n"
                    + "  `POSTED_DATE` varchar(20) DEFAULT NULL)";

            jdbcTemplateObject.execute(crawledJobsSQL);
            System.out.println("crawled Jobs Table create");

            String jobdetailsSQL = "CREATE TABLE IF NOT EXISTS `job_details` (\n"
                    + "  `JOB_DETAILS_ID` integer primary key autoincrement,\n"
                    + "  `JOB_ID` integer NOT NULL,\n"
                    + "  `JOB_TITLE` varchar(500) DEFAULT NULL,\n"
                    + "  `KEYWORD` varchar(200) DEFAULT NULL,\n"
                    + "  `COMPANY` varchar(300) DEFAULT NULL,\n"
                    + "  `JOB_LOCATION` varchar(200) DEFAULT NULL,\n"
                    + "  `EMAIL_ADDRESS` varchar(30) DEFAULT NULL,\n"
                    + "  `WEB_ADDRESS` varchar(50) DEFAULT NULL,\n"
                    + "  `CONTACT_NUMBER` varchar(20) DEFAULT NULL,\n"
                    + "  `ABOUT_JOB` text,\n"
                    + "  `JOB_TYPE` varchar(200) DEFAULT NULL,\n"
                    + "  `SALARY` varchar(200) DEFAULT NULL,\n"
                    + "  `JOB_REFERENCE_CODE` varchar(200) DEFAULT NULL,\n"
                    + "  `LOCATION` varchar(200) DEFAULT NULL,\n"
                    + "  `INDUSTRIES` varchar(300) DEFAULT NULL,\n"
                    + "  `CONTACT_INFORMATION` varchar(300) DEFAULT NULL,\n"
                    + "  `PHONE_NO` varchar(20) DEFAULT NULL,\n"
                    + "  FOREIGN KEY(JOB_ID) REFERENCES crawled_jobs(JOB_ID))";

            jdbcTemplateObject.execute(jobdetailsSQL);
            System.out.println("Job Details table created");

            System.out.println("done with condi");
        } catch (DataAccessException dataAccessException) {
//            dataAccessException.printStackTrace();
        }
    }

    @Override
    public void insertCrawledJobsUrlData(CrawledJobs objCrawledJobs) {

        String SQL = "insert into crawled_jobs (JOB_URL, IS_CRAWLED, KEYWORD, COUNTRY, POSTED_DATE) values (?,?, ?, ?, ?)";
        try {
            jdbcTemplateObject.update(SQL, objCrawledJobs.getJobUrl(), objCrawledJobs.getIsCrawled(), objCrawledJobs.getKeyword(),objCrawledJobs.getCountry(), objCrawledJobs.getPostedDate());
            System.out.println("Crawled Job Url Data inserted");
        } catch (Exception e) {
            System.out.println("-----insertCrawledJobsUrlData-----");
            e.printStackTrace();
        }
    }

    @Override
    public void insertJobDetailsCrawledData(JobDetails objJobDetails) {

        String SQL = "insert into  job_details (JOB_ID,  JOB_TITLE,KEYWORD,  COMPANY, JOB_LOCATION, EMAIL_ADDRESS, WEB_ADDRESS, CONTACT_NUMBER,  ABOUT_JOB, JOB_TYPE, SALARY, JOB_REFERENCE_CODE, LOCATION, INDUSTRIES, CONTACT_INFORMATION, PHONE_NO) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            jdbcTemplateObject.update(SQL, objJobDetails.getJobId().getJobId(), objJobDetails.getJobTitle(), objJobDetails.getKeyword(), objJobDetails.getCompany(), objJobDetails.getJobLocation(), objJobDetails.getEmailAddress(), objJobDetails.getWebAddress(), objJobDetails.getContactNumber(), objJobDetails.getAboutJob(), objJobDetails.getJobType(), objJobDetails.getSalary(), objJobDetails.getJobReferenceCode());
            System.out.println("Job Details Table Data inserted");
        } catch (Exception e) {
            System.out.println("-----insertJobDetailsCrawledData-----");
            e.printStackTrace();
        }
    }

//    @Override
//    public void insertCompanyDetailsData(CompanyDetails objCompanyDetails) {
//
//        String SQL = "insert into company_details (LOCATION, INDUSTRIES, CONTACT_INFORMATION, PHONE_NO) values (?, ?, ?, ?)";
//        try {
//            jdbcTemplateObject.update(SQL, objCompanyDetails.getLocation(), objCompanyDetails.getIndustries(), objCompanyDetails.getContactInformation(), objCompanyDetails.getPhoneNo());
//            System.out.println("Company Details Data inserted");
//        } catch (Exception e) {
//            System.out.println("-----insertCompanyDetailsData-----");
//            e.printStackTrace();
//        }
//    }
    @Override
    public List<CrawledJobs> getCrawledJobsDataList() {

        List<CrawledJobs> CrawledJobsDataList = null;
        String SQL = "Select * from crawled_jobs where IS_CRAWLED=0";
        try {
            CrawledJobsDataList = jdbcTemplateObject.query(SQL,
                    new BeanPropertyRowMapper(CrawledJobs.class));
            System.out.println("Fetching data");
        } catch (Exception e) {
        }
        return CrawledJobsDataList;
    }

}
