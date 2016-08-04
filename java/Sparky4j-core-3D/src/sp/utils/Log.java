package sp.utils;

public class Log {
	
	public static void SPARKY_ASSERT(boolean x, String message) {
		if(!x) {
			SP_FATAL("");
			SP_FATAL("*************************");
			SP_FATAL("    ASSERTION FAILED!    ");
			SP_FATAL("*************************");
			SP_FATAL("");
			SP_FATAL(message);
			SP_FATAL("");
			for(int i = 2; i < Thread.currentThread().getStackTrace().length; i++) {				
				StackTraceElement element = Thread.currentThread().getStackTrace()[i];
				SP_FATAL("-> " + element.getClassName() + "." + element.getMethodName() + "(" + element.getFileName() + ":" + element.getLineNumber() + ")");
			}
			SP_FATAL("");
			System.exit(-1);
		}
	}
	
	public static native void SP_FATAL(String arg);
	public static native void SP_ERROR(String arg);
	public static native void SP_WARN(String arg);
	public static native void SP_INFO(String arg);
	
}
