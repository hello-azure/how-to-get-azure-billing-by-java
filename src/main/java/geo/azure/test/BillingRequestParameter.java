package geo.azure.test;

public class BillingRequestParameter {
	private String serviceManagementEndpoint;
	private String serviceSubscriptionId;
	private String apiVersion;
	private String filter;
	private String currency;
	private String locale;
	private String regionInfo;
	
	public String getServiceManagementEndpoint() {
		return serviceManagementEndpoint;
	}
	public void setServiceManagementEndpoint(String serviceManagementEndpoint) {
		this.serviceManagementEndpoint = serviceManagementEndpoint;
	}
	public String getServiceSubscriptionId() {
		return serviceSubscriptionId;
	}
	public void setServiceSubscriptionId(String serviceSubscriptionId) {
		this.serviceSubscriptionId = serviceSubscriptionId;
	}
	public String getApiVersion() {
		return apiVersion;
	}
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getRegionInfo() {
		return regionInfo;
	}
	public void setRegionInfo(String regionInfo) {
		this.regionInfo = regionInfo;
	}
}
