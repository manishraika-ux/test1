


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;

import java.util.ArrayList;


public class checkString {

    public static void main(String[] args) throws IOException {
       checkString check = new checkString();
        ArrayList<String> excelNumber1= new ArrayList<>();
        excelNumber1=  check.getExcelData();
       check.getNumberFromTxt(excelNumber1);
    }
    public ArrayList<String> getExcelData() throws IOException {

        File path = new File ("src/main/resources");
        ArrayList<String> excelNumber= new ArrayList<>();
        FileInputStream fis=new FileInputStream(new File(path.getAbsolutePath()+"/test.xls"));
        HSSFWorkbook wb=new HSSFWorkbook(fis);
        HSSFSheet sheet=wb.getSheetAt(0);

        FormulaEvaluator formulaEvaluator=wb.getCreationHelper().createFormulaEvaluator();
        for(Row row: sheet)     //iteration over row using for each loop
        {
            for(Cell cell: row)    //iteration over cell using for each loop
            {
                switch(formulaEvaluator.evaluateInCell(cell).getCellType())
                {
                    case Cell.CELL_TYPE_NUMERIC:
                        double  value  = cell.getNumericCellValue();
                        long lvalue = (new Double(value)).longValue();
                        String s = String.valueOf(lvalue);
                        excelNumber.add(s);
                        break;
                }
            }
            System.out.println();
        }
        System.out.println(excelNumber);
        System.out.println("-------------------------------------");
        return excelNumber;
    }

    public void getNumberFromTxt(ArrayList<String> excelNumber1) throws IOException {
        long number  = 0;
        ArrayList<String> missingNumber= new ArrayList<>();

        File path = new File ("src/main/resources");
        //FileInputStream fis=new FileInputStream(new File(path.getAbsolutePath()+"/test.txt"));
        BufferedReader br = new BufferedReader(new FileReader(path.getAbsolutePath()+"/test.txt"));
        String line;
        while((line = br.readLine())!= null)
        {
            System.out.println("the number is  " + line);
            if(excelNumber1.contains(line)){
                System.out.println("the number exist in the list " + line);
            } else {
                System.out.println("-------------------------------------");
                System.out.println("the number "+line +" doesn't exist in the list ");
                missingNumber.add(line);
                System.out.println("-------------------------------------");
            }
        }
        br.close();
        System.out.println("The list that is not included is " + missingNumber);

    }
}

