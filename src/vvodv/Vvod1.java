/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvodv;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author User
 */
public class Vvod1 {

    public static String readLineFromConsole() {
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();
        int k = sc.nextInt();
        System.out.println("k = " + k);
        return result;
    }

    static void zipFile(String file) throws FileNotFoundException, IOException {
        int pointIndex = file.lastIndexOf('.'); // polychaem index posedney tochki
        System.out.println("pointIndex=" + pointIndex);
        String arName = file.substring(0, pointIndex) + ".zip";// vbIrez chastb do tochki i prisoed ZIP
        System.out.println("arName=" + arName);
        //File arFile = new File (arName); proverka syshestvovanie i izmenenie imeni dobavleniem chisla

        try ( ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(arName));  FileInputStream fis = new FileInputStream(file);) {// sozd vh i vbIh potok vnytri try
            ZipEntry ze = new ZipEntry(file);// sozdaem element arhiva s imenem faila
            zos.putNextEntry(ze);// dobav ego v arhiv

            byte readBuffer[] = new byte[2048];
            int bytesRead = 0;
            while ((bytesRead = fis.read(readBuffer)) != -1) {// -1 esli nechego chitat
                zos.write(readBuffer, 0, bytesRead);// pishem bytesRead iz bufera v potok
            }
        } catch (FileNotFoundException ex) {
            System.out.println("No file");
        } catch (IOException ex) {
            System.out.println("No file");
        }
    }

//    public static ArrayList<String> readFromURL(String url) {
//        ArraytList<String> arrayList = new ArrayList<>();
//        URL url = null;
//        try {
//            url = new URL(urlString);
//        } catch (MalformedURLException ex) {
//            System.out.println("eto ne url " + urlString);
//        }
//        URLConnection conn = null;
//        try {
//            conn = url.openConnection();
//
//        } catch (IOException ex) {
//            System.out.println("Oshibka soedineniya");
//        }
//
//        try ( InputStream input = conn.getInputStream();  BufferReader bf = new BufferReader(new InputStreamReader(input));) {
//            BufferReader bf = new BufferReader (new InputStreamReader(input));){
//            while (bf.ready()){
//                arrayList.add(bf.readLine());
//            }
//        }
//        }catch (IOException ex){
//            Logger.getLogger(Vvod1.class.getName().log(Level.SEVERE, null, ex))
//        }
//        return arrayList;
//
//    }
    public static String readFromKeyboard() {
        int i;

        try ( StringWriter sw = new StringWriter();) {
            while (true) {
                i = System.in.read();//chitaem 1 byte
                if (i == '\n') {
                    return sw.toString();
                }
                sw.append((char) i);
            }

        } catch (IOException ex) {
            return "Oshibka";
        }
    }
    
    public static String readFromKeyboard1() {
        int i;

        try ( ByteArrayOutputStream bos = new ByteArrayOutputStream();) {
            while (true) {
                i = System.in.read();//chitaem 1 byte
                if (i == '\n') {
                    return new String(bos.toByteArray(), "UTF8");
                }
                bos.write(i);
            }

        } catch (IOException ex) {
            return "Oshibka";
        }
    }
    
    
    public static void showFolderContent(String folderName){
        File folder = new File(folderName);
        
        if(!folder.isDirectory()){
            System.out.println("ne papka" + folderName);
            
        }
        File listOfFiles[] = folder.listFiles();
        for (File f : listOfFiles){
            System.out.println("->" + f.getName() + "<--" + (f.isFile()?"file" : "papka"));
        }
    }
    
    public static void showFolderContent1(String folderName){
        File folder = new File(folderName);
        
        if(!folder.isDirectory()){
            System.out.println("ne papka" + folderName);
            
        }
        File listOfFiles[] = folder.listFiles();
        
        Arrays.sort(listOfFiles, new Comparator<File>(){
        
        @Override
         public int compare(File o1, File o2){
            return o1.getName().compareTo(o2.getName());
        }
    });
    
        
        for (File f : listOfFiles){
            System.out.println("->" + f.getName() + "<--" + (f.isFile()?"file" : "papka"));
        }
    }

    public static void showFolderContent2(String folderName){
        File folder = new File(folderName);
        
        if(!folder.isDirectory()){
            System.out.println("ne papka" + folderName);
            
        }
        File listOfFiles[] = folder.listFiles();
        
        Arrays.sort(listOfFiles, new Comparator<File>(){
        
        @Override
         public int compare(File o1, File o2){
             int d = (o1.isFile()? 0:1) - (o2.isFile()? 0:1);
             return (d != 0) ? d :  o1.getName().compareTo(o2.getName());
            
        
         }
    });
    
        
        for (File f : listOfFiles){
            System.out.println("->" + f.getName() + "<--" + (f.isFile()?"file" : "papka"));
        }
    }
    
    
    public static void showFoldersRecursively(File[] arr){
        Arrays.sort(arr, new Comparator<File>(){
        
        @Override
         public int compare(File o1, File o2){
             int d = (o1.isFile()? 0:1) - (o2.isFile()? 0:1);
             return (d != 0) ? d :  o1.getName().compareTo(o2.getName());
            
        
         }
    });
        String otstup = "   ";
        //int otstup1 = 10;
        for (File f : arr) {
            //for (int i =0; i < otstup1; i++)
            if (f.isDirectory()){
                System.out.println("papka " + f.getName());
                showFoldersRecursively(f.listFiles());
                
            } else{
                System.out.println("file:" + "    "+f.getName()); 
            }
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
