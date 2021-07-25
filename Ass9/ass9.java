// Accept foldername from user & create one new file as combine.txt 
// write data of all files from folder into that combine.txt file

import java.util.*;
import java.io.*;

class ass9
{
    public static void main(String[] args) throws Exception
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Folder name :");
        String folderPath = sobj.nextLine();

        File folder = new File(folderPath);
        File outputFile = new File("combine.txt");

        // Creating New File
        if(outputFile.createNewFile())
        {
            System.out.println("File Created Successfully !");
        }
        else
        {
            System.out.println("File Already Present !");
        }

        // Writing Buffer
        BufferedWriter bufferwrite = new BufferedWriter(new FileWriter(outputFile,true));

        // Array of obejects of all Files in folder 
        File folderContents[] = folder.listFiles();

        String data;
        for(int i = 0; i < (folderContents.length); i++)
        {
            if(!(folderContents[i].getName().endsWith(".txt")))
            {
                continue;
            }
            BufferedReader bufferRead = new BufferedReader(new FileReader(folderContents[i]));
            while((data = bufferRead.readLine()) != null)
            {
                bufferwrite.write(data);
                bufferwrite.flush();                // flushes the buffer while buffer is being half filled
            }
        }   
    }
}