package sb.mep;

import io.dropwizard.Configuration;

import javax.validation.Valid;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class MepConfiguration extends Configuration {
	
	@Valid
	private String api;
	
	@Valid
	private String authCallback;
	
	@Valid
	private String nfs;
	
	public String getApi() {
		return api;
	}
	
	public void setApi(String api) {
		this.api = api;
	}
	
	public String getAuthCallback() {
		return authCallback;
	}
	
	public void setAuthCallback(String authCallback) {
		this.authCallback = authCallback;
	}
	
	public String getNfs() {
		return nfs;
	}
	
	public void setNfs(String nfs) {
		this.nfs = nfs;
	}
	
//	public HttpConfiguration getHttp() {
//		return http;
//	}
//	
//	public void setHttp(HttpConfiguration http) {
//		this.http = http;
//	}

}
