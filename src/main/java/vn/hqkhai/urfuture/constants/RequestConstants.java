package vn.hqkhai.urfuture.constants;

import org.apache.commons.lang3.ArrayUtils;

public class RequestConstants {

	public static final String AUTHENTICATE = "/authenticate";
	public static final String REGISTER = "/register";
	
	public static final String[] PUBLIC_REQUESTS = ArrayUtils.toArray(AUTHENTICATE, REGISTER);
	
}
