package refPack1;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class PRMS_Login extends MID_CRC_Verify{
final static String dir = System.getProperty("user.dir");
public static WebDriverWait wait; 
public static String browser;
public static WebDriver driver;
Logger log= Logger.getLogger(PRMS_Login.class);

public String[] prnoList=pr_Nos.split(",");


//prms login	
void chrome_prms_Login()
{
try
{
browser="chrome";
//Configure log4J properties file
PropertyConfigurator.configure("log4j.properties");


//WRITE HEADER FIELD IN EXCEL 
log.info("Calling Excel Header from PRMS Login");
//Excel_Data excel1=new Excel_Data();
//excel1.writeExcelHeader();
log.info("Excel Header completed from PRMS Login");

//Code to LOGIN To PRMS
Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
Runtime.getRuntime().exec("taskkill /f /im chrome.exe");
log.info("Killing chromedriver Successful");

//Launch Browser
System.setProperty("webdriver.chrome.driver", dir+"//"+"chromedriver.exe");
driver=new ChromeDriver();	
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
wait = new WebDriverWait(driver, 120);

//Go to site 
driver.get("http://pdr.lge.com/plm/common/loginSso.do?origin=L2Rlc2t0b3BQZHIuZG8%3D");
driver.get("http://pdr.lge.com/plm/common/loginSso.do?origin=L2Rlc2t0b3BQZHIuZG8%3D");
driver.findElement(By.id("USER")).sendKeys(user_Name);
driver.findElement(By.id("LDAPPASSWORD")).sendKeys(password);
Thread.sleep(2000);
driver.findElement(By.id("OTPPASSWORD")).sendKeys(otp);
driver.findElement(By.xpath("//input[@type='button' and @value='Login']")).click();	

Thread.sleep(10000);	
//TO DELETE ALL UNWANTED WINDOW POP-UP
String homeWindow = driver.getWindowHandle();
closeUnwanterWindows(driver, homeWindow);
driver.get("http://pdr.lge.com/plm/common/loginSso.do?origin=L2Rlc2t0b3BQZHIuZG8%3D");
try{
WebElement comboBox1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tr[2]//td[2]//span[@class='comboBoxItemPicker'])[1]")));
comboBox1.click(); 
WebElement mc =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation' and text()='MC']")));
mc.click();
log.info("MC selected");
}catch(Exception e){log.error("Error occured while selecting MC");}	

try{
WebElement comboBox2 =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//tr[2]//td[2]//span[@class='comboBoxItemPicker'])[2]")));
comboBox2.click();
WebElement english =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='presentation' and text()='English']")));
english.click();
log.info("English language selected");
}catch(Exception lang){log.warn("Error occured while selecting language");}


Thread.sleep(50000);
//TO DELETE ALL UNWANTED WINDOW POP-UP	
closeUnwanterWindows(driver, homeWindow);
driver.switchTo().window(homeWindow);

//Select registration_search 
driver.findElement(By.xpath("//td[text()='Registration/Search']")).click();
WebElement searchPDR =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Search PDR')]")));
log.info("Is Search PDR visible: "+searchPDR.isDisplayed());
searchPDR.click();

log.info("Total Suffix Count in Login Class : "+prnoList.length);

//	//WRITE HEADER FIELD IN EXCEL 
//	Excel_Data excel1=new Excel_Data();
//	excel1.writeExcelHeader();
//	

for(int n=0;n<prnoList.length;n++)
{
//System.out.println("A"+suffixList[i]);
log.info("Extracting Version and CRC Values for PR No. : "+prnoList[n]);
extractCRCDetails(prnoList[n], homeWindow, log);
//	String suffConcat= "A"+suffixList[i];
//	extractCRCDetails(model_No,suffConcat);

}	
//CLOSE BROWSER AFTER EVERYTHING IS DONE
log.info("EXTRACTED ALL DETAILS FROM PRMS----> SUCCESS!!!!");
log.info("CLOSING BROWSER AFTER EVERYTHING IS DONE");
driver.quit();
}catch(Exception e1)
{
JOptionPane.showMessageDialog(null, "Server Is Down, Not Responding!!!!", "Message", JOptionPane.ERROR_MESSAGE);
log.error(e1);
}

}	
void ie_prms_Login()
{
try
{
browser="ie";
//Configure log4J properties file
PropertyConfigurator.configure("log4j.properties");
//WRITE HEADER FIELD IN EXCEL 
log.info("Calling Excel Header from PRMS Login");
//Excel_Data excel1=new Excel_Data();
//excel1.writeExcelHeader();
log.info("Excel Header completed from PRMS Login");

//Launch Browser
Runtime.getRuntime().exec("taskkill /f /im IEDriverServer.exe");
Runtime.getRuntime().exec("taskkill /f /im iexplore.exe");
//Runtime.getRuntime().exec("taskkill /f /im ApplnBlockedPopup.exe");

System.setProperty("webdriver.ie.driver", dir+"//"+"IEDriverServer.exe");	
driver= new InternetExplorerDriver();
driver.manage().window().maximize();
driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);
wait = new WebDriverWait(driver, 120);

//Go to site 
driver.get("http://pdr.lge.com/plm/common/loginSso.do?origin=L2Rlc2t0b3BQZHIuZG8%3D");
driver.findElement(By.id("USER")).sendKeys(user_Name);
driver.findElement(By.id("LDAPPASSWORD")).sendKeys(password);
Thread.sleep(2000);
driver.findElement(By.id("OTPPASSWORD")).sendKeys(otp);
driver.findElement(By.xpath("//input[@type='button' and @value='Login']")).click();	

//close all unwanted windows
String homeWindow = driver.getWindowHandle();
Set<String> windows=null;
int n=0;
while(n<25){
windows = driver.getWindowHandles();
Thread.sleep(3000);
if(windows.size()>1){
closeUnwanterWindows(driver, homeWindow);
break;
}
n++;
}
Thread.sleep(5000);	
//selecting MC and English language 
try{
//select MC
WebElement comboBox1 = driver.findElement(By.xpath("(//tr[2]//td[2]//span[@class='comboBoxItemPicker'])[1]"));	
comboBox1.click();
//WebElement mc = driver.findElement(By.xpath("//tr[@id='isc_PickListMenu_0_row_1']//td[text()='MC']"));
WebElement mc = driver.findElement(By.xpath("//td[@class='cellSelected' or @class='cell' and text()='MC']"));
mc.click();
log.info("MC selected");

//select english language
WebElement comboBox2 = driver.findElement(By.xpath("(//tr[2]//td[2]//span[@class='comboBoxItemPicker'])[2]"));	
comboBox2.click();

WebElement SelectEnglish = driver.findElement(By.xpath("//td[@class='cellSelected' or @class='cell' and text()='English']"));
SelectEnglish.click();
log.info("English language selected");
}catch(Exception lang){log.warn("Error occured during MC and language selection");}

String commonWindow=driver.getWindowHandle();
n=0;
while(n<25){
windows = driver.getWindowHandles();
Thread.sleep(2000);
if(windows.size()>1){
closeUnwanterWindows(driver, commonWindow);
break;
}
n++;
}

Thread.sleep(5000);	
//Select registration_search 
driver.findElement(By.xpath("//td[text()='Registration/Search']")).click();
WebElement searchPDR =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Search PDR')]")));
log.info("Is Search PDR visible: "+searchPDR.isDisplayed());
searchPDR.click();

log.info("Total Suffix Count in Login Class : "+prnoList.length);

//	//WRITE HEADER FIELD IN EXCEL 
//	Excel_Data excel1=new Excel_Data();
//	excel1.writeExcelHeader();
//	

for(int i=0;i<prnoList.length;i++)
{
//System.out.println("A"+suffixList[i]);
log.info("Extracting Version and CRC Values for PR No. : "+prnoList[i]);
extractCRCDetails(prnoList[i], commonWindow, log);
//	String suffConcat= "A"+suffixList[i];
//	extractCRCDetails(model_No,suffConcat);

}	
//CLOSE BROWSER AFTER EVERYTHING IS DONE
log.info("EXTRACTED ALL DETAILS FROM PRMS----> SUCCESS!!!!");
log.info("CLOSING BROWSER AFTER EVERYTHING IS DONE");
driver.quit();
}catch(Exception e1)
{
System.out.println(e1);
JOptionPane.showMessageDialog(null, "Server Is Down, Not Responding!!!!", "Message", JOptionPane.ERROR_MESSAGE);
log.error(e1);
}

}	 

