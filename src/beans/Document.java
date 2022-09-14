package beans;

public class Document {
	private int id,studentOrEmpId;
	private String docTitle;
	private byte[] document;

	
	public Document() {}


	public Document(int id, int studentOrEmpId, String docTitle, byte[] document) {
		super();
		this.id = id;
		this.studentOrEmpId = studentOrEmpId;
		this.docTitle = docTitle;
		this.document = document;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getStudentOrEmpId() {
		return studentOrEmpId;
	}


	public void setStudentOrEmpId(int studentOrEmpId) {
		this.studentOrEmpId = studentOrEmpId;
	}


	public String getDocTitle() {
		return docTitle;
	}


	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}


	public byte[] getDocument() {
		return document;
	}


	public void setDocument(byte[] document) {
		this.document = document;
	}
	
	
	

}
