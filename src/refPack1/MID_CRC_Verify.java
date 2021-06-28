package refPack1;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.File;

import javax.swing.JOptionPane;

//import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
//import org.eclipse.wb.swt.SWTResourceManager;
//import org.openqa.selenium.WebDriver;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;



public class MID_CRC_Verify {
	
	protected Shell shlMidCrc;
	private Text username;
	private Text pass;
	private Text otp1;
	
	//private Text prno;
	private Text prnos;
	
	//public static String user_Name,password,otp,prno_No, pr_Nos;
	public static String user_Name,password,otp, pr_Nos;
	
	private Table table;
	public static boolean remStatus;
	
	
	Logger log= Logger.getLogger(MID_CRC_Verify.class);
	
	public static void main(String[] args) {
		try {
			//TO DELETE LOGS FOLDER
//			FileUtils.deleteDirectory(new File("Logs"));
			
			MID_CRC_Verify window = new MID_CRC_Verify();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
				
		//Configure log4J properties file
		//PropertyConfigurator.configure("log4j.properties");
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlMidCrc.open();
		shlMidCrc.layout();
		while (!shlMidCrc.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		//Configure log4J properties file
		PropertyConfigurator.configure("log4j.properties");
		
		shlMidCrc = new Shell(SWT.CLOSE | SWT.TITLE | SWT.BORDER | SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.MIN);
//		shlMidCrc.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
//		shlMidCrc.setImage(SWTResourceManager.getImage(MID_CRC_Verify.class, "/pack1/LG_UI_Logo_MID1.png"));
		shlMidCrc.setSize(1070, 655);		
		shlMidCrc.setText("MID Suffix Verification Tool Ver 2.1");
		
				
		Label lblNewLabel = new Label(shlMidCrc, SWT.NONE);
//		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
//		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD));
		lblNewLabel.setBounds(302, 10, 392, 39);
		lblNewLabel.setText("MID Suffix Verification Tool");
		
		Label lblUserName = new Label(shlMidCrc, SWT.NONE);
//		lblUserName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
//		lblUserName.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblUserName.setBounds(180, 72, 87, 15);
		lblUserName.setText("User Name : ");
		
		Label lblPassword = new Label(shlMidCrc, SWT.NONE);
//		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
//		lblPassword.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblPassword.setText("Password : ");
		lblPassword.setBounds(180, 109, 87, 15);
		
		Label lblOtp = new Label(shlMidCrc, SWT.NONE);
//		lblOtp.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
//		lblOtp.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblOtp.setText("OTP : ");
		lblOtp.setBounds(516, 69, 87, 15);
		
		/*
		Label lblModelNo = new Label(shlMidCrc, SWT.NONE);
		lblModelNo.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblModelNo.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblModelNo.setText("PR No : ");
		lblModelNo.setBounds(516, 106, 87, 15);
		*/
		
		Label lblPRNOS = new Label(shlMidCrc, SWT.NONE);
//		lblPRNOS.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
//		lblPRNOS.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblPRNOS.setText("PR NOS : ");
		lblPRNOS.setBounds(516, 106, 87, 15);
		
		username = new Text(shlMidCrc, SWT.BORDER);
		username.setToolTipText("Enter PRMS User Name");
		username.setBounds(312, 71, 122, 21);
		
		pass = new Text(shlMidCrc, SWT.BORDER | SWT.PASSWORD);
		pass.setToolTipText("Enter PRMS Password");
		pass.setBounds(312, 108, 122, 21);
		
		//CODE To Auto Update User Name and Password field in Text Box
		RememberMe rem2=new RememberMe();
		String UN_PWD=rem2.UPDATE();
		//System.out.println("UN&PWD= "+UN_PWD);
		if(UN_PWD!=null)
		{
		String[] str=UN_PWD.split(",");
		username.setText(str[0]);
		pass.setText(str[1]);
		}
		else
		{
			username.setText("");
			pass.setText("");
			
		}
		
		otp1 = new Text(shlMidCrc, SWT.BORDER);
		otp1.setToolTipText("Enter OTP");
		otp1.setBounds(625, 68, 122, 21);

		/*
		prno = new Text(shlMidCrc, SWT.BORDER);
		prno.setToolTipText("Enter PR number");
		prno.setBounds(625, 105, 122, 21);
		*/
		
		prnos = new Text(shlMidCrc, SWT.BORDER);
		prnos.setToolTipText("Enter PR Nos (Seperated by Comma)");
		prnos.setBounds(625, 105, 243, 21);
		
		//Radio buttons
		Button ieRadioButton = new Button(shlMidCrc, SWT.RADIO);
//		ieRadioButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
//		ieRadioButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		ieRadioButton.setSelection(true);
		ieRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
//		ieRadioButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		ieRadioButton.setBounds(344, 145, 90, 16);
		ieRadioButton.setText("IE Browser");
		
		Button chromeRadioButton = new Button(shlMidCrc, SWT.RADIO);
//		chromeRadioButton.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		chromeRadioButton.setSelection(false);
		chromeRadioButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
//		chromeRadioButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		chromeRadioButton.setBounds(457, 146, 130, 16);
		chromeRadioButton.setText("Chrome Browser");
		
		//clear button
		Button btnClearExcel = new Button(shlMidCrc, SWT.NONE);
		btnClearExcel.setText("CLEAR EXCEL");
		btnClearExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				table.removeAll();
//				Excel_Data excel2= new Excel_Data();
//				excel2.deleteExcelData();
//				log.info("Excel Data deleted");
				
			}
		});
		btnClearExcel.setBounds(242, 190, 130, 47);
		
