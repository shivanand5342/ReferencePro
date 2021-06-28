package refPack1;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class RememberMe extends MID_CRC_Verify {

Logger log= Logger.getLogger(RememberMe.class);	


String un_pwd;
public File file = new File("C:\\Users\\save.txt");

//	PRMS_Verify rem=new PRMS_Verify();	
//	RememberMe rem=new RememberMe();


public void SAVE(String username, String pass)
{ //Save the UserName and Password (for one user)

//Configure log4J properties file
PropertyConfigurator.configure("log4j.properties");


try {
if(!file.exists()) file.createNewFile(); //if the file !exist create a new one

BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
bw.write(username); //write the name
bw.newLine(); //leave a new Line
bw.write(pass); //write the password
bw.close(); //close the BufferdWriter
log.info("UserName and Password Save Successful!!!!");

} catch (IOException e) { e.printStackTrace(); } 
catch (Exception e) 
{ 
log.error("Executed Catch Block in RememberMe class--SAVE Method");
log.error(e);
} 

}//End Of Save


//	public void UPDATE(){ //UPDATE ON OPENING THE APPLICATION
//
// try {
// 
// System.out.println("Control Passed!!!");
// 
// if(file.exists()){ //if this file exists
//
// Scanner scan = new Scanner(file); //Use Scanner to read the File
// 
// 
// rem.username.setText(scan.nextLine()); //append the text to name field
// 
// //rem2.user_Name.setText(scan.nextLine()); //append the text to name field
// 
// rem.pass.setText(scan.nextLine()); //append the text to password field
// scan.close();
// }
//
// } catch (FileNotFoundException e) { 
// e.printStackTrace();
// } catch (NoSuchElementException e) { 
// System.out.println("Executed Catch Block when save.txt file is blank");
// rem.username.setText("");
// rem.pass.setText("");
// }
//
// }//End OF UPDATE



public String UPDATE(){ //UPDATE ON OPENING THE APPLICATION

try {

String un,pwd;


log.info("Control Passed to UPDATE Method!!!");

if(file.exists()){ //if this file exists

Scanner scan = new Scanner(file); //Use Scanner to read the File

un=scan.nextLine(); //append the text to name field 
// rem.username.setText(scan.nextLine()); //append the text to name field

//rem2.user_Name.setText(scan.nextLine()); //append the text to name field

// rem.pass.setText(scan.nextLine()); //append the text to password field
pwd=scan.nextLine(); //append the text to password field
un_pwd=un+","+pwd;

//DELETE IT
// System.out.println(un_pwd);
// return un_pwd;
scan.close();
}

} catch (FileNotFoundException e) { 
e.printStackTrace();
} catch (NoSuchElementException e) { 
log.warn("Executed Catch Block when save.txt file is blank");
//un_pwd="";
un_pwd=null;

}
catch (Exception e) 
{ log.error("Executed Catch Block in RememberMe class--UPDATE Method");
log.error(e);
} 

return un_pwd;

// finally
// {
// // System.out.println(un_pwd);
// return un_pwd;
// // scan.close();
// 
// }
// 


}//End OF UPDATE




}