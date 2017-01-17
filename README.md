**一. 参考资料**

``` powershell
1. https://msdn.microsoft.com/en-us/library/azure/mt219004.aspx 
2. https://github.com/blrchen/AzureSamples/tree/master/RateCard

```


**二. 获取AD认证信息**

``` powershell
1. 在PowerShell中，登录Azure 账户
   Login-AzureRmAccount -EnvironmentName AzureChinaCloud

2. 选择当前订阅ID，我们可以获得【TenantId】
   Set-AzureRmContext -SubscriptionId <your subscription id>

   Environment           : AzureChinaCloud
   Account               : ...
   TenantId              : ...
   SubscriptionId        : ...
   SubscriptionName      : ...
   CurrentStorageAccount : 

3. 创建AD Application,我们可以获得【Application ID】和设置的【Password】
   $azureAdApplication = New-AzureRmADApplication -DisplayName "exampleapp" -HomePage "https://www.contoso.org" -IdentifierUris "https://www.contoso.org/example" -Password "<Your_Password>"

   DisplayName             : exampleapp
   Type                    : Application
   ApplicationId           : ...
   ApplicationObjectId     : ...
   AvailableToOtherTenants : ...
   AppPermissions          : {}
   IdentifierUris          : {https://www.contoso.org/example}
   ReplyUrls               : {}

4. 创建服务凭证：
   New-AzureRmADServicePrincipal -ApplicationId $azureAdApplication.ApplicationId
   当你创建完成服务凭证后，初始是没有任何权限的，我们需要为其设置权限范围，你需要现实的为你的服务凭证设置具体的权限

5. 授权
   为你的服务凭证添加角色设置，在这个例子里，你将为你的服务凭证设置访问你订阅下所有资源的读权限。 如果想了解更多内容，请参考：https://docs.microsoft.com/en-us/azure/active-directory/role-based-access-control-what-is
   New-AzureRmRoleAssignment -RoleDefinitionName Reader -ServicePrincipalName $azureAdApplication.ApplicationId
   
 6. 以上设置完或，我们可以获得【Subscription ID】、【TenantId】、【Application ID】、【Application Password】，同时还需要两个重要的Endpoint：
    China Azure AD Endpoint: 		https://management.chinacloudapi.cn
    China Azure Management Endpoint：	https://management.chinacloudapi.cn
   
```


**三. 代码测试**

``` java
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

```


