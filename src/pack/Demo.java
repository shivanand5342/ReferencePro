package pack;

/*
Region ;**** Directives created by AutoIt3Wrapper_GUI ****
AutoIt3Wrapper_Outfile_x64=QXDM_Alt_I_Press.exe
AutoIt3Wrapper_UseX64=y
EndRegion ;**** Directives created by AutoIt3Wrapper_GUI ****
*/

import java.io.File;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;

import com.jacob.com.LibraryLoader;

import autoitx4java.AutoItX;

class Demo{
	
	final static String dir = System.getProperty("user.dir");
	public static void main(String[] args) {
		//Launch Browser
		System.setProperty("webdriver.chrome.driver", dir+"//"+"chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
	   
		
	}
	
}