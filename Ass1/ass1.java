// Accept Filename From user & Display Size of file on the screen


import java.io.*;
import java.util.Scanner;


class ass1
{
    public static void main(String[] args) 
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Filename :");
        String fileName = sobj.nextLine();

        File fobj = new File("./files/"+fileName);
        System.out.println(fobj.getName()+" "+fobj.length());

    }
}