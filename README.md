**本示例用于指导如何通过Java获取账单数据**

1. 在PowerShell中，登录Azure 账户
``` python
Login-AzureRmAccount -EnvironmentName AzureChinaCloud

```

2. 选择当前订阅ID，我们可以获得【TenantId】
``` python
Set-AzureRmContext -SubscriptionId <your subscription id>

Environment           : AzureChinaCloud
Account               : 《Account》
TenantId              : 《TenantId》
SubscriptionId        : 《SubscriptionId》
SubscriptionName      : 《SubscriptionName》
CurrentStorageAccount : 

```

3. 创建AD Application,我们可以获得【Application ID】和设置的【Password】
``` python
$azureAdApplication = New-AzureRmADApplication -DisplayName "exampleapp" -HomePage "https://www.contoso.org" -IdentifierUris "https://www.contoso.org/example" -Password "<Your_Password>"
PS C:\> $azureAdApplication

DisplayName             : exampleapp
Type                    : Application
ApplicationId           : 8bc80782-a916-47c8-a47e-4d76ed755275
ApplicationObjectId     : c95e67a3-403c-40ac-9377-115fa48f8f39
AvailableToOtherTenants : False
AppPermissions          : {}
IdentifierUris          : {https://www.contoso.org/example}
ReplyUrls               : {}

```

4. 创建服务凭证：
``` python
PS C:\> New-AzureRmADServicePrincipal -ApplicationId $azureAdApplication.ApplicationId

当你创建完成服务凭证后，初始是没有任何权限的，我们需要为其设置权限范围，你需要现实的为你的服务凭证设置具体的权限

```

5. 授权
为你的服务凭证添加角色设置，在这个例子里，你将为你的服务凭证设置访问你订阅下所有资源的读权限。 如果想了解更多内容，请参考：Azure Role-based Access Control
``` python
PS C:\> New-AzureRmRoleAssignment -RoleDefinitionName Reader -ServicePrincipalName $azureAdApplication.ApplicationId

```

6. 以上设置完或，我们可以获得AD Creditrial相关信息
``` python
【China Azure AD Login Endpoint】: https://management.chinacloudapi.cn
【China Azure Management Endpoint】：https://management.chinacloudapi.cn
【Subscription ID】
【TenantId】
【Application ID】
【Application Password】

```
