package utilis;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class Exports {
public static final int HTML = 1;
public static final int CSV = 2;
	public void csvOrHtml(JTable table,int fileType) {
		String fileName="" ;
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("CSV File", "csv"));
		if (chooser.showSaveDialog(table) == JFileChooser.APPROVE_OPTION) {
			
			if(this.HTML==fileType) {
				fileName = chooser.getSelectedFile().getAbsolutePath() + ".html";
			}else if(Exports.CSV==fileType) {
				fileName= chooser.getSelectedFile().getAbsolutePath() + ".csv";	
			}

			try {
				DataOutputStream dout = new DataOutputStream(new FileOutputStream(fileName));
				if(this.HTML==fileType) {
					dout.writeBytes(html(table));
				}else if(Exports.CSV==fileType) {
					dout.writeBytes(getCsv(table));	
				}
				
				dout.flush();
				dout.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	private String getCsv(JTable table) {
		String data = "";
		int row = table.getRowCount();
		int col = table.getColumnCount();
		for (int i = 0; i < table.getColumnCount(); i++) {
			data += table.getTableHeader().getColumnModel().getColumn(i).getHeaderValue()+ ",";	
		}
		data += "\n";
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				data += table.getValueAt(i, j).toString() + ",";
			}
			data += "\n";
		}

		return data;
	}

	public void pdf(JTable table) {

		Document document = new Document();
		try {

			JFileChooser chooser = new JFileChooser();
			chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF File", "pdf"));
			if (chooser.showSaveDialog(table) == JFileChooser.APPROVE_OPTION) {
				String fileName = chooser.getSelectedFile().getAbsolutePath() + ".pdf";
				PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
				document.open();
				document.addTitle("ENQUERY DETAILS");
				document.setMargins(10, 10, 10, 10);
				
//				Paragraph heading =new Paragraph("ENQUERY DETAILS");
//				heading .setAlignment(Paragraph.ALIGN_CENTER);
//				document.add(heading );
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				float width[] = {40f,200f,200f,200f,200f,200f,200f,200f};
				PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
				
//				pdfTable .setWidths(width);
//				pdfTable .setW
				for (int i = 0; i < table.getColumnCount(); i++) {
					pdfTable.addCell(table.getTableHeader().getColumnModel().getColumn(i).getHeaderValue().toString());	
				}
				

				for (int i = 0; i < table.getRowCount(); i++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						pdfTable.addCell(table.getValueAt(i, j).toString());
					}
				}
				document.add(pdfTable);
				document.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public String html(JTable table) {
		StringBuffer data = new StringBuffer();
		
		data.append("<!DOCTYPE html><html><head><title>ENQUERY</title></head><body>");
		data.append("<table border='1' cellspacing='0' cellpadding='8'><tr align='center'>");

		int row = table.getRowCount();
		int col = table.getColumnCount();
		
		for (int i = 0; i < table.getColumnCount(); i++) {
			data.append("<th>"+ table.getTableHeader().getColumnModel().getColumn(i).getHeaderValue()+"</th>");	
		}
		data.append("</tr>");
		for (int i = 0; i < row; i++) {
			data.append("<tr>");
			for (int j = 0; j < col; j++) {
				data.append("<td>"+table.getValueAt(i, j).toString()+"</td>");
			}
			data.append("</tr>");
		}
		data.append("</table></body></html>");
		return data.toString();
		}

	public void excel(JTable table,String sheetName) {
		
		
        JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF File", "pdf"));
		if (chooser.showSaveDialog(table) == JFileChooser.APPROVE_OPTION) {
			String fileName = chooser.getSelectedFile().getAbsolutePath() + ".xls";
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			Sheet firstSheet = workbook.createSheet(sheetName);
			Row rowA = firstSheet.createRow(0);
			for (int i = 0; i < table.getColumnCount(); i++) {
				Cell cellA = rowA.createCell(i);
		        cellA.setCellValue(table.getTableHeader().getColumnModel().getColumn(i).getHeaderValue().toString() );	
			}

			for (int i = 0; i < table.getRowCount(); i++) {
				 rowA = firstSheet.createRow(i+1);
			
				for (int j = 0; j < table.getColumnCount(); j++) {
					Cell cellA = rowA.createCell(j);
			        cellA.setCellValue(new HSSFRichTextString(table.getValueAt(i, j).toString()));
				}
			}
	        try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
			
	}

	
	
}