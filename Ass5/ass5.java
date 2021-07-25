// Accept filename from user & create that file

import java.util.*;
import java.io.*;

class ass5
{
    public static void main(String[] args) throws Exception 
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Filename :");
        String filePath = "./files/"+sobj.nextLine();

        File fobj = new File(filePath);

        // .createNewFile() Returns true if Successful & returns False if File is already Present
        boolean check = fobj.createNewFile();
        if(check == true)
        {
            System.out.println("File Created : "+fobj.getName());
        }
        else
        {
            System.out.println("File Already Exists !");
        }   
    }
}

