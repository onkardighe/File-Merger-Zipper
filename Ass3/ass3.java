// 3. Accept Directory name (Folder Name) from user & Display all file names from that folder on screen
import java.util.*;
import java.io.*;

class ass3
{
    public static void main(String[] args) 
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Ente Folder Name :");
        String pathFolder = "./"+sobj.nextLine();           
        
        File fobj = new File(pathFolder);  
        File arrFiles[] = fobj.listFiles();                     // .listFiles() returns array of File object
        for(File file : arrFiles)
        {
            if(file.getName().endsWith(".txt"))                 // Filter that shows only files that ends up with .txt
            {
                System.out.println(file.getName());
            }
        }
    }
}