		Button btnCompare = new Button(shlMidCrc, SWT.NONE);
		btnCompare.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
								
				log.info("Total No of Coulmn : "+table.getColumnCount());
				log.info("Total No of Rows : "+table.getItemCount());
				
				
				if(table.getItemCount()==0)
				{
					JOptionPane.showMessageDialog(null, "There is Nothing to Compare!!!!", "Message", JOptionPane.WARNING_MESSAGE);
				}
				
				int[][] flagArr=new int[table.getItemCount()][table.getColumnCount()];
				
				
				TableItem[] tableItems = table.getItems();
				
				for(int i=0;i<table.getItemCount();i++)
				{
					for(int j=0;j<table.getColumnCount()-3;j++)
					{
						//System.out.println(tableItems[0].getText(j+2)+" == "+tableItems[i].getText(j+2));
						if(tableItems[0].getText(j+3).equals(tableItems[i].getText(j+3)))
								{
							flagArr[i][j]=1;							
								}
						else
						{
							flagArr[i][j]=0;
							tableItems[i].setForeground(j+3, Display.getDefault().getSystemColor(SWT.COLOR_RED)); 
						}
						
					}
					System.out.println();
				}
				
				for(int i=0;i<table.getItemCount();i++)
				{
					for(int j=0;j<table.getColumnCount()-3;j++)
					{

						log.info(tableItems[0].getText(j+3)+" == "+tableItems[i].getText(j+3));
						log.info(flagArr[i][j]+"  i["+i +"]"+"["+j+"]");
												
					}
				
				}
				
				
				for(int i=0;i<table.getItemCount();i++)
				{
					for(int j=0;j<table.getColumnCount()-3;j++)
					{
						if(flagArr[i][j]==1)
						{
							tableItems[i].setText(10, "PASS");	
							//tableItems[i].setBackground(9, Display.getDefault().getSystemColor(SWT.COLOR_DARK_GREEN)); 
							tableItems[i].setBackground(10, Display.getDefault().getSystemColor(SWT.COLOR_GREEN)); 
						}
						else
						{
							tableItems[i].setText(10, "FAIL");								
						//	tableItems[i].setForeground(9, Display.getDefault().getSystemColor(SWT.COLOR_RED)); 
							tableItems[i].setBackground(10, Display.getDefault().getSystemColor(SWT.COLOR_RED)); 
																					
							break;
							
						}
						
					}
				
				}
					
			}
		});
		btnCompare.setText("COMPARE");
		btnCompare.setBounds(334, 528, 98, 33);
		
		Button btnOpenExcel = new Button(shlMidCrc, SWT.NONE);
		btnOpenExcel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
