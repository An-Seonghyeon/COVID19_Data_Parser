package edu.handong.csee.java.hw5;
import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;

/**
 * This program implements to read the csv file which has conditions that 
 * consists one country name in one line, and may contain wrong country names and empty or space lines.
 * The read csv file will be used to select certain countries from another data file and display the
 * data from selected countries. This program used Apache Commons CSV to read the csv files efficiently.
 * 
 * @author Seonghyeon An
 * @version 1.0
 * @since 2020-06-06
 */
public class CountryListCsvReader {
	private CovidArrayList<String> countryList = new CovidArrayList<String>();
	
	/**
	 * This is the constructor to read csv file that contains the certain country lists and
	 * save them into the String typed customized linked list. It used useful tools provided by Apache Commons CSV, such as
	 * CSVParser and CSVRecord. It reads one country in one line respectively and saves/adds them in the
	 * linked list in order. It also used try-catch block and if the file name is not existed, it will display the messages
	 * to check the file name or path again.
	 * 
	 * @param fileName A csv file to read.
	 */
	public CountryListCsvReader(String fileName) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(fileName));
			CSVParser parser = CSVParser.parse(reader, CSVFormat.TDF.withIgnoreEmptyLines());
			
			for(CSVRecord record: parser) {
				if(record.get(0).endsWith(",")){
					countryList.add(record.get(0).replace("," , ""));
				}
				
				else {
					countryList.add(record.get(0));
				}
			}
			
			reader.close();
			parser.close();
			
		} catch(IOException e) {
			System.out.println("There is no such existed file.\nPlease check the file name or path again.");
		}
	}
	
	/**
	 * This is the method to return the String typed customized linked list that consists the list of countries 
	 * after successfully read the csv file and saves/adds them into the linked list in order.
	 * 
	 * @return A String typed customized linked list that contains the list of countries from the read csv file.
	 */
	public CovidArrayList<String> getCountryLinkedList() {
		return countryList;
	}
}