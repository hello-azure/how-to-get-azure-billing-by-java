package geo.azure.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.naming.ServiceUnavailableException;


public class Test {


	@org.junit.Test
	public void Test() throws ServiceUnavailableException, ExecutionException, InterruptedException, IOException {
		
		// Get Request URL
		BillingRequestParameter requestParam =new BillingRequestParameter();
		requestParam.setServiceManagementEndpoint("https://management.chinacloudapi.cn");
		requestParam.setServiceSubscriptionId("");
		requestParam.setApiVersion("2016-08-31-preview");
		requestParam.setFilter("MS-MC-AZR-0033P");
		requestParam.setCurrency("CNY");
		requestParam.setLocale("en-US");
		requestParam.setRegionInfo("CN");
		String requestUrl =BillingRate.getRequestUrl(requestParam);
		
		
		// Get Credential Token
		ADCredentialParameter credParam = new ADCredentialParameter();
		credParam.setServiceManagementEndpoint("<China Azure Management Endpoint>");
		credParam.setServiceTenantId("<TenantId>");
		credParam.setAdCredentialEndpoint("<China Azure AD Endpoint>");
		credParam.setAdApplicationId("<Application ID>");
		credParam.setAdApplicationPassword("<Application Password>");
		String token = BillingRate.getADCredentialsToken(credParam);
		String authorization = String.format("Bearer %s", token);
		

		// Send "GET" request
		URL url = new URL(requestUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", authorization);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setConnectTimeout(15000);
		conn.setReadTimeout(15000);

		int retVal = conn.getResponseCode();
		if (retVal == 200) {
			InputStream inStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
			String line = "";
			String result = "";
			while ((line = reader.readLine()) != null)
				if (line.length() > 0)
					result += line.trim();
			
			System.out.println(result);
			
		} else {
			System.out.println("Get billing data fail.Error code: " + retVal);
		}

	}
}
