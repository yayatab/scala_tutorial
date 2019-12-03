package office_handling

import java.io.{File, FileInputStream, FileOutputStream}
import java.util.Date

import org.apache.poi.ss.usermodel._
import org.apache.poi.xssf.usermodel.{XSSFRow, XSSFWorkbook}

import scala.collection.mutable.ListBuffer
import org.apache.commons.io.FileUtils

class Employee(var name: String, var email: String, var dateOfBirth: Date, var salary: Double) {
  // Getters and Setters (Omitted for brevity)
}


object Employee {
  val SHEET_NAME: String = "EMPLOYEES"
  val HEADEARS: Array[String] = Array("name", "email", "dateOfBirth", "salary")
}

/**
 * this calss demonstrattes how to write and read in/to Microsoft excel
 *
 */
object ExcelIO {

  def createFont(workbook: Workbook): Font = {
    val ret: Font = workbook.createFont()
    ret.setBold(true)
    ret.setFontHeightInPoints(16)
    ret.setColor(IndexedColors.BLACK.getIndex)
    ret
  }

  def copySheetToWorkBook(s: Sheet, neWorkBook: Workbook): Unit = {
    val newSheet = neWorkBook.createSheet(s.getSheetName)
    for (rowIndex <- s.getFirstRowNum to s.getLastRowNum) {
      val newRow: XSSFRow = newSheet.createRow(rowIndex).asInstanceOf[XSSFRow]
      val policy = new CellCopyPolicy.Builder().cellFormula(true).cellStyle(false).cellValue(true).build()
      newRow.copyRowFrom(s.getRow(rowIndex), policy)
    }
  }

  def main(args: Array[String]): Unit = {

    FileUtils.deleteQuietly(new File("poi-generated-file.xlsx"))

    val columns = Array("Name", "Email", "Date Of Birth", "Salary")
    val employees = new ListBuffer[Employee]
    val dob = new Date("04/24/1992")
    print(dob)

    employees.append(new Employee("Rajeev Singh", "rajeev@example.com", new Date("04/24/1992"), 1200000.0))
    employees.append(new Employee("Thomas cook", "thomas@example.com", new Date("04/24/1992"), 1500000.0))
    employees.append(new Employee("Steve Maiden", "steve@example.com", new Date("04/02/1992"), 1800000.0))

    // new .xslx file.
    val workbook: Workbook = new XSSFWorkbook()
    /* CreationHelper helps us create instances of various things like DataFormat,
             Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
    val createHelper: CreationHelper = workbook.getCreationHelper
    val sheet: Sheet = workbook.createSheet(Employee.SHEET_NAME)
    val font: Font = createFont(workbook)
    createHeaderRow(Employee.HEADEARS, sheet, font)
    writeEmployees(employees, sheet, createHelper)
    writeToFileAndClose(workbook, Employee.HEADEARS)
    val neWorkBook : Workbook = readExcel()
    println(neWorkBook.sheetIterator().hasNext)
    val newWorkBook : Workbook = new XSSFWorkbook()
    writeEmployees(employees, newWorkBook.createSheet(Employee.SHEET_NAME +"2"), createHelper)
    val s = newWorkBook.getSheetAt(0)
    copySheetToWorkBook(s, neWorkBook)
    writeToFileAndClose(newWorkBook, Employee.HEADEARS, "WATWATWAT.xlsx")
  }

  private def readExcel(inPath: String = "poi-generated-file.xlsx"): Workbook =  {
    new XSSFWorkbook(new FileInputStream(inPath))
  }

  private def writeToFileAndClose[T](workbook: Workbook, columns: Array[T], outPath: String = "poi-generated-file.xlsx"): Unit = {
    val si = workbook.sheetIterator()
    while (si.hasNext) {
      val sheet = si.next()
      for(i <- 1 to (columns.length+1)) {
        sheet.autoSizeColumn(i);
      }
    }
    val fileOut: FileOutputStream = new FileOutputStream(outPath)
    workbook.write(fileOut)
    fileOut.close()

    workbook.close()
  }


  private def createHeaderRow(headears: Array[String], sheet: Sheet, font: Font): Unit = {
    // Create a CellStyle with the font// Create a CellStyle with the font

    val headerCellStyle = sheet.getWorkbook.createCellStyle
    headerCellStyle.setFont(font)

    // Create a Row
    val headerRow = sheet.createRow(0)

    // Create cells
    for (i <- 1 to headears.length) {
      val cell = headerRow.createCell(i)
      cell.setCellValue(headears(i -1))
      cell.setCellStyle(headerCellStyle)
    }

    // Create Cell Style for formatting Date

  }

  private def writeEmployees(employees: ListBuffer[Employee], sheet: Sheet, creationHelper: CreationHelper): Unit = {
    val dateCellStyle = sheet.getWorkbook.createCellStyle
    dateCellStyle.setDataFormat(creationHelper.createDataFormat.getFormat("dd-MM-yyyy"))
    for ((employee, i) <- employees.zipWithIndex) {
      val row = sheet.createRow(i + 1)

      row.createCell(1).setCellValue(employee.name)
      row.createCell(2).setCellValue(employee.email)
      val dateOfBirthCell = row.createCell(3)
      dateOfBirthCell.setCellValue(employee.dateOfBirth)
      dateOfBirthCell.setCellStyle(dateCellStyle)
      row.createCell(4).setCellValue(employee.salary)
    }

  }
}
