import java.io.*;
import java.nio.Buffer;

public class ReferenceIncrementor {

    /*Attributes*/

    private int nextRef;

    /*Constructor*/

    public ReferenceIncrementor()
    {
        this.readFile();
        this.writeFile(nextRef);
    }

    /*Getter methods*/

    public int getNextRef()
    {
        return this.nextRef;
    }

    /*Setter methods*/

    private void setNextRef(int nextRef)
    {
        this.nextRef = nextRef + 1;
    }

    /*Other methods*/

    private void readFile()
    {
        String filename = "referenceIncrementor.txt";
        String line = null;

        try
        {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null)
            {
                this.setNextRef(Integer.parseInt(line));
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("unable to open file '" + filename + "'");
        }
        catch(IOException ex)
        {
            System.out.println("Error reading file '" + filename + "'");
        }

    }

    private void writeFile(int nextRef)
    {
        try
        {
            String content = Integer.toString(nextRef);
            String filepath = "referenceIncrementor.txt";
            File file = new File(filepath);

            if(!file.exists())
            {
                file.createNewFile();
            }

            FileWriter filw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter buffw = new BufferedWriter(filw);

            buffw.write(content);

            buffw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void resetFile(int nextRef)
    {
        try
        {
            String content = Integer.toString(nextRef);
            String filepath = "referenceIncrementor.txt";
            File file = new File(filepath);

            FileWriter filw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter buffw = new BufferedWriter(filw);

            buffw.write(content);

            buffw.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
