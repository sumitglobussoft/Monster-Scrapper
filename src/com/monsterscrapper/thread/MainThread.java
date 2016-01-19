/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.thread;

import com.monsterscrapper.crawler.MonsterScrapper;
import com.monsterscrapper.csv.GenerateCsvFile;
import com.monsterscrapper.dao.MonsterScrapperDao;
import static com.monsterscrapper.entity.CrawledJobs_.keyword;
import com.monsterscrapper.proxy.ProxyImport;

import com.monsterscrapper.ui.MainPage;
import static com.monsterscrapper.ui.MainPage.CSVButton;
import java.util.List;
import java.util.concurrent.Callable;

import static com.monsterscrapper.ui.MainPage.keywordList;
import static com.monsterscrapper.ui.MainPage.countryList;
import static com.monsterscrapper.ui.MainPage.loggertextArea;

import java.util.ArrayList;

/**
 *
 * @author GLB-214
 */
public class MainThread implements Callable<String> {

    MonsterScrapperDao objMonsterScrapperDao = null;
    List<ProxyImport> proxyList = null;
    List<String> countryCode = new ArrayList<>();

    public MainThread(MonsterScrapperDao objMonsterScrapperDao, List<ProxyImport> proxyList) {
        this.objMonsterScrapperDao = objMonsterScrapperDao;
        this.proxyList = proxyList;
    }

    @Override
    public String call() throws Exception {

        List countryobjectlist = countryList.getSelectedValuesList();
        for (Object countryobjectlist1 : countryobjectlist) {

            try {
                String countryName = (String) countryobjectlist1;
                String splitedVal[] = countryName.split(":");
                countryCode.add(splitedVal[1]);
            } catch (Exception e) {
            }

        }

        for (String countryCode1 : countryCode) {
            while (!MainPage.running) {
            }
            if (!MainPage.running) {
                System.out.println("inside stopppppppppppppp");
                if (MainPage.checkStop == 0) {
                    MainPage.checkStop = 1;
                }
                break;
            }
            for (String keywords : keywordList) {
                while (!MainPage.running) {
                }
                if (!MainPage.running) {
                    System.out.println("inside stopppppppppppppp");
                    if (MainPage.checkStop == 0) {
                        MainPage.checkStop = 1;
                    }
                    break;
                }

                List<String> a = countryCode;
                String aa = a.toString();
                System.out.println(" ---------------------------------- " + aa);
                MonsterScrapper objMonsterScrapper = new MonsterScrapper();
                System.out.println("--------Country Code------" + countryCode1);
                keywords = keywords.replace(" ", "+");
                System.out.println(""+keywords);
//                String url = "http://jobsearch.monster.co.uk/jobs/?q=" + keywords + "&cy=" + countryCode1;
                String url = "http://jobsearch.monster.co.uk/jobs/?q=" + keywords + "&where=" + countryCode1 + "&cy=uk";
                objMonsterScrapper.CrawledUrl(url, proxyList, keywords, countryCode1);
            }
        }

        loggertextArea.append("\n process completed");
        GenerateCsvFile objGenerateCsvFile = new GenerateCsvFile();
        objGenerateCsvFile.excel(keywordList.get(0));
        CSVButton.setEnabled(true);
        return "done";
    }

}
