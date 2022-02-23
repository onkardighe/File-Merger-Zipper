import java.util.*;
import java.io.*;


// Class for selected files
public class Selection 
{
    public static ArrayList<File> selectedFiles;
    private static String extn[] = {".txt",".java",".c",".cpp",".py"};

    public void select(File file)
    {
        if(!(selectedFiles.contains(file)))
        {
            selectedFiles.add(file);
        }
    }   

    public void remove(File file)
    {
        if(selectedFiles.contains(file))
        {
            selectedFiles.remove(file);
        }
    }

    public void proceedToPack(File dest)
    {
        new packerMain(selectedFiles, dest, extn);
    }


    Selection()
    {
        selectedFiles = new ArrayList<File>(); 
    }
}