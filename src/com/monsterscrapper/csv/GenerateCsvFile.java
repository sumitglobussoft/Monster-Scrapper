/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monsterscrapper.csv;

import static com.monsterscrapper.ui.MainPage.keywordList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

/**
 *
 * @author GLB-214
 */
public class GenerateCsvFile {

    public static void excel(String keyword) throws FileNotFoundException, IOException {

        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("Monster Details");
        HSSFRow rowhead = sheet.createRow((int) 0);
        rowhead.createCell((int) 0).setCellValue("S.No.");
        rowhead.createCell((int) 1).setCellValue("JOB_URL");
        rowhead.createCell((int) 2).setCellValue("KEYWORD");
        rowhead.createCell((int) 3).setCellValue("COUNTRY");
        rowhead.createCell((int) 4).setCellValue("POSTED_DATE");
        rowhead.createCell((int) 5).setCellValue("COMPANY");
        rowhead.createCell((int) 6).setCellValue("JOB_LOCATION");
        rowhead.createCell((int) 7).setCellValue("EMAIL_ADDRESS");
        rowhead.createCell((int) 8).setCellValue("WEB_ADDRESS");
        rowhead.createCell((int) 9).setCellValue("CONTACT_NUMBER");
        rowhead.createCell((int) 10).setCellValue("ABOUT_JOB");
        rowhead.createCell((int) 11).setCellValue("JOB_TYPE");
        rowhead.createCell((int) 12).setCellValue("SALARY");
        rowhead.createCell((int) 13).setCellValue("JOB_REFERENCE_CODE");
//        rowhead.createCell((int) 14).setCellValue("LOCATION");
//        rowhead.createCell((int) 15).setCellValue("INDUSTRIES");
//        rowhead.createCell((int) 16).setCellValue("CONTACT_INFORMATION");
//        rowhead.createCell((int) 17).setCellValue("PHONE_NO");

        try {
            Class.forName("org.sqlite.JDBC");
            java.sql.Connection con = DriverManager.getConnection("jdbc:sqlite:monsterdb.db", "root", "");

            String keywordsArray = keywordList.toString().replace("[", "(\"").replace("]", "\")").replace(",", "\",\"");
                      
            String sql = "SELECT * FROM crawled_jobs,job_details WHERE crawled_jobs.JOB_ID=job_details.JOB_ID and crawled_jobs.KEYWORD in " + keywordsArray + ";";
            System.out.println("sql : " + sql);

            java.sql.PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int k = 0;
            while (rs.next()) {

                HSSFRow row = sheet.createRow((int) k + 2);
                try {
                    row.createCell((int) 0).setCellValue(k + 1);
                } catch (Exception sd) {
                }
                try {
                    row.createCell((int) 1).setCellValue(rs.getString("JOB_URL") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 2).setCellValue(rs.getString("KEYWORD") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 3).setCellValue(rs.getString("COUNTRY") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 4).setCellValue(rs.getString("POSTED_DATE") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 5).setCellValue(rs.getString("COMPANY") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 6).setCellValue(rs.getString("JOB_LOCATION") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 7).setCellValue(rs.getString("WEB_ADDRESS") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 8).setCellValue(rs.getString("EMAIL_ADDRESS") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 9).setCellValue(rs.getString("CONTACT_NUMBER") + "");
                } catch (Exception sd) {
                }
                try {
                    row.createCell((int) 10).setCellValue(rs.getString("ABOUT_JOB") + "");
                } catch (Exception sd) {
                }
                try {
                    row.createCell((int) 11).setCellValue(rs.getString("JOB_TYPE") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 12).setCellValue(rs.getString("SALARY") + "");
                } catch (Exception sd) {
                }

                try {
                    row.createCell((int) 13).setCellValue(rs.getString("JOB_REFERENCE_CODE") + "");
                } catch (Exception sd) {
                }

//                try {
//                    row.createCell((int) 14).setCellValue(rs.getString("LOCATION") + "");
//                } catch (Exception sd) {
//                }
//
//                try {
//                    row.createCell((int) 15).setCellValue(rs.getString("INDUSTRIES") + "");
//                } catch (Exception sd) {
//                }
//
//                try {
//                    row.createCell((int) 16).setCellValue(rs.getString("CONTACT_INFORMATION") + "");
//                } catch (Exception sd) {
//                }
//
//                try {
//                    row.createCell((int) 17).setCellValue(rs.getString("PHONE_NO") + "");
//                } catch (Exception sd) {
//                }
                k++;
            }

        } catch (Exception aaa) {
        }

        JFileChooser fileChooser = new JFileChooser();
        File desktop = new File(System.getProperty("user.home"), "Desktop");

        System.out.println("" + desktop);
        String a = desktop.toString();
        File file = new File(a + "\\monster");
        if (!file.exists()) {
            if (file.mkdir()) {
                String filename = file + "\\" + keyword + ".csv";
                System.out.println("Directory is created!");
                FileOutputStream fileOut = new FileOutputStream(filename);
                hwb.write(fileOut);
                fileOut.close();
                System.out.println("Your excel file has been generated!");
                JOptionPane.showMessageDialog(null, "Excel File regenerated successfully\n" + filename);
            } else {
                System.out.println("Failed to create directory!");
            }
        } else {
            String filename = file + "\\" + keyword + ".csv";
            System.out.println("Directory is created!");
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
            JOptionPane.showMessageDialog(null, "Excel File regenerated successfully\n" + filename);
        }

//        int returnStatus = fileChooser.showSaveDialog(null);
//        if (returnStatus == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println(selectedFile);
//            filename = selectedFile.toString();
//        }
//        FileOutputStream fileOut = new FileOutputStream(filename);
//        hwb.write(fileOut);
//        fileOut.close();
//        System.out.println("Your excel file has been generated!");
//        JOptionPane.showMessageDialog(null, "Excel File regenerated successfully\n" + filename);
//        logTextArea.append(String.valueOf("Excel File regenerated successfully " + "\n"));
    }
}
