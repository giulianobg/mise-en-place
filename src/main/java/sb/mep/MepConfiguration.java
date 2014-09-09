package sb.mep;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class MepConfiguration extends Configuration {
	
	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
	
//	@Valid
//	private String api;
	
//	@Valid
//	private String authCallback;
	
//	@Valid
//	private String nfs;
	
//	public String getApi() {
//		return api;
//	}
//	
//	public void setApi(String api) {
//		this.api = api;
//	}
//	
//	public String getAuthCallback() {
//		return authCallback;
//	}
//	
//	public void setAuthCallback(String authCallback) {
//		this.authCallback = authCallback;
//	}
	
//	public String getNfs() {
//		return nfs;
//	}
//	
//	public void setNfs(String nfs) {
//		this.nfs = nfs;
//	}
	
//	public HttpConfiguration getHttp() {
//		return http;
//	}
//	
//	public void setHttp(HttpConfiguration http) {
//		this.http = http;
//	}
	
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return database;
	}
	
	@JsonProperty("database")
	public void setDataSourceFactory(DataSourceFactory database) {
		this.database = database;
	}
	
}
