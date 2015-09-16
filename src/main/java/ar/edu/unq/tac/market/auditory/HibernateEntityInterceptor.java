package ar.edu.unq.tac.market.auditory;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import ar.edu.unq.tac.market.domain.AuditableEntity;

public class HibernateEntityInterceptor extends EmptyInterceptor implements Serializable {

	private static final long serialVersionUID = 3594513880567072558L;

	public final static String ATTRIBUTE_OID = "id";
	public final static String ATTRIBUTE_MODIFICATION_TIMESTAMP = "modificationTimestamp";
	public final static String ATTRIBUTE_CREATION_TIMESTAMP = "creationTimestamp";
	public final static String ATTRIBUTE_MODIFICATION_USER = "modificationUser";
	public final static String ATTRIBUTE_CREATION_USER = "creationUser";

	//private static final Logger LOGGER = LoggerFactory.getLogger(HibernateEntityInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.EmptyInterceptor#onFlushDirty(java.lang.Object,
	 * java.io.Serializable, java.lang.Object[], java.lang.Object[],
	 * java.lang.String[], org.hibernate.type.Type[])
	 */
	public boolean onFlushDirty(Object entity, Serializable id,
			Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) throws CallbackException {

		if (!(entity instanceof AuditableEntity)) {
			// Nothing to audit for this entity
			return false;
		}
		// else audit entity

		// TODO si el objeto es auditable guardar la pista de auditoria
		// (change set?).
		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];

			if (ATTRIBUTE_MODIFICATION_TIMESTAMP.equals(propertyName)) {
				currentState[i] = new Date();
			} else if (ATTRIBUTE_MODIFICATION_USER.equals(propertyName)) {
				currentState[i] = SpringSecurityHelper.getLoggedUsername();
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object,
	 * java.io.Serializable, java.lang.Object[], java.lang.String[],
	 * org.hibernate.type.Type[])
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) throws CallbackException {

		if (!(entity instanceof AuditableEntity)) {
			// Nothing to audit for this entity
			return false;
		}
		// else audit entity

		Date now = new Date();
		String loggedUsername = SpringSecurityHelper.getLoggedUsername();

		for (int i = 0; i < propertyNames.length; i++) {
			String propertyName = propertyNames[i];
			if (ATTRIBUTE_MODIFICATION_TIMESTAMP.equals(propertyName)) {
				state[i] = now;
			} else if (ATTRIBUTE_CREATION_TIMESTAMP.equals(propertyName)) {
				state[i] = now;
			} else if (ATTRIBUTE_MODIFICATION_USER.equals(propertyName)) {
				state[i] = loggedUsername;
			} else if (ATTRIBUTE_CREATION_USER.equals(propertyName)) {
				state[i] = loggedUsername;
			}
		}

		return true;
	}

}
