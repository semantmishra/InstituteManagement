package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import Form.DocumentForm;
import beans.Document;
import beans.FeeBean;
import database.DBConnection;

public class DocumentDao {

	public boolean save(beans.Document doc,String sql) {
		try {
			PreparedStatement st = DBConnection.getConnection().prepareStatement(sql);
			st.setInt(1, doc.getStudentOrEmpId());
			st.setString(2, doc.getDocTitle());
			st.setBytes(3, doc.getDocument());
			if(st.executeUpdate()==1)
				return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Document> getDocument(int id,int docType) {
		try {
			String sql ="";
			if(docType==DocumentForm.STUDENT)
				sql = "SELECT `id`,`doc_title` ,`document` FROM `stddocument` where student_id =?";
			else
				sql = "SELECT `id`,`doc_title` ,`document` FROM `empdoc` where emp_id =?";
			
			List<Document> documents = new ArrayList<Document>();
			
			PreparedStatement st= DBConnection.getConnection().prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs= st.executeQuery();
			while(rs.next()) {
				Document document = new Document();
				document.setId(rs.getInt("id"));
				document.setDocTitle(rs.getString("doc_title"));
				document.setDocument(rs.getBytes("document"));
				documents.add(document);
			}
			return documents;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	

}