public void extractCRCDetails(String prno, String commonWindow, Logger log) throws InterruptedException
{
//required values
String modelNo=null,suffix=null;
String HWversion , osversion , Widewine_DRM_write , internal_memory_size; 

Thread.sleep(10000);
//PDR no text field clear
for(int i=0; i<3;i++)
try{
driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
driver.findElement(By.xpath("//input[@placeholder='PDR No.']")).clear();
break;
}catch(Exception e){log.error("PDR No. field clearing: "+e.getMessage());}
//Enter PR number
for(int j=0; j<3;j++)
try {
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.findElement(By.xpath("//input[@placeholder='PDR No.']")).sendKeys(prno);
break;
} catch(Exception e) {log.error("PRNO entering : " +e.getMessage());}
for(int j=0; j<3;j++)
try {
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
driver.findElement(By.xpath("//input[@placeholder='PDR No.']")).sendKeys(Keys.ENTER);
break;
} catch(Exception e) {log.error("PRNO entering : " +e.getMessage());}

Thread.sleep(10000);

//select link
if(browser.equalsIgnoreCase("chrome")){
driver.findElement(By.xpath("//td[2]//div[@role ='presentation' and contains(text(), 'L')]")).click();
}
else if(browser.equalsIgnoreCase("ie"))
{
driver.findElement(By.xpath("//tr[@role ='listitem' and @aria-posinset ='1']//td[2][contains(text(), 'L')]")).click();; 
}
Thread.sleep(5000);

//Move to PDR property window
moveChildWinow(driver, commonWindow);
Thread.sleep(30000);
//click on pripco 
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='labelOnlyToolStripButton' and text()='PRI PCO']")));
Thread.sleep(5000);
driver.findElement(By.xpath("//td[@class='labelOnlyToolStripButton' and text()='PRI PCO']")).click(); 
Thread.sleep(10000);
moveChildWinow(driver, commonWindow);
//wait.until(ExpectedConditions.titleContains("PDR - PRI PCO Detail"));
Thread.sleep(30000);

