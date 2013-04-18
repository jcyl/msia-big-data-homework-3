import java.util.Arrays;
import java.util.Hashtable;
import java.io.*;
public class FlightRead {
	public static void main(String[] args) {	
		//String []files = {"test.csv", "test2.csv","On_Time_On_Time_Performance_2012_1.csv"};
		String []files = {"test.csv", "test2.csv"};
		String fileWrite = "write.csv";
		String [] toDelete = {"\"Year\"","\"Quarter\"","\"Month\"","\"DayofMonth\"","\"DayOfWeek\"","\"UniqueCarrier\"","\"Origin\"","\"OriginCityName\"","\"OriginState\"","\"Dest\"","\"DestCityName\"","\"DestState\"","\"CRSDepTime\"","\"DepTime\"","\"DepDelay\"","\"DepDelayMinutes\"","\"DepDel15\"","\"DepartureDelayGroups\"","\"TaxiOut\"","\"TaxiIn\"","\"ArrDelay\"","\"ArrDelayMinutes\"","\"ArrDel15\"","\"Cancelled\"","\"ActualElapsedTime\"","\"AirTime\"","\"Distance\""};
		// Create Hash table with the toDelete table
		Hashtable<String,Integer> hashtable = new Hashtable<String,Integer>();
		// Update the hashtable with the variables to be deleted
		for (int i=0; i<toDelete.length;i++){
			hashtable.put(toDelete[i],1);
		}
		try {
			// This part initializes the file to be written in
			FileWriter fileWriter = new FileWriter(fileWrite);
			BufferedWriter bufferedWriter= new BufferedWriter(fileWriter);
			// The name of the file to opened to be read
			for (int i=0;i<files.length;i++){
				String fileName = files[i];
				// This will reference one line at a time
				String line = null;
				try {
					// // FileReader reads text files in the default encoding.
					FileReader fileReader = 
						new FileReader(fileName);
					// Always wrap FileReader in BufferedReader.
					BufferedReader bufferedReader = 
						new BufferedReader(fileReader);
					// Extract the header to determine which coluns to keep
					line = bufferedReader.readLine();
					String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					// Create array of the indices of where the columns we want
					int lengthRemain = toDelete.length;
					int lengthOriginal = fields.length;
					int [] toDeleteIndex = new int[lengthRemain];
					int j=0;
					for (int z=0;z<lengthOriginal;z++){
						if (hashtable.get(fields[z])!=null){
							toDeleteIndex[j]=z;
							j++;
						}
					}
					// Now with the columns we know we want to delete, we run through each line to get the tokens we want, put them into a comma delimited line, and then append it to the text file
					while((line = bufferedReader.readLine()) != null) {
						String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
						// Extract only the tokens that we want
						String[] dataclean = new String[lengthRemain];
						for (int z=0;z<lengthRemain;z++){
							dataclean[z]=data[toDeleteIndex[z]];
						}
						String datacleanstring = Arrays.toString(dataclean);
						datacleanstring=datacleanstring.replaceAll("\\[","").replaceAll("\\]","");
//						System.out.println(Arrays.toString(dataclean));
						bufferedWriter.write(datacleanstring);
						//bufferedWriter.write(Arrays.toString(dataclean));
						bufferedWriter.newLine();
					}
					// Always close files.
					bufferedReader.close();			
			}
			catch(FileNotFoundException ex) {
				System.out.println(
						"Unable to open file '" + 
						fileName + "'");				
			}
			catch(IOException ex) {
				System.out.println(
						"Error reading file '" 
						+ fileName + "'");					
				// Or we could just do this: 
				// ex.printStackTrace();
			}
		}
		bufferedWriter.close();
	}
	catch(IOException ex) {
		System.out.println(
				"Error writing to file '"
				+ fileWrite + "'");
		// Or we could just do this:
		// 	            // ex.printStackTrace();
		// 	            	        }
} 
}
}
