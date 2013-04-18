import java.util.Hashtable;
import java.io.*;
public class FlightRead {
	public static void main(String[] args) {	
		//String []files = {"test.csv", "test2.csv","On_Time_On_Time_Performance_2012_1.csv"};
		String []files = {"test.csv", "test2.csv"};
		String [] toDelete = {"\"Year\"","\"Quarter\"","\"Month\"","\"DayofMonth\"","\"DayOfWeek\"","\"UniqueCarrier\"","\"Origin\"","\"OriginCityName\"","\"OriginState\"","\"Dest\"","\"DestCityName\"","\"DestState\"","\"CRSDepTime\"","\"DepTime\"","\"DepDelay\"","\"DepDelayMinutes\"","\"DepDel15\"","\"DepartureDelayGroups\"","\"TaxiOut\"","\"TaxiIn\"","\"ArrDelay\"","\"ArrDelayMinutes\"","\"ArrDel15\"","\"Cancelled\"","\"ActualElapsedTime\"","\"AirTime\"","\"Distance\""};
		// Create Hash table with the toDelete table
		Hashtable<String,Integer> hashtable = new Hashtable<String,Integer>();
		// Update the hashtable with the variables to be deleted
		for (int i=0; i<toDelete.length;i++){
			hashtable.put(toDelete[i],1);
		}
		// The name of the file to open.
		for (int i=0;i<1;i++){
			//		String fileName = "test.csv";
			String fileName = files[i];
			//		String fileName = "On_Time_On_Time_Performance_2012_1.csv";
			// This will reference one line at a time
			String line = null;
			try {
				// // FileReader reads text files in the default encoding.
				FileReader fileReader = 
					new FileReader(fileName);
				// Always wrap FileReader in BufferedReader.
				BufferedReader bufferedReader = 
					new BufferedReader(fileReader);
				line = bufferedReader.readLine();
				String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				System.out.println("----------------------");
				for (String f : toDelete) {
					System.out.println(f);
				}
				System.out.println("----------------------");
				for (String f : fields) {
					System.out.println(f);
				}
				// Create array of the indices of where the columns we want
				int lengthRemain = toDelete.length;
				int lengthOriginal = fields.length;
				int [] toDeleteIndex = new int[lengthRemain];
				int j=0;
				System.out.println("----------------------");
				for (int z=0;z<lengthOriginal;z++){
					if (hashtable.get(fields[z])!=null){
						toDeleteIndex[j]=z;
						j++;
					}
				}
for (int z =0; z<lengthRemain; z++)System.out.println(fields[toDeleteIndex[z]]);
				//while((line = bufferedReader.readLine()) != null) {


				// Determine the columns of interest here
				//           	
				//}
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
	} 
}
