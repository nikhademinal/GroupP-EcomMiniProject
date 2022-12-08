package MiniProject.MiniProjectWithHibernate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IdGenerator implements IdentifierGenerator {
	private int idGen()
	{
		Random r=new Random();
		return r.nextInt(100000);
	}
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		
		return idGen()+"_"+Calendar.getInstance().get(Calendar.YEAR);
	}

}
