// // 6. Accept filename from user (Existing) & 
// // Accept one another filname 
// // Create new file with second name & 
// // copy contents of first file into Second file


import java.util.*;

import javax.lang.model.util.ElementScanner14;

import java.io.*;

class ass6
{
    public static void main(String[] args) throws Exception
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Source File Name :");
        String pathFirstFile = "./files/"+sobj.nextLine();
        System.out.println("Enter Destination File Name :");
        String pathSecondLine = "./files/"+sobj.nextLine();

        File firstFile = new File(pathFirstFile);
        File secondFile = new File(pathSecondLine);

        // Filter to check if File is exist or NOT
        if(secondFile.exists())
        {
            System.out.println("File already Exist");
        }
        else
        {
            System.out.println("File NOT Exist, Creating New One !");
            secondFile.createNewFile();
        }

        BufferedReader bufferReadFirst = new BufferedReader(new FileReader(firstFile));
        BufferedReader bufferReadSecond = new BufferedReader(new FileReader(secondFile));
        
        BufferedWriter bufferWriteSecond = new BufferedWriter(new FileWriter(secondFile,true));         // Append is TRUE

        // Writing data to second File
        String data;
        while((data = bufferReadFirst.readLine()) != null)
        {
            bufferWriteSecond.append(data);
        }

        
        bufferReadFirst.close();
        bufferWriteSecond.close();
    }
}

