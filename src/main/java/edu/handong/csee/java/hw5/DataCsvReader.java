package edu.handong.csee.java.hw5;
import org.apache.commons.csv.*;
import java.io.*;
import java.nio.file.*;

/**
 * This program implements to read the csv file which contains the large amount of data.
 * The data file shows the accumulated confirmed patient numbers of each country and it also
 * contains additional information of each country, such as province, latitude, and longitude.
 * This program used Apache Commons CSV to read the csv files and handle the large amount of data efficiently.
 * 
 * @author Seonghyeon An
 * @version 1.0
 * @since 2020-06-06
 */
public class DataCsvReader {
	private CovidArrayList<String> countryDataList = new CovidArrayList<String>();
	
	/**
	 * This is the constructor to read csv file that contains the basic information and number of
	 * accumulated confirmed patients in each country. After successfully read the csv file, it saved
	 * into the String typed customized linked list in order. It reads one line each and saves/adds all the information from one line to the customized linked list respectively.
	 * It only reads the name of province/state, country/region, latitude, longitude, and total accumulated patient numbers
	 * which is positioned at the last column in the given csv file. It saves them together with "/" as a separator of the information category.
	 * It also used try-catch block and if the file name is not existed, it will display the messages
	 * to check the file name or path again.
	 * 
	 * @param fileName A csv file to read.
	 */
	public DataCsvReader(String fileName) {
		
		try {
			Reader reader = Files.newBufferedReader(Paths.get(fileName));
			CSVParser parser = CSVParser.parse(reader, CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
	
			for(CSVRecord record: parser) {
				String province = record.get("Province/State");
				String country = record.get("Country/Region");
				String latitude = record.get("Lat");
				String longitude = record.get("Long");
				String totalPatientsNumber = record.get(record.size() - 1);
				
				countryDataList.add(province + "/" + country + "/" + latitude + "/" + longitude + "/" + totalPatientsNumber);
			}
			
			reader.close();
			parser.close();
			
		} catch(IOException e) {
			System.out.println("There is no such existed file.\nPlease check the file name or path again.");
		}
	}
	
	/**
	 * This is the method to return the String typed customized linked list that consists the name of province/state,
	 * country/region, latitude, longitude, and total accumulated patient numbers of each country per line
	 * after successfully read the csv file. It saves/adds the information in order.
	 * 
	 * @return A String typed customized linked list that contains the name of province/state, country/region, latitude, longitude, and 
	 * total accumulated patient numbers of each country per line together with "/" in terms of separator.
	 */	
	public CovidArrayList<String> getCountryDataLinkedList() {
		return countryDataList;
	}
}