//				Excel_Data excelOpen= new Excel_Data();
//				excelOpen.openExcel();
			}
		});
		btnOpenExcel.setText("OPEN EXCEL");
		btnOpenExcel.setBounds(457, 528, 87, 33);
		
		Button button = new Button(shlMidCrc, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				table.removeAll();
				//JOptionPane.showMessageDialog(null, "Table Cleared!!!");
				
			}
		});
		button.setText("CLEAR TABLE");
		button.setBounds(574, 528, 87, 33);
		
		Button button_1 = new Button(shlMidCrc, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				table.removeAll();
							
				
//				Excel_Data importDetails=new Excel_Data();
//				int colCount=importDetails.columnCount();
				
//				if(colCount==0)
				{
					//JOptionPane.showMessageDialog(null,"There is no Data in Excel Sheet!!! ");
					JOptionPane.showMessageDialog(null,"There is no Data in Excel Sheet!!! ", "Message", JOptionPane.ERROR_MESSAGE);
				}
				
//				String[][] str1=importDetails.importExcelData();
									
				
//			for(int j=0;j<colCount;j++)
				{
				
					TableItem item1 = new TableItem(table, SWT.NONE);
					for(int i=0;i<=9;i++)
					{
									
//				    	item1.setText(i,str1[i][j]);		
					}				
					
				}			
			}
		});
		button_1.setText("IMPORT");
		button_1.setBounds(210, 528, 98, 33);
		
		table = new Table(shlMidCrc, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(19, 262, 1000, 242);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		TableColumn tblclmnModelNo = new TableColumn(table, SWT.NONE);
		tblclmnModelNo.setWidth(107);
		//tblclmnModelNo.setText("PR no");
		tblclmnModelNo.setText("HW_verison");
		
		TableColumn tblclmnSuffix = new TableColumn(table, SWT.NONE);
		tblclmnSuffix.setWidth(106);
		tblclmnSuffix.setText("OS_version");
		
		TableColumn tblclmnPRnos = new TableColumn(table, SWT.NONE);
		tblclmnPRnos.setWidth(132);
		tblclmnPRnos.setText("Widewine_DRM_write");
		
		TableColumn tblclmnSwv = new TableColumn(table, SWT.NONE);
		tblclmnSwv.setWidth(151);
		tblclmnSwv.setText("internal_memory_size");
		/*
		TableColumn tblclmnSwov = new TableColumn(table, SWT.NONE);
		tblclmnSwov.setWidth(85);
		tblclmnSwov.setText("SWOV");
		
		
		TableColumn tblclmnDbCrc = new TableColumn(table, SWT.NONE);
		tblclmnDbCrc.setWidth(83);
		tblclmnDbCrc.setText("DB CRC");
		
		
		TableColumn tblclmnFpriCrc = new TableColumn(table, SWT.NONE);
		tblclmnFpriCrc.setWidth(80);
		tblclmnFpriCrc.setText("FPRI CRC");
		
		
		TableColumn tblclmnFileCrc = new TableColumn(table, SWT.NONE);
		tblclmnFileCrc.setWidth(77);
		tblclmnFileCrc.setText("File CRC");
		
		
		TableColumn tblclmnCupssCrc = new TableColumn(table, SWT.NONE);
		tblclmnCupssCrc.setWidth(80);
		tblclmnCupssCrc.setText("Cupss CRC");
		
		
		TableColumn tblclmnNtCode = new TableColumn(table, SWT.NONE);
		tblclmnNtCode.setWidth(180);
		tblclmnNtCode.setText("NT code");
		*/
		TableColumn tbleclmnResult = new TableColumn(table, SWT.NONE);
		tbleclmnResult.setWidth(130);
		tbleclmnResult.setText("Result");
		
		Button btnClose = new Button(shlMidCrc, SWT.NONE);
		btnClose.setText("CLOSE");
		btnClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setBounds(564, 190, 130, 47);
		
		Label lblRememberMe = new Label(shlMidCrc, SWT.NONE);
//		lblRememberMe.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblRememberMe.setText("Remember Me :");
//		lblRememberMe.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.BOLD));
		lblRememberMe.setBounds(180, 146, 101, 15);
		
		Button remember = new Button(shlMidCrc, SWT.CHECK);
		remember.setSelection(true);
		
		remember.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//remStatus=remember.getSelection();
			}
		});
		remember.setBounds(312, 147, 13, 16);
		
		Label lblDesignedDeveloped = new Label(shlMidCrc, SWT.NONE);
