// 4. Accept Directory name (Folder Name) from user & Display all file names with its Size
import java.util.*;
import java.io.*;

class ass4
{
    public static void main(String[] args) 
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Folder name :");
        String pathFolder = sobj.nextLine();

        File fobj = new File(pathFolder);

         // .listFiles() returns array of File object
        File arrfiles[] = fobj.listFiles();

        for(File file : arrfiles)
        {
            // Filter that shows only that files whose name ends up with .txt
            if(file.getName().endsWith(".txt"))
            {
                System.out.println(file.getName()+" "+file.length());
            }
            
        }
    }
}