<#import "components/common.ftlh" as c>

<@c.page>
    <div class="row justify-content-center mt-2">
        <#list protocols as protocol>

                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${protocol.fileName}</h5>
                            <div class="table-responsive-sm">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">ИНН</th>
                                        <th scope="col">Наименование организации</th>
                                        <th scope="col">Фамилия</th>
                                        <th scope="col">Имя</th>
                                        <th scope="col">Отчество</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <#assign end=protocol.parsedData?size>
                                    <#list 0..end as index>
                                        <#--<tr>
                                            <td><#if protocol.parsedData[index].itn??>${protocol.parsedData[index].itn}<#else></#if></td>
                                            <td><#if protocol.parsedData[index].organizationName??>${protocol.parsedData[index].organizationName}<#else></#if></td>
                                            <td><#if protocol.parsedData[index].lastname??>${protocol.parsedData[index].lastname}<#else></#if></td>
                                            <td><#if protocol.parsedData[index].firstname??>${protocol.parsedData[index].firstname}<#else></#if></td>
                                            <td><#if protocol.parsedData[index].patronymic??>${protocol.parsedData[index].patronymic}<#else></#if></td>
                                        </tr>-->
                                        <tr>
                                            <#list protocol.statuses[index] as status>
                                                <td><#if status??>${status}<#else></#if></td>
                                            </#list>
                                        </tr>
                                    </#list>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
            </div>
        </#list>
    </div>

</@c.page>