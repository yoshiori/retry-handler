/**
 * 
 */
package org.yoshiori;

import java.util.List;

/**
 * @author yoshiori
 *
 */
public class RetryException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Throwable> throwables;
	
	public RetryException(Throwable t) {
		super(t);
	}

	public RetryException(List<Throwable> throwables) {
		this.throwables = throwables;
	}

	public List<Throwable> getThrowables() {
		return throwables;
	}

}