for(int i=0;i<3;i++){
try{
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
WebElement modelNoWE = driver.findElement(By.xpath("(//table//td//label[contains(text(), 'Customer Model')]/../../following-sibling::*)[1]/div"));
modelNo = modelNoWE.getText();
log.info("Model Name: "+modelNo);
break;
}catch(Exception e){log.error("Error while fethcing suffix name: "+e.getMessage());}
}

for(int i=0;i<3;i++){
try{
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
WebElement suffixWE = driver.findElement(By.xpath("(//table//td//label[contains(text(), 'Customer Suffix')]/../../following-sibling::*)[1]/div"));
suffix = suffixWE.getText();
log.info("Suffix: "+suffix);
break;
}catch(Exception e){log.error("Error while fethcing suffix name: "+e.getMessage());}
}

Thread.sleep(5000);
JavascriptExecutor je = ((JavascriptExecutor) driver);
//scroll upto swv 
WebElement we = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'HW Version')]/../../following-sibling::*)[1]/div")); 
je.executeScript("arguments[0].scrollIntoView(true);",we); 

HWversion = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'HW Version')]/../../following-sibling::*)[1]/div")).getText();

WebElement we1 = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'Input SW Version')]/../../following-sibling::*)[1]/div")); 
je.executeScript("arguments[0].scrollIntoView(true);",we1);

osversion = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'OS version')]/../../following-sibling::*)[1]/div")).getText();

Widewine_DRM_write = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'WIDEVINE DRM WRITE')]/../../following-sibling::*)[1]/div")).getText();

internal_memory_size = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'Internal Memory Size Check')]/../../following-sibling::*)[1]/div")).getText();

/* swv = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'Output SW Version')]/../../following-sibling::*)[1]/div")).getText();
log.info("swv: "+swv);

swov = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'Input SW Version')]/../../following-sibling::*)[1]/div")).getText();
log.info("swov: "+swov);

dbcrc = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'DB CRC Check')]/../../following-sibling::*)[1]/div")).getText();
log.info("dbcrc: "+dbcrc);

fpricrc = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'FPRI CRC Check')]/../../following-sibling::*)[1]")).getText();
log.info("fpricrc: "+fpricrc);

filecrc = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'FILE CRC CHECK')]/../../following-sibling::*)[1]/div")).getText();
log.info("filecrc: "+filecrc);

cupscrc = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'CUPSS CRC CHECK')]/../../following-sibling::*)[1]/div")).getText();
log.info("cupscrc: "+cupscrc);

ntcodeText = driver.findElement(By.xpath("(//table//td//span[contains(text(), 'NT Code')]/../../following-sibling::*)[1]/div")).getText();
String[] ntc = ntcodeText.split("\\(");
ntcode = ntc[0].trim();
log.info("ntcode: "+ntcode);
*/
//close unwanted windows
Set<String> allWindows=driver.getWindowHandles();
allWindows.remove(commonWindow);
Iterator<String> it = allWindows.iterator();
while(it.hasNext()){
String wh = it.next();
driver.switchTo().window(wh).close();

}
//Excel_Data excel= new Excel_Data();	
//excel.writeExcel(modelNo, suffix, prno, swv, swov, dbcrc, fpricrc, filecrc, cupscrc, ntcode);
//excel.writeExcel(HWversion, osversion, Widewine_DRM_write, internal_memory_size);
driver.switchTo().window(commonWindow);	

}

//close all unwanted windows
public static void closeUnwanterWindows(WebDriver driver, String homeWindow)
{
try
{
Set<String> allWindows = driver.getWindowHandles();
//Use Iterator to iterate over windows
Iterator<String> windowIterator = allWindows.iterator();

//Verify next window is available
while(windowIterator.hasNext()){

//Store the Recruiter window id
String childWindow = windowIterator.next();

//Here we will compare if parent window is not equal to child window 
if (!homeWindow.equals(childWindow)){	
driver.switchTo().window(childWindow);
driver.close();
}
}	
//SWICTH BACK TO HOME WINDOW
driver.switchTo().window(homeWindow);
}catch(Exception e)
{
//SWICTH BACK TO HOME WINDOW
driver.switchTo().window(homeWindow);
}
}

//move child window
public static void moveChildWinow(WebDriver driver, String homeWindow){
try
{
Set<String> allWindows = driver.getWindowHandles();

//Use Iterator to iterate over windows
Iterator<String> windowIterator = allWindows.iterator();

//Verify next window is available
while(windowIterator.hasNext()){

//Store the Recruiter window id
String childWindow = windowIterator.next();

//Here we will compare if parent window is not equal to child window 
if (!homeWindow.equals(childWindow)){	
driver.switchTo().window(childWindow);
driver.manage().window().maximize();	
}
}
}catch(Exception e){}
}
}

