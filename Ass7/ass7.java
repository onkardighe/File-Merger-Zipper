// Accept filename & create one String if 100 Bytes which contains name of that file & size of that file


import java.util.*;
import java.io.*;

class ass7
{
    public static void main(String[] args) 
    {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Enter Filename :");
        String filePath = "./files/"+sobj.nextLine();
        
        File file = new File(filePath);
        
        String outputString = file.getName()+" "+file.length();
        int emptyStringSize = 100-outputString.length();
        System.out.println("Beafore :"+outputString.length());
        
        for(int i = 1; i <= emptyStringSize; i++)
        {
            outputString = outputString+" ";
        }
        System.out.println("After :"+outputString.length());
 
    }
}