//		lblDesignedDeveloped.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblDesignedDeveloped.setBounds(465, 593, 101, 14);
		lblDesignedDeveloped.setText("Shivanand");
		
		Label lblDevelopedBy = new Label(shlMidCrc, SWT.NONE);
//		lblDevelopedBy.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblDevelopedBy.setText("Developed by :");
		lblDevelopedBy.setBounds(372, 593, 87, 14);
		
		Button btnStart = new Button(shlMidCrc, SWT.NONE);
		btnStart.setText("START");
		btnStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try
				{
				
				user_Name=username.getText();
				password=pass.getText();
				otp=otp1.getText();	
				pr_Nos=prnos.getText();
				
				
				log.info("Display From UI");
				log.info("User Name :"+user_Name);				
				log.info("Password : *********");
				log.info("OTP :"+otp);
				log.info("PR NOS :"+pr_Nos);			
				
								
				
				remStatus=remember.getSelection();
				log.info("Status : "+remStatus);
								
				if(remStatus==true)
				{	
					RememberMe rem =new RememberMe();
					
					//System.out.println("Came inside IF Loop");
					log.info("Came inside IF Loop");
					//SAVE(user_Name.getText(),password.getText());	
										
					rem.SAVE(user_Name, password);
					
				}
				else
				{
					RememberMe rem =new RememberMe();
					rem.SAVE("","");					
				}


				
//				suffixList=suffix.split(",");
				//System.out.println("Total Suffix Count in UI Class : "+suffixList.length);
//				WebDriver driver = null;
				boolean ieStatus;
				boolean chromeStatus;
				
				ieStatus = ieRadioButton.getSelection();
				chromeStatus = chromeRadioButton.getSelection();
				PRMS_Login start=new PRMS_Login();
				
				if(ieStatus == true)
				{
					
					start.ie_prms_Login();	
				}
				else
				{
					
					start.chrome_prms_Login();	
				}
				
								
				} catch (Exception e1) {
				
					e1.printStackTrace();
				} 
				
				}
								
		});
		btnStart.setBounds(406, 190, 122, 46);
		
		Label label = new Label(shlMidCrc, SWT.NONE);
//		label.setImage(SWTResourceManager.getImage(MID_CRC_Verify.class, "/pack1/LG_UI_Logo.png"));
//		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		label.setBounds(19, 37, 81, 94);
		
		Label lblLifesGoodme = new Label(shlMidCrc, SWT.NONE);
//		lblLifesGoodme.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		lblLifesGoodme.setText("Life's Good");
//		lblLifesGoodme.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.BOLD));
//		lblLifesGoodme.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblLifesGoodme.setBounds(19, 122, 120, 33);
		
		Label lblLg = new Label(shlMidCrc, SWT.NONE);
		lblLg.setText("LG");
		//lblLg.setForeground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_FOREGROUND));
		//lblLg.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.BOLD));
		//lblLg.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
		lblLg.setBounds(100, 56, 52, 47);
		
	}	
	}