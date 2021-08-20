package edu.handong.csee.java.hw5;
import org.apache.commons.cli.*;
import java.util.*;

/**
 * This program implements to parse and display the total confirmed patients of the certain country from
 * raw data file. It calculates the total country numbers, total confirmed patients of all countries existed in data file.
 * It parses the confirmed patients of certain countries which are shown in the csv file that contains the country list per line.
 * If the csv file that contains the country list is not existed, it calculates and displays the confirmed accumulated patient numbers of each country existed in data file.
 * It also provides option to display the sorted data according to the number of confirmed patients.
 * By default, it automatically displays the data based on the alphabetical order of the country names.
 * This program used Apache Commons CLI to provide several options for implementing COVID-19 data parser program.
 * 
 * @author Seonghyeon An
 * @version 1.0
 * @since 2020-06-06
 */
public class MainRunner {
		String dataFile;
		String countrylistFile;
		boolean sort;
		boolean help;
	
	/**
	 * This is the main method to implement the COVID-19 data parser program with several options provided by Apache Commons CLI.
	 * By default, it displays the help option. The user must provide raw data file to implement the program.
	 * 
	 * @param args Options/Commands to implement the COVID-19 data parser program. Command "-h" or "-help" for further information.
	 */
	public static void main(String[] args) {
		MainRunner runner = new MainRunner();
		runner.run(args);
	}
	
	private Options createOptions() {
		Options options = new Options();
		
		options.addOption(Option.builder("d").longOpt("data").desc("Set the data file for the confirmed patients number").hasArg().argName("file path").required().build());
		
		options.addOption(Option.builder("l").longOpt("countrylist").desc("Set the csv file that contains the country names").hasArg().argName("file path").build());
		
		options.addOption(Option.builder("s").longOpt("numsort").desc("Display the country results sort by the number of patients of each country (Descending)").build());
		
		options.addOption(Option.builder("h").longOpt("help").desc("Help").build());
		
		return options;
	}
	
	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		
		String header = "COVID-19 Data Parser";
		String footer = "\nPlease report issues to 21900421@handong.edu email account";
		
		formatter.printHelp("Java2020-1-HW5", header, options, footer, true);
	}
	
	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();
		
		try {
			CommandLine cmd = parser.parse(options, args);
			
			dataFile = cmd.getOptionValue("d");
			
			countrylistFile = cmd.getOptionValue("l");
			
			sort = cmd.hasOption("s");
			
			help = cmd.hasOption("h");
			
		} catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}
	
	private void run(String[] args) {
		Options options = createOptions();
		
		if(parseOptions(options, args)) {
			if(help) {
				printHelp(options);
				return;
			}
			
			if(countrylistFile == null) {
				DataCsvReader countryData = new DataCsvReader(dataFile);
				Analyzer analyzer = new Analyzer(countryData.getCountryDataLinkedList());
				
				if(countryData.getCountryDataLinkedList().size() > 0) {
					System.out.println("The total number of countries: " + analyzer.getNumberOfCountries());
					System.out.println("The total number of the accumulated patients until now: " + analyzer.getNumberOfAllPatients());
				
					if(sort) {
						HashMap<String, Integer> hashCountryData = new HashMap<String, Integer>();
						hashCountryData = analyzer.getHashMapData();
						
						System.out.println("The total number of patients by the selected countries (Sorted by the number of confirmed patients.)");
						analyzer.printSortedHashMapByValueArrayList(hashCountryData, analyzer.getCountryList());
					}
					
					else {
						String[] allCountryList = new String[analyzer.getCountryList().size()];
						
						for(int i = 0; i < analyzer.getCountryList().size(); i++) {
							allCountryList[i] = analyzer.getCountryList().get(i);
						}
						
						HashMap<String, Integer> hashCountryData = new HashMap<String, Integer>();
						hashCountryData = analyzer.getHashMapData();
						
						Arrays.sort(allCountryList);
						
						System.out.println("The total number of patients by the selected countries (Sorted by country names in alphabetical order.)");
						
						for(String data: allCountryList) {
							System.out.println("- " + data + ": " + hashCountryData.get(data));
						}
					}
				}
			}
			
			if(countrylistFile != null) {
				DataCsvReader countryData = new DataCsvReader(dataFile);
				CountryListCsvReader countryList = new CountryListCsvReader(countrylistFile);
				Analyzer analyzer = new Analyzer(countryData.getCountryDataLinkedList());
				
				if(countryData.getCountryDataLinkedList().size() > 0 && countryList.getCountryLinkedList().size() > 0) {
					System.out.println("The total number of countries: " + analyzer.getNumberOfCountries());
					System.out.println("The total number of the accumulated patients until now: " + analyzer.getNumberOfAllPatients());
				
					if(sort) {
						HashMap<String, Integer> hashCountryData = new HashMap<String, Integer>();
						hashCountryData = analyzer.getHashMapData();
						
						System.out.println("The total number of patients by the selected countries (Sorted by the number of confirmed patients.)");
						analyzer.printSortedHashMapByValueArrayList(hashCountryData, countryList.getCountryLinkedList());
					}
					
					else {
						String[] allCountryList = new String[countryList.getCountryLinkedList().size()];
						
						for(int i = 0; i < countryList.getCountryLinkedList().size(); i++) {
							allCountryList[i] = countryList.getCountryLinkedList().get(i);
						}
						
						HashMap<String, Integer> hashCountryData = new HashMap<String, Integer>();
						hashCountryData = analyzer.getHashMapData();
						
						Arrays.sort(allCountryList);
						
						System.out.println("The total number of patients by the selected countries (Sorted by country names in alphabetical order.)");
						
						for(String data: allCountryList){
							if(hashCountryData.containsKey(data)) {
								System.out.println("- " + data + ": " + hashCountryData.get(data));
							}
						}
					}
				}
			}	
		}
	}
}