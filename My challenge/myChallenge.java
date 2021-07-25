// Accept a folder name & Create a new file as combine.txt 
// Create a Header String for Each file 
// Header Contains filename filesize Ex. [demo.txt 42] (file size in bytes)
// write Header & contents of the file on after another in new file that we have created

import java.util.*;
import java.io.*;

class myChallenge
{
    public static void main(String[] args) throws Exception
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Folder name :");
        String folderPath = "./"+sobj.nextLine();

        
        File folder = new File(folderPath);
        File outputFile = new File("output.txt");


        // Buffer that writes data in output.txt file
        BufferedWriter bufferWrite = new BufferedWriter(new FileWriter(outputFile));

        // Array of obejects of all Files in folder 
        File contentsFolder[] = folder.listFiles();

        String data;
        String header;
        for(int i = 0; i<contentsFolder.length; i++)
        {
            // Filter to check files of extension .txt
            if(!(contentsFolder[i].getName().endsWith(".txt")))
            {
                continue;
            }

            // Creating Header 
            header = contentsFolder[i].getName()+" "+contentsFolder[i].length();
            int temp = 100-header.length();
            for(int j = 1; j <= temp; j++)
            {
                // Adding blank Spaces in Header to make Header size 100 
                header = header +" ";
            }

            //Writing Header in File
            bufferWrite.write(header);

            // Buffer that read contents of file object
            BufferedReader bufferRead = new BufferedReader(new FileReader(contentsFolder[i]));
            // Read each file by line until file ends
            while((data = bufferRead.readLine()) != null)
            {
                bufferWrite.write(data);
            }
        }
        bufferWrite.close();
    }
}

