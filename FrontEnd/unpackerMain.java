import java.io.*;
import javax.swing.JFileChooser;

public class unpackerMain 
{
    //Basic Values & Can be changed anytime
    private static String src = "../output/onkar.zip";
    private static String dest = "../";
    private static String outputFolderName = "Unpacked Files";


    public static void main(String[] args)  throws Exception
    {
        unpackerMain obj = new unpackerMain();
        // FileExporer FileExporerObj = new FileExporer();
    }


    public  unpackerMain() throws Exception
    {
        File srcFile = new File(src);
        if(srcFile.length() == 0)
        {
            emptyInput();
            return;
        }
        else
        {
            unpackFile(srcFile);
        }
    }


    // Function for if input is incorrect
    private static void emptyInput()
    {
        System.out.println("Input Source File is Empty !!");
    }

    // Function to Unpack Files 
    private void unpackFile(File srcFile) throws Exception
    {
        BufferedReader reader = new BufferedReader(new FileReader(srcFile));

        String fileString = "";
        char[] headerArr = new char[(int)srcFile.length()];
        reader.read(headerArr,0,(int)srcFile.length());
 
        for(int i = 0; i <(int)srcFile.length(); i++)
        {
            // Typecasting from Binary to character & then stored in String
            fileString = fileString+(char)headerArr[i];

        }
        int offset = 0;

        while(offset < fileString.length())
        {
            FileAttributes fileHeaderObj = headerUnpack(fileString,offset);
            fileUnpack(fileString, fileHeaderObj, offset+100);
            offset = offset + (100+fileHeaderObj.fileSize);
        }
        reader.close();

    }




/*////////////////////////////////////////////////////////////////////
                    FUNCTION : headerUnpack
---------------------------------------------------------------------
        Retreives all information from Header
        requires two paramaters 
            1. Complete String of File
            2. Starting offset of header
        Returns instance of FileAttributes class
////////////////////////////////////////////////////////////////////*/

    private FileAttributes headerUnpack(String fileString, int offset) 
    {
        String header = fileString.substring(offset,(offset+100));
        String[] headerAttributes = header.trim().split("\\s");
        return new FileAttributes(headerAttributes[0],Integer.parseInt(headerAttributes[1]));
    }

/*////////////////////////////////////////////////////////////////////
                    FUNCTION : fileUnpack
---------------------------------------------------------------------
PARAMETERS : 
    1. String of Whole  input File
    2. Instance of FileAttributes class
    3. Offset for file to be Created
////////////////////////////////////////////////////////////////////*/
    private void fileUnpack(String fileString, FileAttributes fileHeaderObj, int fileOffset) throws Exception
    {
        File outputFolderObj = new File(dest+outputFolderName);
        File outFolderFile = new File(outputFolderObj+"/"+fileHeaderObj.fileName);

        // Check for output folder exists already
        if(!(outputFolderObj.exists()))
        {
 
            if(outputFolderObj.mkdirs())
            {
                System.out.println("Unpack Output Folder Created");
            }
        }


        // Check for file already exists
        if(!(outFolderFile.exists()))
        {
            if(outFolderFile.createNewFile())
            {
                System.out.println("Unpacked file Created");
            }
        }
        String fileContent = fileString.substring(fileOffset,fileOffset+fileHeaderObj.fileSize).trim();
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(outFolderFile));
        bufferWriter.write(fileContent);
        bufferWriter.flush();
    }
    
}   // UnpackerMain CLASS END




// It's like structure in C
// this class stores all atrributes regarding the file
class FileAttributes
{
    public String fileName;
    public int fileSize;

    public FileAttributes()
    {
        fileName = "";
        fileSize = 0;
    }

    public FileAttributes(String name, int iSize)
    {
        fileName = name;
        fileSize = iSize;
    }
}



// this class used for getting input file with the help of default File Explorer
class FileExporer
{
    public FileExporer()
    {
        final JFileChooser  fc = new JFileChooser("./");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int retVal = fc.showOpenDialog(null);
        System.out.println(retVal);

    }
    
}