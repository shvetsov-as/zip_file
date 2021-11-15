/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vvodv;

import java.io.File;
import static vvodv.Vvod1.showFoldersRecursively;

/**
 *
 * @author User
 */
public class Vvodv {
    public static void main(String[] args) {
        //System.out.println(Vvod1.readLineFromConsole());
        //Vvod1.zipFile(file);
//        ArrayList<String> as = Vvod1.readFromURL("address url");
//        for (String s : as) {
//            System.out.println(s);
//        }

        //System.out.println(Vvod1.readFromKeyboard1());
        //Vvod1.showFolderContent2("example");

File[] rootList = new File ("example").listFiles();
Vvod1.showFoldersRecursively(rootList);


    }
    
    
    
}
