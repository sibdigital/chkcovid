<#import "components/common.ftlh" as c>

<@c.page>
    <div class="container">
        <#list protocols as protocol>
            <tr>
                <td>${org.itn}</td>
                <td>${org.organizationName}</td>
                <td>${org.lastname}</td>
                <td>${org.firstname}</td>
                <td>${org.patronymic}</td>
            </tr>
        </#list>
    </div>

</@c.page>