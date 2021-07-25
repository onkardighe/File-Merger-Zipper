import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
class OwnListener extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}
class MarvellousPacker
{
    FileOutputStream outstream = null;
    String ValidExt[] = {".txt",".c",".java",".cpp"};
    public MarvellousPacker(String src, String Dest) throws Exception
    {
        File outfile =new File(Dest);
        File infile = null;
        outstream = new FileOutputStream(Dest);
    
        File folder = new File(src);
        
        System.setProperty("user.dir",src);
        
        listAllFiles(src);
    }
    
    public void listAllFiles(String path)
    {
        try(Stream<Path> paths = Files.walk(Paths.get(path)))
        {
            paths.forEach(filePath ->
                          {
                              if (Files.isRegularFile(filePath))
                              {
                                  try
                                  {
                                        String name = filePath.getFileName().toString();
                                        String ext = name.substring(name.lastIndexOf("."));
                                      
                                        List<String> list = Arrays.asList(ValidExt);
                                        if(list.contains(ext))
                                        {
                                            File file=new File(filePath.getFileName().toString());
                                            
                                            Pack(file.getAbsolutePath());
                                        }
                                  }
                                  catch (Exception e)
                                  {
                                              System.out.println(e);
                                  }
                              }
                          });
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
    public void Pack(String filePath)
    {
        FileInputStream instream = null;
        
        try
        {
            byte[] buffer = new byte[1024];
            int length;
            byte temp[] = new byte[100];
            
            File fobj = new File(filePath);
            String Header = filePath+" "+fobj.length();
            for (int i = Header.length(); i < 100; i++)
                Header += " ";
            
            temp = Header.getBytes();
            
            instream = new FileInputStream(filePath);
            outstream.write(temp, 0, temp.length);
            
            while ((length = instream.read(buffer)) > 0)
            {
                outstream.write(buffer, 0, length);
            }
        
            instream.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
class Window2
{
    public Frame fobj1;
    Button bobjpack;

    public Window2(String src,String dest) throws Exception
    {
        Frame fobj1 = new Frame("Marvellous Infosystem");
        bobjpack = new Button("PACK");
        Button bobjunpack = new Button("UNPACK");

        bobjpack.setBounds(30,100,90,30);
        bobjunpack.setBounds(140,100,180,30);

        fobj1.add(bobjpack);
        fobj1.add(bobjunpack);
        fobj1.setSize(400,250);
        fobj1.setLayout(null);
        fobj1.setVisible(true);

        fobj1.addWindowListener(new OwnListener());
        packButtonEvent(src, dest);
    }
    public void packButtonEvent(String src, String dest) throws Exception
    {
        bobjpack.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        new MarvellousPacker(src,dest);
                        fobj1.dispose(); 
                    } 
                    catch(Exception a)
                    {
                        System.out.println("EXCEPTION OCCURRED !!");
                    }
                };
            }
        );  
    }
    public void actionPerformed(ActionEvent e){}    
}
class Window1
{
    public Frame fobj;
    Button bobjsubmit;

    public Window1()
    {
        Frame fobj = new Frame("Marvellous Infosystem");
        bobjsubmit = new Button("SUBMIT");
        Button bobjprev = new Button("PREVIOUS");

        Label lobj = new Label("Packing portal");
        Label lobj1 = new Label("Directory name");
        Label lobj2 = new Label("File name");

        TextField lobj1t = new TextField();
        TextField lobj2t = new TextField();

        lobj.setBounds(120,30,180,30);

        lobj1.setBounds(30,80,90,30);
        lobj1t.setBounds(140,80,180,30);

        lobj2.setBounds(30,130,80,30);
        lobj2t.setBounds(140,130,180,30);

        bobjsubmit.setBounds(30,180,120,40);
        bobjprev.setBounds(175,180,150,40);

        fobj.add(bobjsubmit);
        fobj.add(bobjprev);
        fobj.add(lobj);
        fobj.add(lobj1);
        fobj.add(lobj2);
        fobj.add(lobj1t);
        fobj.add(lobj2t);
        fobj.setSize(400,250);
        fobj.setLayout(null);
        fobj.setVisible(true);
        
        String str1 = lobj1t.getText();
        String str2 = lobj2t.getText();
        submitButtonEvent(str1, str2);
        fobj.addWindowListener(new OwnListener());
    }

    public void submitButtonEvent(String str1, String str2)
    {
        bobjsubmit.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e) 
                {
                    try
                    {
                        new Window2(str1,str2);
                        fobj.dispose(); 
                    }
                    catch(Exception a)
                    {
                        System.out.println("EXCEPTION OCCURED !!");
                    }
                };
            }
        );
    }
}
class PackingUnpacking
{
    public static void main(String arg[])
    {
        Window1 obj = new Window1();
    }
}