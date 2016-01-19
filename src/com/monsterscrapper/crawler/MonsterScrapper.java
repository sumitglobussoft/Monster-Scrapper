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
import static com.monsterscrapper.ui.MainPage.proxyList;
import com.monsterscrapper.utility.FetchPageWithProxy;
import com.monsterscrapper.utility.GetRequestHandler;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author GLB-214
 */
public class MonsterScrapper {

    CrawledJobs objCrawledJobs;
    JobDetails objJobDetails;
    CrawlEachJobs objCrawlEachJobs;

    ApplicationContext context
            = new ClassPathXmlApplicationContext("Beans.xml");
    // TODO code application logic here

    MonsterScrapperDaoImpl objMonsterScrapperDaoImpl
            = (MonsterScrapperDaoImpl) context.getBean("MonsterScrapperDaoImpl");

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
//        // TODO code application logic here
//        MonsterScrapper objMonsterScrapper = new MonsterScrapper();
////        String url = "http://jobsearch.monster.co.uk/jobs/?q=java&cy=uk";
//        String url = "http://jobsearch.monster.co.uk/jobs/?q=infosys&cy=uk";
//        objMonsterScrapper.CrawledUrl(url, proxyList, keywords, countrys);
//
//    }
    public void CrawledUrl(String url, List<ProxyImport> proxyList, String keywords, String countrys) throws IOException, InterruptedException, URISyntaxException {
//        while (!MainPage.running) {
//        }
        if (!MainPage.running) {
            System.out.println("inside stopppppppppppppp");
            if (MainPage.checkStop == 0) {
//                IndiamartGUI.logger.append("STOPPED");
                MainPage.checkStop = 1;
//                csvButton.enable();
//                deleteDB.setEnabled(true);
//                startButton.setEnabled(true);
            }
            return;
        }

//        String url = "http://jobsearch.monster.co.uk/jobs/?q=infosys&where=australia&cy=uk&intcid=HP_HeroSearch";
//        String keyword = "java";
//        String countryCode = "uk";

        String responseDetails = "";
        try {
            responseDetails = new GetRequestHandler().doGetRequest(new URL(url));
        } catch (Exception ex) {
            Logger.getLogger(MonsterScrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
//        Document detailsDoc = Jsoup.parse(responseDetails);
        Document detailsDoc = Jsoup.parse(FetchPageWithProxy.fetchPage(new URI(url), proxyList));
//        System.out.println("listings::::"+detailsDoc);
        Elements listings = detailsDoc.select("div[id=listings] table tbody tr td[scope=row]");

        for (Element listing : listings) {

            String href = listing.select("a").attr("href");
            System.out.println("href : " + href);

            String title = listing.select("a").attr("title");
            System.out.println("title :" + title);

            Element e = listing.select("div[class=jobTitleCol fnt4] div[class=jobTitleContainer] a").first();
            String splitdate = e.attr("onclick");
            String[] a = splitdate.split("AVSDM=");
            String[] b = a[1].split("&");
            String posteddate = b[0];

            System.out.println("Posted Date : " + posteddate);

            objCrawledJobs = new CrawledJobs();
            objJobDetails = new JobDetails();
//            objCrawlEachJobs.checkPattern(href);
            objCrawledJobs.setJobUrl(href);
            objCrawledJobs.setKeyword(keywords);
            objCrawledJobs.setCountry(countrys);
            objCrawledJobs.setPostedDate(posteddate);

            loggertextArea.append("\nJob Url           : " + href);
            loggertextArea.append("\nKeyword        : " + keywords);
            loggertextArea.append("\nPosted Date : " + posteddate);

            objMonsterScrapperDaoImpl.insertCrawledJobsUrlData(objCrawledJobs);

//            objMonsterScrapperDaoImpl.insertJobDetailsCrawledData(objJobDetails);
            loggertextArea.append("\n\n--------------------------------------");

        }

        List<Future<String>> list = new ArrayList<Future<String>>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {

            Element pagination = detailsDoc.select("div[class=navigationBar] a").last();
//            Element pagination = detailsDoc.select("div[class=pagingWrapper] a").last();
            System.out.println("pagination::::::::::::::::::::::::::::::\n" + pagination);

            if (pagination.attr("title").contains("Next")) {
                String nexturl1 = pagination.attr("href");
                System.out.println("-----Next Pagination Url------" + nexturl1);
                CrawledUrl(nexturl1, proxyList, keywords, countrys);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

            System.out.println("\n\n-------Pagination Exception--------");
//             loggertextArea.append("-------Pagination Exception--------");
            try {
                List<CrawledJobs> getCrawledJobsDataList = objMonsterScrapperDaoImpl.getCrawledJobsDataList();
                for (CrawledJobs crawledJobsDataList : getCrawledJobsDataList) {
//                    while (!MainPage.running) {
//                    }
                    if (!MainPage.running) {
                        if (MainPage.checkStop == 0) {
                            MainPage.checkStop = 1;
                        }
                        return;
                    }

                    Callable worker = new CrawlEachJobs(crawledJobsDataList, objMonsterScrapperDaoImpl, proxyList);
                    Future<String> future = executor.submit(worker);
                    list.add(future);
                }
            } catch (Exception erer) {
            }
            ////call next
            System.out.println("Page Not Avaliable");

            for (Future<String> fut : list) {
                try {
                    //print the return value of Future, notice the output delay in console
                    // because Future.get() waits for task to get completed
                    System.out.println(new Date() + "::" + fut.get());
                } catch (InterruptedException | ExecutionException ep) {
                    ep.printStackTrace();
                }
            }
            //shut down the executor service now
            executor.shutdown();
        }
    }

}
