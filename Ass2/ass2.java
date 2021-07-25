// Accept filename from user & Display contents of that file on the screen

import java.util.*;
import java.io.*;

class ass2
{
    public static void main(String args[]) throws Exception
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Filename :");
        String filePathName = "./files/"+sobj.nextLine();

        BufferedReader readBuffer = new BufferedReader(new FileReader(filePathName));
        String fileLine;
        while((fileLine = readBuffer.readLine()) != null)
        {
            System.out.println(fileLine);
        }
    }
}
