<#import "components/common.ftlh" as c>

<@c.page>
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
        <#list organisations as org>
            <tr>
                <td>${org}</td>
                <td>${org}</td>
                <td>${org}</td>
                <td>${org}</td>
                <td>${org}</td>
                <td>Eclair</td>
                <td>$0.87</td>
            </tr>
        </#list>

        </tbody>
    </table>
</@c.page>