package it.unibo.oop.lab.mvcio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;


/**
 * 
 */
public class Controller {
    private File file;

    /*
     * This class must implement a simple controller responsible of I/O access. It
     * considers a single file at a time, and it is able to serialize objects in it.
     * 
     * Implement this class with:
     * 
     * 1) A method for setting a File as current file
     * 
     * 2) A method for getting the current File
     * 
     * 3) A method for getting the path (in form of String) of the current File
     * 
     * 4) A method that gets a String as input and saves its content on the current
     * file. This method may throw an IOException.
     * 
     * 5) By default, the current file is "output.txt" inside the user home folder.
     * A String representing the local user home folder can be accessed using
     * System.getProperty("user.home"). The separator symbol (/ on *nix, \ on
     * Windows) can be obtained as String through the method
     * System.getProperty("file.separator"). The combined use of those methods leads
     * to a software that runs correctly on every platform.
     */
    public Controller() {
        File defaultFile = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt");
        //File defaultFile = new File("ciao.txt");
        if (!defaultFile.exists()) {
            try {
                defaultFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.file = defaultFile;
    }
    public Controller(final String path)   {
        try {
            setFile(path);
        } catch (FileNotFoundException e) {
            e.getMessage();
            this.file = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "output.txt");
            if (!this.file.exists()) {
                try {
                    this.file.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
    /**
     * 
     * @param path as string 
     */
    public final void setFile(final String path) throws FileNotFoundException{
        File newFile = new File(path);
        if (newFile.exists()) {
            this.file = newFile;
        } else {
            throw new FileNotFoundException();
        }
    }
    /**
     * 
     * @return the file selected at the moment
     */
    public File getFile() {
        return this.file;
    }

    public class FileNotSettedException extends Exception { 
        private static final long serialVersionUID = 1L;

        public String getMessage() {
            return "file selezionato non esistente";
        }
    }
    /**
     * 
     * @return path of the file if exist
     * @throws FileNotSettedException
     */
    public String getFilePath() throws FileNotSettedException {

        if (file.exists()) {
            return file.getAbsolutePath();
        } else {
            throw new FileNotSettedException();
        }
    }
    /**
     * 
     * @return true if file is setted, false 
     */
    public boolean isFileSetted() {
        return this.file != null;
    }
    /**
     * 
     * @param string to be printed on file 
     * @throws IOException
     * @throws FileNotSettedException
     */
    public void write(final String string) throws IOException, FileNotSettedException {
        if (!isFileSetted()) {
            throw new FileNotSettedException();
        } 
        if (!file.exists() || !file.canWrite()) {
            throw new IOException();
        }
        try (PrintStream stream = new PrintStream(this.file)) {
            stream.print(string);
        } catch (FileNotFoundException e) {
            e.getMessage();
        } 
    }
}
