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
                                        <tr>
                                            <td>${protocol.parsedData[index].itn}</td>
                                            <td>${protocol.parsedData[index].organizationName}</td>
                                            <td>${protocol.parsedData[index].lastname}</td>
                                            <td>${protocol.parsedData[index].firstname}</td>
                                            <td>${protocol.parsedData[index].patronymic}</td>
                                        </tr>
                                        <tr>
                                            <#list protocol.statuses[index] as status>
                                                <td>${status}</td>
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