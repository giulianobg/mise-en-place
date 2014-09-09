package sb.mep.client.views;

import io.dropwizard.views.View;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author Giuliano Griffante
 *
 */
public class SimplePageView extends View {
	
	private HttpServletRequest request;
	private Map<String, Object> params;
	
	public SimplePageView(String path) {
		super(path, Charset.forName("UTF-8"));
		this.params = new HashMap<String, Object>();
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}
	
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public void addParam(String key, Object value) {
		params.put(key, value);
	}
	
}
