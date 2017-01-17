package geo.azure.test;

public class ADCredentialParameter {

	private String serviceManagementEndpoint;
	private String serviceTenantId;
	private String adCredentialEndpoint;
	private String adApplicationId;
	private String adApplicationPassword;
	public String getServiceManagementEndpoint() {
		return serviceManagementEndpoint;
	}
	public void setServiceManagementEndpoint(String serviceManagementEndpoint) {
		this.serviceManagementEndpoint = serviceManagementEndpoint;
	}
	public String getServiceTenantId() {
		return serviceTenantId;
	}
	public void setServiceTenantId(String serviceTenantId) {
		this.serviceTenantId = serviceTenantId;
	}
	public String getAdCredentialEndpoint() {
		return adCredentialEndpoint;
	}
	public void setAdCredentialEndpoint(String adCredentialEndpoint) {
		this.adCredentialEndpoint = adCredentialEndpoint;
	}
	public String getAdApplicationId() {
		return adApplicationId;
	}
	public void setAdApplicationId(String adApplicationId) {
		this.adApplicationId = adApplicationId;
	}
	public String getAdApplicationPassword() {
		return adApplicationPassword;
	}
	public void setAdApplicationPassword(String adApplicationPassword) {
		this.adApplicationPassword = adApplicationPassword;
	}
}
