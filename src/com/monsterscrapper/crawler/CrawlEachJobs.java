/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.crawler;

import com.monsterscrapper.dao.MonsterScrapperDaoImpl;
import com.monsterscrapper.entity.CrawledJobs;
import com.monsterscrapper.entity.JobDetails;
import com.monsterscrapper.proxy.ProxyImport;
import com.monsterscrapper.ui.MainPage;
import static com.monsterscrapper.ui.MainPage.loggertextArea;
import com.monsterscrapper.utility.FetchPageWithProxy;
import com.monsterscrapper.utility.GetRequestHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author GLB-130
 */
public class CrawlEachJobs implements Callable<String> {

    CrawledJobs objCrawledJobs = null;
    JobDetails objJobDetails = new JobDetails();
    MonsterScrapperDaoImpl objMonsterScrapperDaoImpl;
    List<ProxyImport> proxyList;

    public CrawlEachJobs(CrawledJobs objCrawledJobs, MonsterScrapperDaoImpl objMonsterScrapperDaoImpl, List<ProxyImport> proxyList) {

        this.objCrawledJobs = objCrawledJobs;
        this.objMonsterScrapperDaoImpl = objMonsterScrapperDaoImpl;
        this.proxyList = proxyList;
    }

//    public static void main(String[] args) {
        // TODO code application logic here
//        CrawlEachJobs objCrawlEachJobs = new CrawlEachJobs();
//        String url = "http://jobview.monster.co.uk/J2EE-Technology-Architect-Job-London-London-UK-161074266.aspx?mescoid=1500138001001&jobPosition=2";
//        String url = "http://jobview.monster.co.uk/Client-Partner-Insurance-Consulting-Job-City-of-London-London-UK-161470797.aspx?mescoid=4100673001001&jobPosition=3";
//        String url = "http://job-openings.monster.co.uk/monster/d200e9fb-89cd-483d-b7be-75b198dd11c5?mescoid=1100011001001&jobPosition=11";
//        String url = "http://jobview.monster.co.uk/Support-Co-ordinator-SEN-Further-Education-Job-Reading-Home-Counties-UK-162067921.aspx?mescoid=4300692001001&jobPosition=15";
//        String url = "http://jobview.monster.co.uk/HGV-Class-1-Driver-Job-Swadlincote-Midlands-UK-161243768.aspx?jobPosition=2";
//        String url = "http://jobview.monster.co.uk/Resource-Planner-Job-Glasgow-Scotland-UK-162004357.aspx?mescoid=1300093001001&jobPosition=7";
//        String url = "http://jobview.monster.co.uk/Graduate-Java-Developer-Java-J2SE-Computer-Science-Job-London-London-UK-162016592.aspx?mescoid=1500127001001&jobPosition=1";
//        String url = "http://jobview.monster.co.uk/Java-Developer-%E2%80%93-London-FinTech-Start-Up-%E2%80%93-Core-Java-Spark-Hadoop-Big-Data-Machine-Learning-Job-London-London-UK-162282486.aspx?mescoid=1500127001001&jobPosition=1";
//        objCrawlEachJobs.checkPattern(url);
//    }

