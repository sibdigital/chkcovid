<#import "components/common.ftlh" as c>

<@c.page>
    <div class="container">
        <table>
            <thead>
            <tr>
                <th>ИНН</th>
                <th>Наименование организации</th>
                <th>Фамилия</th>
                <th>Имя</th>
                <th>Отчество</th>
            </tr>
            </thead>

            <tbody>
            <#list organizations as org>
                <tr>
                    <td>${org.itn}</td>
                    <td>${org.organizationName}</td>
                    <td>${org.lastname}</td>
                    <td>${org.firstname}</td>
                    <td>${org.patronymic}</td>
                </tr>
            </#list>

            </tbody>
        </table>
    </div>

</@c.page>