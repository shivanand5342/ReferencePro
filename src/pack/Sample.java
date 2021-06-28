package pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sample {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("D:\\Issues\\APPS_List\\Telus_V27.txt");
		Scanner scan = new Scanner(file); //Use Scanner to read the File
       /** while(file.) {
		String str =scan.nextLine();
		//String str = "/system/app/DownloadProviderUi/DownloadProviderUi.apk=com.android.providers.downloads.ui";
		
		String[] listStr = str.split("/");
		
		System.out.println(listStr[3]);
        }*/
		
       
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          String[] listStr = data.split("/");
  		
  		  System.out.println(listStr[3]);
        }
        myReader.close();
		
	}

}