    public void checkPattern(CrawledJobs objCrawledJobs, String url, List<ProxyImport> proxyList) throws IOException, InterruptedException, URISyntaxException {
//        while (!MainPage.running) {
//        }
        String responseDetails = "";
        try {
            responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
        } catch (Exception ex) {
            Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Document detailsDoc = Jsoup.parse(responseDetails);
        Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
        String urllength = detailsDoc.toString();
//        System.out.println("listings::::"+detailsDoc);
        try {
            Elements jobSummary = detailsDoc.select("div[id=jobwrapper]");
            if (jobSummary.size() > 0) {
                CrawledUrlPattern1(objCrawledJobs, url, proxyList);
            } else {
                CrawledUrlPattern2(objCrawledJobs, url, proxyList);

            }
        } catch (Exception e) {
            CrawledUrlPattern3(objCrawledJobs, url, proxyList);
            System.out.println("=====+++++++++++++=====");
           
        }

    }

    public void CrawledUrlPattern1(CrawledJobs objCrawledJobs, String url, List<ProxyImport> proxyList) throws IOException, InterruptedException, URISyntaxException {
//        while (!MainPage.running) {
//        }
        String responseDetails = "";
        try {
            responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
        } catch (Exception ex) {
            Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Document detailsDoc = Jsoup.parse(responseDetails);
        Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));

//        objJobDetails = new JobDetails();
//        objCompanyDetails = new CompanyDetails();
        System.out.println("-----------Pattern 1--------------");

        Elements jobSummary = detailsDoc.select("div[id=jobwrapper]");
        //        System.out.println("jobSummary:::"+jobSummary);

        Element companyName = jobSummary.select("div[id=jobsummary] dl dd[itemprop=hiringOrganization]").first();
        String CompanyName = companyName.text();
        System.out.println("companyName : " + CompanyName);

        Element companyLocation = jobSummary.select("div[id=jobsummary] dl dd span[itemprop=jobLocation]").first();
        String CompanyLocation = companyLocation.text();
        System.out.println("companyLocation : " + CompanyLocation);

        Element industries = jobSummary.select("div[id=jobsummary] dl dd span[itemprop=industry]").first();
        String Industries = industries.text();
        System.out.println("industries : " + Industries);

        Element jobType = jobSummary.select("div[id=jobsummary] dl dd span[itemprop=employmentType]").first();
        Element jobType1 = jobSummary.select("div[id=jobsummary] dl dd[class=multipleddlast] span").first();
        String JobType = jobType.text() + jobType1.text();
        System.out.println("job Type : " + JobType);
        System.out.println("job Type 1 : " + jobType1.text());

        Element salary = jobSummary.select("div[id=jobsummary] dl dd span[itemprop=baseSalary]").first();
        String Salary = salary.text();
        System.out.println("salary : " + Salary);

        Element refrencecode = jobSummary.select("div[id=jobsummary] dl dd span").get(6);
        String Refrencecode = refrencecode.text();
        System.out.println("refrence Code : " + Refrencecode);

        Element information = jobSummary.select("div[id=jobsummary] dl dd span").get(7);
        String Information = information.text();
        System.out.println("Contact Information : " + Information);

        Element title = jobSummary.select("div[id=jobcopy] h1[itemprop=title]").first();
        String Title = title.text();
        System.out.println("title : " + Title);

        Element content = jobSummary.select("div[id=jobBodyContent] span").first();
        String Content = content.text();
        System.out.println("About the job : " + Content);

        Element phoneno = jobSummary.select("div[id=jobsummary] dl dd[class=multipleddlast] span").last();
        String Phoneno = phoneno.text();
        System.out.println("Phone No : " + Phoneno);

        loggertextArea.append("\nCompany Name        : " + CompanyName);
        loggertextArea.append("\nCompany Location    : " + CompanyLocation);
        loggertextArea.append("\nIndustries          : " + Industries);
        loggertextArea.append("\nJob Type            : " + JobType);
        loggertextArea.append("\nSalary              : " + Salary);
        loggertextArea.append("\nRefrence Code       : " + Refrencecode);
        loggertextArea.append("\nContact Information : " + Information);
        loggertextArea.append("\n Title              : " + Title);
        loggertextArea.append("\nContent             : " + Content);
        loggertextArea.append("\nPhone no            : " + Phoneno);

        objJobDetails.setJobId(objCrawledJobs);
        objJobDetails.setCompany(CompanyName);
        objJobDetails.setJobLocation(CompanyLocation);
        objJobDetails.setIndustries(Industries);
        objJobDetails.setJobType(JobType);
        objJobDetails.setSalary(Salary);
        objJobDetails.setJobReferenceCode(Refrencecode);
        objJobDetails.setContactInformation(Information);
        objJobDetails.setJobTitle(Title);
        objJobDetails.setAboutJob(Content);
        objJobDetails.setContactNumber(Phoneno);

        objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

        loggertextArea.append("\n\n--------------------------------------");

    }

