/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.proxy;

import java.net.InetAddress;
import java.util.concurrent.Callable;
import static com.monsterscrapper.ui.MainPage.loggertextArea;
import static com.monsterscrapper.ui.MainPage.proxyList;
import static com.monsterscrapper.ui.MainPage.searchButton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mendon Ashwini CheckProxy is a thread which checks if the proxies in
 * the file are valid r not
 */
public class CheckProxy implements Callable<String> {

    @Override
    public String call() throws Exception {

        for (int i = proxyList.size() - 1; i >= 0; i--) {
            System.out.println(proxyList.get(i).proxyPort + "::check:::" + proxyList.get(i).proxyIP);
            int test = 0;

            try {
                InetAddress inet = InetAddress.getByName(proxyList.get(i).proxyIP);
                boolean check=inet.isReachable(10000);
                if (check) {
                    System.out.println(proxyList.get(i).proxyIP + " is reachable");
                    loggertextArea.append("\n\n" + proxyList.get(i).proxyIP + " is reachable");
                } else {
                    test = 1;
                    System.out.println(proxyList.get(i).proxyIP + " is not reachable");
                    loggertextArea.append("\n\n" + proxyList.get(i).proxyIP + " is not reachable");
                }
            } catch (Exception iOException) {
                loggertextArea.append("\n\n" + proxyList.get(i).proxyIP + " is not valid IP");
            }

//            try {
//                InetAddress inet = InetAddress.getByName(proxyList.get(i).proxyIP);
//                inet.isReachable(1000);
//                System.out.println(proxyList.get(i).proxyIP + " is reachable");
//                loggertextArea.append("\n\n" + proxyList.get(i).proxyIP + " is reachable");
//
//            } catch (Exception iOException) {
//                test = 1;
//                System.out.println(proxyList.get(i).proxyIP + " is not reachable");
//                loggertextArea.append("\n\n" + proxyList.get(i).proxyIP + " is not reachable");
//            }
//            if (test == 0) {
//                try {
//                    int port = Integer.parseInt(proxyList.get(i).proxyPort);
//                    if ((port > 65535) || (port < 0)) {
//                        test = 1;
//                        loggertextArea.append("\n\n" + proxyList.get(i).proxyPort + " is not valid");
//
//                    }
//                } catch (Exception e) {
//                    test = 1;
//                }
//            }
            if (test == 1) {
                try {
                    loggertextArea.append("\n\n" + proxyList.get(i).proxyIP + ":" + proxyList.get(i).proxyPort + " is removed from list");
                    proxyList.remove(proxyList.get(i));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        if (proxyList.size() > 0) {
            loggertextArea.append("\n\nDone Loading the Proxy!!");
            loggertextArea.append("\nData will be refreshed with Proxy!!");
        } else {
            loggertextArea.append("\n\nThere is not Data in the proxy list!!");

        }

        //code for writing the file in txt
        File desktop = new File(System.getProperty("user.home"), "Desktop");
         File file2 = new File(desktop + "\\monster");
        if (!file2.exists()) {
            file2.mkdir();
        }
        
        File file = new File(desktop +"\\monster"+ "\\Monster_scraper.txt");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        
    
        
        
        

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        String content = "";

        for (int i = 0; i < proxyList.size(); i++) {
            ProxyImport obj = proxyList.get(i);
            if (obj.proxyLen == 4) {
                content = obj.proxyIP + ":" + obj.proxyPort + ":" + obj.proxyUserName + ":" + obj.proxyPassword + "\n";
            }
            if (obj.proxyLen == 2) {
                content = obj.proxyIP + ":" + obj.proxyPort + "\n";

            }
            bw.write(content);
            bw.newLine();

        }

        bw.close();

        System.out.println("Done");

        searchButton.setEnabled(true);

        return "done";
    }

}
