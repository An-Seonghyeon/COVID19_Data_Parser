package edu.handong.csee.java.hw5;
import java.util.*;

/**
 * This program implements to calculate and display the total confirmed patients of COVID-19 from each country,
 * according to the data from csv file. It calculates total number of countries where patients existed, 
 * total number of confirmed patients all around the world, and confirmed accumulated number of patients
 * from each country where the user wants to search.
 * 
 * @author Seonghyeon An
 * @version 1.0
 * @since 2020-06-06
 */
public class Analyzer {
	private CovidArrayList<String> dataLinkedList = new CovidArrayList<String>();
	private CovidArrayList<String> country = new CovidArrayList<String>();
	private HashMap<String, Integer> numPatientsByCountry = new HashMap<String, Integer>();
	private int totalCountriesNumber = 0;
	private int totalPatientsNumber = 0;
	private int countryPerPatientsNumber = 0;
	private int dataValue = 0;
	private String[] eachCountry;
	
	/**
	 * This is the constructor that sets a String typed customized linked list that contains overall data from each country.
	 * One node from linked list contains information from one country each.
	 * 
	 * @param data A String typed customized linked list that contains overall data of each country.
	 */
	public Analyzer(CovidArrayList<String> data) {
		dataLinkedList = data;
	}
	
	/**
	 * This is the method to calculate the total number of countries existed in data file.
	 * It automatically considers about repeated countries and ignores them during counting.
	 * It also lists down and adds all the countries existed in data file into the String typed customized linked list.
	 * 
	 * @return Total number of countries existed in data file.
	 */
	public int getNumberOfCountries() {
		for(int i = 0; i < dataLinkedList.size(); i++) {
			eachCountry = dataLinkedList.get(i).split("/");

			country.add(eachCountry[1]);
		}
		
		checkDuplication:
		for(int j = 0; j < country.size(); j++) {
			for(int k = j + 1; k < country.size(); k++) {
				if(country.get(j).equals(country.get(k))) {
					continue checkDuplication;
				}
			}
			totalCountriesNumber++;
		}
		return totalCountriesNumber;
	}
	
	/**
	 * This is the method to return the String typed customized linked list that contains the list of countries existed in data file.
	 * The duplicated countries are ignored and added into the linked list one time only.
	 * 
	 * @return A String typed customized linked list that contains the list of countries existed in data file.
	 */
	public CovidArrayList<String> getCountryList() {
		return getRemovedDuplicatedCountries(country);
	}
	
	/**
	 * This is the method to calculate the total confirmed accumulated patient numbers of all countries shown in data file.
	 * Make sure that information of each country from data file are separated by "/".
	 * DataCsvReader program will automatically separate each information by "/".
	 * 
	 * @return Total number of confirmed accumulated patients of all countries existed in data file.
	 */
	public int getNumberOfAllPatients() {
		for(int i = 0; i < dataLinkedList.size(); i++) {
			eachCountry = dataLinkedList.get(i).split("/");
			
			if(Util.isInteger(eachCountry[eachCountry.length - 1])) {
				totalPatientsNumber += Util.convertStringToValue(eachCountry[eachCountry.length - 1]);
			}
		}
		return totalPatientsNumber;
	}
	
	/**
	 * This is the method to return the HashMap that contains the each country with its confirmed accumulated patient numbers. 
	 * The key is a country name from linked list saved as a String type, and value is a confirmed accumulated patient numbers of the respective country saved as an integer type.
	 * 
	 * @return A HashMap that contains the each country with its respective confirmed accumulated patient numbers.
	 */
	public HashMap<String, Integer> getHashMapData(){
		for(int i = 0; i < getCountryList().size(); i++) {
			dataValue = getNumberOfPatientsByCountryName(getCountryList().get(i));
			
			numPatientsByCountry.put(getCountryList().get(i), dataValue);
		}
		
		return numPatientsByCountry;
	}
	
	/**
	 * This is the method to sort the HashMap data based on its value and display after sorting.
	 * It gets the HashMap that contains the country with its respective accumulated patient numbers,
	 * and a String typed customized linked list that contains the country names per line.
	 * It automatically excludes the country names from linked list that are not included in HashMap data.
	 * It only sorts the HashMap value where the key is same with country name from linked list.
	 * 
	 * @param hashData A HashMap that contains the country name with its respective accumulated patient numbers. The key is a country name saved as a String type, and value is a confirmed accumulated patient numbers of the respective country saved as an integer type.
	 * @param country A String typed customized linked list that contains the one country name per line.
	 */
	public void printSortedHashMapByValueArrayList(HashMap<String, Integer> hashData, CovidArrayList<String> country) {
		ArrayList<String> sortedValue = new ArrayList<String>();
		
		for(int i = 0; i < country.size(); i++) {
			if(hashData.containsKey(country.get(i))) {
				sortedValue.add(country.get(i));
			}
		}
		
		Collections.sort(sortedValue, (value1, value2) -> (hashData.get(value2).compareTo(hashData.get(value1))));
		
		for(String sortedCountryList: sortedValue) {
			System.out.println("- " + sortedCountryList + ": " + hashData.get(sortedCountryList));
		}
	}
	
	private CovidArrayList<String> getRemovedDuplicatedCountries(CovidArrayList<String> countryList) {
		CovidArrayList<String> temp = new CovidArrayList<String>();
		
		checkDuplication:
			for(int i = 0; i < countryList.size(); i++) {
				for(int j = i + 1; j < countryList.size(); j++) {
					if(countryList.get(i).equals(countryList.get(j))) {
						continue checkDuplication;
					}
				}
				temp.add(country.get(i));
			}
		return temp;
	}
	
	private int getNumberOfPatientsByCountryName(String countryName) {
		countryPerPatientsNumber = 0;
		
		for(int i = 0; i < dataLinkedList.size(); i++) {
			eachCountry = dataLinkedList.get(i).split("/");
			
			if(countryName.equals(eachCountry[1])) {
				if(Util.isInteger(eachCountry[eachCountry.length - 1])) {
					countryPerPatientsNumber += Util.convertStringToValue(eachCountry[eachCountry.length - 1]);
				}
			}
		}
		return countryPerPatientsNumber;
	}
}