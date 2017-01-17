package geo.azure.test;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.naming.ServiceUnavailableException;

import com.microsoft.aad.adal4j.*;

public class BillingRate {

	public static String getADCredentialsToken(ADCredentialParameter credParam)
			throws ServiceUnavailableException, MalformedURLException, ExecutionException, InterruptedException {
		AuthenticationContext context;
		AuthenticationResult result = null;
		ExecutorService service = null;
		try {
			service = Executors.newFixedThreadPool(1);
			
		
			context = new AuthenticationContext(String.format("%s/%s", credParam.getAdCredentialEndpoint(),credParam.getServiceTenantId()),
					true, service);
			ClientCredential cred = new ClientCredential(credParam.getAdApplicationId(), credParam.getAdApplicationPassword());
			Future<AuthenticationResult> future = context.acquireToken(credParam.getServiceManagementEndpoint(), cred,
					null);
			result = future.get();
		} finally {
			service.shutdown();
		}

		if (result == null) {
			throw new ServiceUnavailableException("authentication result was null");
		}
		return result.getAccessToken();
	}
	

	public static String getRequestUrl(BillingRequestParameter requestParam) {

		String parameters = String.format(
				"api-version=%s&$filter=OfferDurableId eq '%s' and Currency eq '%s' and Locale eq '%s' and RegionInfo eq '%s'",
				requestParam.getApiVersion(), 
				requestParam.getFilter(), 
				requestParam.getCurrency(),
				requestParam.getLocale(), 
				requestParam.getRegionInfo());

		String requestURL = String.format("%s/subscriptions/%s/providers/Microsoft.Commerce/RateCard?%s",
				requestParam.getServiceManagementEndpoint(), 
				requestParam.getServiceSubscriptionId(), 
				parameters);

		//将空格符替换为%20
		requestURL =requestURL.replaceAll(" ", "%20");
		return requestURL;
	}

}
