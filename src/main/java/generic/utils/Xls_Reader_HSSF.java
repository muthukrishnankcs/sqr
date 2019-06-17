package generic.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

public class Xls_Reader_HSSF {
		
		public  String path;
		public  FileInputStream fis = null;
		public  FileOutputStream fileOut =null;
		private HSSFWorkbook workbook = null;
		private HSSFSheet sheet = null;
		private HSSFRow row   =null;
		private HSSFCell cell = null;
		
		
		public Xls_Reader_HSSF(String path) {
		
		this.path=path;
		try {
			fis = new FileInputStream(path);
			workbook = new HSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		}
		
		// returns the data from a cell
		/*public String getCellData(String sheetName,int colNum,int rowNum){
			//try{
				
			
			int index = workbook.getSheetIndex(sheetName);	
		
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);

			cell = row.getCell(colNum);

			
			//Iterate through each rows from first sheet
			//Iterator<Row> rowIterator = sheet.iterator();
			//while(rowIterator.hasNext()) {
				//Row row = rowIterator.next();

				//For each row, iterate through each columns
				//Iterator<Cell> cellIterator = row.cellIterator();
				//while(cellIterator.hasNext()) {

					//Cell cell = cellIterator.next();

				if(cell.getCellType()==Cell.CELL_TYPE_STRING)
				  return cell.getStringCellValue();
				return null;
				
				
				//}
				//System.out.println("");
			//}
			//file.close();
//			FileOutputStream out =
//					new FileOutputStream(new File("C:\\test.xls"));
//			workbook.write(out);
//			out.close();
					

		//} catch (FileNotFoundException e1) {
			//e1.printStackTrace();
		//} catch (IOException e1) {
			//e1.printStackTrace();
		//}
		//}
		
		}
*/	
		
		// returns the data from a cell
		public String getCellData(String sheetName,int colNum,int rowNum){
			try{
				if(rowNum <=0)
					return "";
			
			int index = workbook.getSheetIndex(sheetName);

			if(index==-1)
				return "";
			
		
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum-1);
			if(row==null)
				return "";
			cell = row.getCell(colNum);
			if(cell==null)
				return "";
			
		  if(cell.getCellType()==Cell.CELL_TYPE_STRING)
			  return cell.getStringCellValue();
		  else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC || cell.getCellType()==Cell.CELL_TYPE_FORMULA ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (HSSFDateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(HSSFDateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.MONTH)+1 + "/" +
		                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cellText;
		           
		          // System.out.println(cellText);

		         }

			  
			  
			  return cellText;
		  }else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		      return "";
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
			}
		}

}
	

