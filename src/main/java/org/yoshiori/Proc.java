/**
 * 
 */
package org.yoshiori;


/**
 * @author yoshiori_shoji
 *
 */
public class Proc {
	static final Class<? extends Throwable> DEFAULT_CATCH_CLASS = Throwable.class;
	static final long DEFAULT_SLEEP_TIME = 0L;
	
	public static void retry(int retryCount, Runnable runnable) throws Throwable{
		retry(retryCount, runnable, DEFAULT_CATCH_CLASS);
	}

	public static void retry(int retryCount, Runnable runnable, Class<? extends Throwable> catchClass) throws Throwable {
		retry(retryCount, runnable, DEFAULT_SLEEP_TIME, catchClass);
	}

	public static void retry(int retryCount, Runnable runnable, long sleeptime) throws Throwable {
		retry(retryCount, runnable, sleeptime, DEFAULT_CATCH_CLASS);
	}

	public static void retry(int retryCount, Runnable runnable, long sleeptime,
			Class<? extends Throwable> catchClass) throws Throwable {
		if(catchClass == null){
			throw new IllegalArgumentException("catchClass is null");
		}
		if(sleeptime < 0){
			throw new IllegalArgumentException("sleeptime value is negative");
		}
		for(int i = 0; i < retryCount ; i++){
			try{
				runnable.run();
				break;
			} catch (Throwable e) {
				if(catchClass.isAssignableFrom(e.getClass())){
					Thread.sleep(sleeptime);
					continue;
				}
				throw e;
			}
		}
		
	}


}
