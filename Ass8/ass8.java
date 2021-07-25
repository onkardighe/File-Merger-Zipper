// Accept folder name from user, Create one file as output.txt.
// write all names  of that file from that folder into output.txt

import java.util.*;
import java.io.*;

class ass8
{
    public static void main(String[] args) throws Exception
    {   
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Folder Name :");
        String folderPath = "./"+sobj.nextLine();

        File folder = new File(folderPath);
        File newFile = new File("output.txt");

        if(newFile.createNewFile())
        {
            System.out.println("File Created Successfully !");
        }
        else
        {
            System.out.println("File Already present !");
        }

        BufferedWriter bufferwrite = new BufferedWriter(new FileWriter(newFile));

        File foldercontents[] = folder.listFiles();

        String fileName;
        for(int i = 0; i < foldercontents.length; i++)
        {
            if((fileName = foldercontents[i].getName()).endsWith(".txt"))
            {
                bufferwrite.write(foldercontents[i].getName());
            }
        }
        bufferwrite.close();
    }
}