    public void CrawledUrlPattern2(CrawledJobs objCrawledJobs, String url, List<ProxyImport> proxyList) throws IOException, InterruptedException, URISyntaxException {
//        while (!MainPage.running) {
//        }
        String responseDetails = "";
        try {
            responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
        } catch (Exception ex) {
            Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Document detailsDoc = Jsoup.parse(responseDetails);
        Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
        System.out.println("-----------Pattern 2--------------");

        Elements jobSummary = detailsDoc.select("div[id=CJT-body]");

//        System.out.println("-----" + jobSummary);
        try {
            Element title = jobSummary.select("div[id=CJT-fields] h1[itemprop=title]").first();
            Element salary = jobSummary.select("div[id=CJT-fields] div[id=CJT-salary]").first();
            Element location = jobSummary.select("div[id=CJT-fields] div[id=CJT-Location]").first();
            Elements aboutus = jobSummary.select("div[id=CJT-bodybg] div[id=CJT-jobBodyContent] span p");//.first();

            String Title = title.text().replace("Role:", "");
            String Salary = salary.text().replace("Salary:", "");
            String Location = location.text().replace("Location:", "");
            String Aboutus = aboutus.text();

            loggertextArea.append("\nTitle    : " + Title);
            loggertextArea.append("\nSalary   : " + Salary);
            loggertextArea.append("\nLocation : " + Location);
            loggertextArea.append("\nAboutUs  : " + Aboutus);

            objJobDetails.setJobId(objCrawledJobs);
            objJobDetails.setJobTitle(Title);
            objJobDetails.setSalary(Salary);
            objJobDetails.setJobLocation(Location);
            objJobDetails.setAboutJob(Aboutus);

            objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

            loggertextArea.append("\n\n--------------------------------------");

        } catch (Exception e) {

            Element title = jobSummary.select("div[id=CJT-jobtitle] h1[itemprop=title]").first();
            Element companyName = jobSummary.select("div[id=CJT-jobinfo] ul li span[itemprop=name]").first();
            Element companyLocation = jobSummary.select("div[id=CJT-jobinfo] ul li span[itemprop=jobLocation]").first();
            Element companySalary = jobSummary.select("div[id=CJT-jobinfo] ul li span[itemprop=baseSalary]").first();
            Element companyJobType = jobSummary.select("div[id=CJT-jobinfo] ul li span[itemprop=employmentType]").first();
            Element industries = jobSummary.select("div[id=CJT-jobinfo] ul li span[itemprop=industry]").first();
            Element refrencecode = jobSummary.select("div[id=CJT-jobinfo] ul li span").get(15);
            Element contact = jobSummary.select("div[id=CJT-jobinfo] ul li span").get(16);

            String Title = title.text();
            String CompanyName = companyName.text();
            String CompanyLocation = companyLocation.text();
            String CompanySalary = companySalary.text();
            String CompanyJobType = companyJobType.text();
            String Industries = industries.text();
            String RefrenceCode = refrencecode.text();
            String Contact = contact.text();

            loggertextArea.append("\nTitle            : " + Title);
            loggertextArea.append("\nComapny Name     : " + CompanyName);
            loggertextArea.append("\nComapny Location : " + CompanyLocation);
            loggertextArea.append("\nComapny Salary   : " + CompanySalary);
            loggertextArea.append("\nJob Type         : " + CompanyJobType);
            loggertextArea.append("\nIndustries       : " + Industries);
            loggertextArea.append("\nReference Code   : " + RefrenceCode);
            loggertextArea.append("\nContact          : " + Contact);

            objJobDetails.setJobId(objCrawledJobs);
            objJobDetails.setJobTitle(Title);
            objJobDetails.setCompany(CompanyName);
            objJobDetails.setJobLocation(CompanyLocation);
            objJobDetails.setSalary(CompanySalary);
            objJobDetails.setJobType(CompanyJobType);
            objJobDetails.setIndustries(Industries);
            objJobDetails.setJobReferenceCode(RefrenceCode);
            objJobDetails.setContactInformation(Contact);

            objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

            loggertextArea.append("\n\n--------------------------------------");

        }
    }

    public void CrawledUrlPattern3(CrawledJobs objCrawledJobs, String url, List<ProxyImport> proxyList) throws IOException, InterruptedException, URISyntaxException {
//        while (!MainPage.running) {
//        }
        try {

            String responseDetails = "";
            try {
                responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
            } catch (Exception ex) {
                Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
//            Document detailsDoc = Jsoup.parse(responseDetails);
            Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
            System.out.println("-----------Pattern 3--------------");

            Element title = detailsDoc.select("article[id=jobview] h3").first();
            Element companyLocation = detailsDoc.select("article[id=jobview] small[class=location]").first();
            Element aboutus = detailsDoc.select("div[class=jobview-section] div").first();

            String Title = title.text();
            String CompanyLocation = companyLocation.text();
            String AboutUs = aboutus.text();

            loggertextArea.append("\nTitle            : " + Title);
            loggertextArea.append("\nCompany Location : " + CompanyLocation);
            loggertextArea.append("\nAbout Us         : " + AboutUs);

            objJobDetails.setJobId(objCrawledJobs);
            objJobDetails.setJobTitle(Title);
            objJobDetails.setJobLocation(CompanyLocation);
            objJobDetails.setAboutJob(AboutUs);

            objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

            loggertextArea.append("\n\n--------------------------------------");

        } catch (Exception e) {

            try {

                String responseDetails = "";
                try {
                    responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
                } catch (Exception ex) {
                    Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
                }
//                Document detailsDoc = Jsoup.parse(responseDetails);
                Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
                System.out.println("-----------Pattern 4--------------");

                Element title = detailsDoc.select("h1[itemprop=title]").first();
                Element companyName = detailsDoc.select("div[id=CJT-jobsummary] ul li span[itemprop=name]").first();
                Element companyLocation = detailsDoc.select("div[id=CJT-jobsummary] ul li span[itemprop=jobLocation]").first();
                Element companySalary = detailsDoc.select("div[id=CJT-jobsummary] ul li span[itemprop=baseSalary]").first();
                Element companyJobType = detailsDoc.select("div[id=CJT-jobsummary] ul li span[itemprop=employmentType]").first();
                Element industries = detailsDoc.select("div[id=CJT-jobsummary] ul li span[itemprop=industry]").first();
                Element address = detailsDoc.select("div[id=CJT-jobsummary] ul li span").get(12);
                Element phoneno = detailsDoc.select("div[id=CJT-jobsummary] ul li span").get(13);
                Element aboutus = detailsDoc.select("div[id=bodycol]").first();

                String Title = title.text();
                String CompanyName = companyName.text();
                String CompanyLocation = companyLocation.text();
                String CompanySalary = companySalary.text();
                String CompanyJobType = companyJobType.text();
                String Industries = industries.text();
                String Address = address.text();
                String PhoneNo = phoneno.text();
                String AboutUS = aboutus.text();

                loggertextArea.append("\nTitle              : " + Title);
                loggertextArea.append("\nCompany Name       : " + CompanyName);
                loggertextArea.append("\nCompany Location   : " + CompanyLocation);
                loggertextArea.append("\nCompany Salary     : " + CompanySalary);
                loggertextArea.append("\nCompany JobType    : " + CompanyJobType);
                loggertextArea.append("\nIndustries         : " + Industries);
                loggertextArea.append("\nAddress            : " + Address);
                loggertextArea.append("\nPhone No           : " + PhoneNo);
                loggertextArea.append("\nAbout Us           : " + AboutUS);

                objJobDetails.setJobId(objCrawledJobs);
                objJobDetails.setJobTitle(Title);
                objJobDetails.setCompany(CompanyName);
                objJobDetails.setJobLocation(CompanyLocation);
                objJobDetails.setSalary(CompanySalary);
                objJobDetails.setJobType(CompanyJobType);
                objJobDetails.setIndustries(Industries);
                objJobDetails.setContactInformation(Address);
                objJobDetails.setPhoneNo(PhoneNo);
                objJobDetails.setAboutJob(AboutUS);

                objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

                loggertextArea.append("\n\n--------------------------------------");

            } catch (Exception et) {
//                loggertextArea.append("\n-----------EX--------");
                try {
                    String responseDetails = "";
                    try {
                        responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
                    } catch (Exception ex) {
                        Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
                    }

//                    Document detailsDoc = Jsoup.parse(responseDetails);
                    Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
                    System.out.println("-----------Pattern 5--------------");

                    Element title = detailsDoc.select("span[itemprop=title]").first();
                    Element companyLocation = detailsDoc.select("span[itemprop=name]").first();
                    Element companyJobType = detailsDoc.select("span[itemprop=employmentType]").first();
                    Element companySalary = detailsDoc.select("div[itemprop=baseSalary]").first();
                    Element companyrefrencecode = detailsDoc.select("div[id=CJT-left] ul li span").get(5);

                    String Title = title.text();
                    String CompanyLocation = companyLocation.text();
                    String CompanyJobType = companyJobType.text();
                    String CompanySalary = companySalary.text();
                    String CompanyRefrenceCode = companyrefrencecode.text();

                    loggertextArea.append("\nTitle             : " + Title);
                    loggertextArea.append("\nCompany Location  : " + CompanyLocation);
                    loggertextArea.append("\nCompany Job Type  : " + CompanyJobType);
                    loggertextArea.append("\nCompany Salary    : " + CompanySalary);
                    loggertextArea.append("\nRefernce Code     : " + CompanyRefrenceCode);

                    objJobDetails.setJobId(objCrawledJobs);
                    objJobDetails.setJobTitle(Title);
                    objJobDetails.setJobLocation(CompanyLocation);
                    objJobDetails.setJobType(CompanyJobType);
                    objJobDetails.setSalary(CompanySalary);
                    objJobDetails.setJobReferenceCode(CompanyRefrenceCode);

                    objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

                    loggertextArea.append("\n\n--------------------------------------");

                } catch (Exception e5) {
//                    while (!MainPage.running) {
//                    }
                    try {
                        String responseDetails = "";
                        try {
                            responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
                        } catch (Exception ex) {
                            Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        Document detailsDoc = Jsoup.parse(responseDetails);
                        Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
                        System.out.println("-----------Pattern 6--------------");

                        Element title = detailsDoc.select("div[class=CJT_jobtitle]").first();
                        Element companyName = detailsDoc.select("div[id=CJT_positioninfo] ul li span[itemprop=name]").first();
                        Element companyLocation = detailsDoc.select("ul li span[itemprop=jobLocation]").first();
                        Element companySalary = detailsDoc.select("ul li span[itemprop=baseSalary]").first();
                        Element companyJobType = detailsDoc.select("ul li span[itemprop=employmentType]").first();
                        Element companyrefrencecode = detailsDoc.select("ul li span").get(8);
                        Element companyContact = detailsDoc.select("ul li span").get(9);
                        Element Aboutus = detailsDoc.select("div[id=CJT_jobBodyContent] span").first();
                        Element email = detailsDoc.select("div[id=CJT_footer] area[mns_rt=Company]").first();
                        Element webEmail = detailsDoc.select("div[id=CJT_footer] area[mns_rt=EmailContact]").first();

                        String Title = title.text();
                        String CompanyName = companyName.text();
                        String CompanyLocation = companyLocation.text();
                        String CompanySalary = companySalary.text();
                        String CompanyJobType = companyJobType.text();
                        String CompanyRefrenceCode = companyrefrencecode.text();
                        String CompanyContact = companyContact.text();
                        String AboutUS = Aboutus.text();
                        String EMail = email.attr("href");
                        String WebEmail = webEmail.attr("href");

                        loggertextArea.append("\nTitle            : " + Title);
                        loggertextArea.append("\nCompany Name     : " + CompanyName);
                        loggertextArea.append("\nCompany Location : " + CompanyLocation);
                        loggertextArea.append("\nCompany Salary   : " + CompanySalary);
                        loggertextArea.append("\nCompany JobType  : " + CompanyJobType);
                        loggertextArea.append("\nRefernce Code    : " + CompanyRefrenceCode);
                        loggertextArea.append("\nCompany Contact  : " + CompanyContact);
                        loggertextArea.append("\nAbout Us         : " + AboutUS);
                        loggertextArea.append("\nEmail Address    : " + WebEmail);
                        loggertextArea.append("\nWeb Email        : " + EMail);

                        objJobDetails.setJobId(objCrawledJobs);
                        objJobDetails.setJobTitle(Title);
                        objJobDetails.setCompany(CompanyName);
                        objJobDetails.setJobLocation(CompanyLocation);
                        objJobDetails.setSalary(CompanySalary);
                        objJobDetails.setJobType(CompanyJobType);
                        objJobDetails.setJobReferenceCode(CompanyRefrenceCode);
                        objJobDetails.setContactInformation(CompanyContact);
                        objJobDetails.setAboutJob(AboutUS);
                        objJobDetails.setEmailAddress(EMail);
                        objJobDetails.setWebAddress(WebEmail);

                        objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

                        loggertextArea.append("\n\n--------------------------------------");

                    } catch (Exception eee) {
//                        while (!MainPage.running) {
//                        }
                        try {
                            String responseDetails = "";
                            try {
                                responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
                            } catch (Exception ex) {
                                Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
                            }
//                            Document detailsDoc = Jsoup.parse(responseDetails);
                            Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
                            System.out.println("-----------Pattern 7--------------");

                            Element title = detailsDoc.select("div[id=CJT_jobtitle] h2[itemprop=title]").first();
                            Element companyName = detailsDoc.select("ul[id=CJT_info] h2").first();
                            Element companyLocation = detailsDoc.select("span[id=TrackingJobBody] p").get(2);
                            Element companySalary = detailsDoc.select("span[id=TrackingJobBody] p").get(3);
                            Element webAddress = detailsDoc.select("ul[id=CJT_info] li a[itemprop=url]").first();
                            Element emailAddress = detailsDoc.select("ul[id=CJT_info] li a[mns_rt=EmailContact]").first();
                            Element companyContact = detailsDoc.select("ul[id=CJT_info] li").get(4);
                            Element aboutUs = detailsDoc.select("div[id=CJT_jobBodyContent] div span[id=TrackingJobBody]").first();

                            String Title = title.text();
                            String CompanyName = companyName.text();
                            String CompanyLoctaion = companyLocation.text().replace("Location:", "");
                            String CompanySalary = companySalary.text().replace("Salary:", "");
                            String WebAddress = webAddress.attr("href");
                            String Email = emailAddress.text();
                            String CompanyContact = companyContact.text();
                            String AboutUS = aboutUs.text();

                            loggertextArea.append("\nTitle            : " + Title);
                            loggertextArea.append("\nCompnay Name     : " + CompanyName);
                            loggertextArea.append("\nComapny Location : " + CompanyLoctaion);
                            loggertextArea.append("\nComapny Salary   : " + CompanySalary);
                            loggertextArea.append("\nWeb Address      : " + WebAddress);
                            loggertextArea.append("\nEmail Address    : " + Email);
                            loggertextArea.append("\nCompany Contact  : " + CompanyContact);
                            loggertextArea.append("\nAbout Us         : " + AboutUS);

                            objJobDetails.setJobId(objCrawledJobs);
                            objJobDetails.setJobTitle(Title);
                            objJobDetails.setCompany(CompanyName);
                            objJobDetails.setJobLocation(CompanyLoctaion);
                            objJobDetails.setSalary(CompanySalary);
                            objJobDetails.setWebAddress(WebAddress);
                            objJobDetails.setEmailAddress(Email);
                            objJobDetails.setContactInformation(CompanyContact);
                            objJobDetails.setAboutJob(AboutUS);

                            objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);

                            loggertextArea.append("\n\n--------------------------------------");
                            
                           

                        } catch (Exception efg) {
                        }
                    }
                }

            }
        }
    }

    @Override
    public String call() throws Exception {
        while (!MainPage.running) {
        }
        try {
            checkPattern(objCrawledJobs, objCrawledJobs.getJobUrl(), proxyList);
        } catch (Exception ex) {

        }
        return "Demo";

    }

}
