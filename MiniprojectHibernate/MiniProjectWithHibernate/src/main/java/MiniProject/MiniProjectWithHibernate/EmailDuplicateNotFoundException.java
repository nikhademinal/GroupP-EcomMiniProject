package MiniProject.MiniProjectWithHibernate;

public class EmailDuplicateNotFoundException extends Exception{
	private String message;
	
	public EmailDuplicateNotFoundException(String message) {
	super(message);
	}
}
