
import java.util.*;
import java.io.*;


public class packerMain {

    //Basic Values & Can be changed anytime
    private static String src = "./files";
    private static String dest = "./output";
    private static String outputFileName = "output";
    private static String extn[] = {".txt",".java",".c",".cpp",".py"};

    public static void main(String[] args) throws Exception
    {

        packerMain obj = new packerMain(src, extn);
    }

    public packerMain(String src, String[] extn) throws Exception
    {
        
        if((src == null) || (src == ""))
        {
            src = "./";
        }
        else if((dest == null) || (dest == ""))
        {
            dest = "./output";
        }
        else if(extn == null)
        {
            System.out.println("Extensions NOT  specified !");
        }

        File folder = new File(src);
        if(!(folder.exists()))
        {
            // Write Code For Source Path is Incorrect
            System.out.println("Src Path incorrect");
            return;
        }

        File outputFolder =  new File(dest);
        if(!(outputFolder.exists()))
        {
            outputFolder.mkdirs();
        }

        File arrFiles[] = folder.listFiles();     
        String header = null; 
        
        File fobj = new File(dest+"/"+outputFileName+".txt");
        fobj.createNewFile();
        BufferedWriter buffer = new BufferedWriter(new FileWriter(fobj));

        // /////////////////////////////////// WRITE CODE FOR IF FOLDER IS EMPTY i.e. arrFiles.length == null
        
        for(int i = 0; i < arrFiles.length; i++)
        {
            header = null;   
            for(String extName : extn)
            {
                if(arrFiles[i].getName().endsWith(extName))
                {
                    header = packerHeader(arrFiles[i]);
                    writeOutputFile(arrFiles[i], header, buffer);
                }
            }
        }

        // ziping file
        ZipFile(fobj);
    }

    private void ZipFile(File fileObj)
    {
        String newOutputFileName = fileObj.getName().replace(".txt",".zip");
        fileObj.renameTo(new File(dest+"/"+newOutputFileName));
        
    }

    private String packerHeader(File fileObj)
    {
        String fileHeader = null;

        fileHeader = fileObj.getName()+" "+fileObj.length();
        while (fileHeader.length() < 100)
        {
            fileHeader = fileHeader+" ";
        }
        return fileHeader;
    }

    private void writeOutputFile(File fileObj, String header, BufferedWriter writer) throws Exception
    {
        writer.write(header);
        writer.flush();

        BufferedReader reader = new BufferedReader(new FileReader(fileObj));

        String data = null;
        while((data = reader.readLine()) != null)
        {
            writer.write(data);
            writer.flush();
        }
    }
}


