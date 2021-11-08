package com.amazon.util;

import java.io.FileWriter;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVUtil {
	public void writeToCSV(String data) throws Exception
    {
      String csv = "src\\main\\resources\\" + "data.csv";
      CSVWriter writer = new CSVWriter(new FileWriter(csv));
        
      //Create record
      String [] record = data.split(",");
      
      //Write the record to file
      writer.writeNext(record);
        
      //close the writer
      writer.close();
    }
	
	
	@SuppressWarnings("resource")
    public void parseFullCSV() throws Exception
    {
      //Build reader instance
      CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\" + "data.csv"), ',', '"', 1);
       
      //Read all rows at once
      List<String[]> allRows = reader.readAll();
       
      //Read CSV line by line and use the string array as you want
     for(String[] row : allRows){
        System.out.println(Arrays.toString(row));
     }
    }
	
	
	@SuppressWarnings("resource")
    public void parseCSVLineByLine() throws Exception
    {
      //Start reading from line number 2 (line numbers start from zero)
      CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\" + "data.csv"), ',' , '"' , 1);
       
      //Read CSV line by line and use the string array as you want
      String[] nextLine;
      while ((nextLine = reader.readNext()) != null) {
         if (nextLine != null) {
            //Verifying the read data here
            System.out.println(Arrays.toString(nextLine));
         }
       }
    }
	
	public void appendToCSV(String data) throws Exception
	{
	      String csv = "src\\main\\resources\\" + "data.csv";
	      CSVWriter writer = new CSVWriter(new FileWriter(csv, true));
	        
	      String [] record = data.split(",");
	        
	      writer.writeNext(record);
	        
	      writer.close();
	}

	
	@SuppressWarnings("resource")
	public void customSearator() throws Exception
	{
	      //Build reader instance
	      CSVReader reader = new CSVReader(new FileReader("src\\main\\resources\\data.csv"), ';', '"', 1);
	       
	      //Read all rows at once
	      List<String[]> allRows = reader.readAll();
	       
	      //Read CSV line by line and use the string array as you want
	     for(String[] row : allRows){
	        System.out.println(Arrays.toString(row));
	     }
	}
}
