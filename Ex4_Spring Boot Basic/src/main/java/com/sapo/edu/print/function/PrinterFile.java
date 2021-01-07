package com.sapo.edu.print.function;

import com.sapo.edu.print.Interface.Printer;
import com.sapo.edu.customer.Model.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
@Component
@Qualifier("printerFile")
public class PrinterFile implements Printer {
    private final static String workingDir = System.getProperty("user.dir");
    private final static String FILE_URL =workingDir+"\\result.txt" ;

    @Override
    public void printCustoner(Customer customer) {

    }
    /**
     * In thông báo ra File
     * @param message
     */
    @Override
    public void printMessage(String message) {
        try {

            File file = new File(FILE_URL);
            OutputStream outputStream = new FileOutputStream(file,true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.append(message+"\n");
            outputStreamWriter.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
