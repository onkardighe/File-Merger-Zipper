import java.util.*;
import java.io.*;


public class packerMain {

    //Basic Values & Can be changed anytime
    private static String dest = "../output";
    private static String outputFileName = "onkar";
    


    public packerMain(ArrayList<File> srcFile, File destFile, String[] extn)
    {

        // CHECK FOR EXTENSIONS
        if(extn == null)
        {
            System.out.println("Extensions NOT  specified !");
        }

        // CHECK FOR DESTINATION
        dest = destFile.getAbsolutePath();
        File outputFolder =  destFile;
        if(!(outputFolder.exists()))
        {
            outputFolder.mkdirs();
        }

        try
        {

            String header = null; 

            File fobj = new File(dest+"/"+outputFileName+".txt");
        
            fobj.createNewFile();
            BufferedWriter buffer = new BufferedWriter(new FileWriter(fobj));
            // /////////////////////////////////// WRITE CODE FOR IF FOLDER IS EMPTY i.e. srcFile.size() == 0
            
            for(int i = 0; i < srcFile.size(); i++)
            {
                header = null;   
                for(String extName : extn)
                {
                    if(srcFile.get(i).getName().endsWith(extName))
                    {
                        header = packerHeader(srcFile.get(i));
                        writeOutputFile(srcFile.get(i), header, buffer);
                    }
                }
            }
    
            // ziping file
            ZipFile(fobj);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println(e);

        }

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

    private void writeOutputFile(File fileObj, String header, BufferedWriter writer) 
    {
        try 
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
            reader.close();
        } 
        catch(IOException e) 
        {
            e.printStackTrace();
        }
    }
}


