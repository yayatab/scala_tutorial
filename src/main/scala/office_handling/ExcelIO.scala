package office_handling

import java.io.FileOutputStream
import java.util
import java.util.Date

import scala.collection._
import org.apache.poi.ss.usermodel.{CellStyle, CreationHelper, Font, IndexedColors, Sheet, Workbook}
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import scala.collection.mutable.ListBuffer

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
    ret.setFontHeight(14)
    ret.setColor(IndexedColors.LAVENDER.getIndex)
    ret
  }

  def main(args: Array[String]): Unit = {

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
    writeToFileAndClose(workbook, Employee.HEADEARS)
    createHeaderRow(Employee.HEADEARS, sheet, font)
    writeEmployees(employees, sheet, createHelper)
  }

  private def writeToFileAndClose[T](workbook: Workbook, columns: Array[T], outPath: String = "poi-generated-file.xlsx"): Unit = {
    val si = workbook.sheetIterator()
    while (si.hasNext) {
      val sheet = si.next()
      for(i <- 0 to columns.length) {
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
      cell.setCellValue(headears(i))
      cell.setCellStyle(headerCellStyle)
    }

    // Create Cell Style for formatting Date

  }

  private def writeEmployees(employees: ListBuffer[Employee], sheet: Sheet, creationHelper: CreationHelper): Unit = {
    val dateCellStyle = sheet.getWorkbook.createCellStyle
    dateCellStyle.setDataFormat(creationHelper.createDataFormat.getFormat("dd-MM-yyyy"))
    for ((employee, i) <- employees.zipWithIndex) {
      val row = sheet.createRow(i + 1)
      row.createCell(0).setCellValue(employee.name)
      row.createCell(1).setCellValue(employee.email)
      val dateOfBirthCell = row.createCell(2)
      dateOfBirthCell.setCellValue(employee.dateOfBirth)
      dateOfBirthCell.setCellStyle(dateCellStyle)
      row.createCell(3).setCellValue(employee.salary)
    }
  }
}
