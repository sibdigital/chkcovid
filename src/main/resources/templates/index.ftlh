<#import "components/common.ftlh" as c>

<@c.page>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark text-center">
        <span class="navbar-brand mb-0 h1 mx-auto">Заголовок</span>
    </nav>

    <div class="container">
        <div class="row mt-1">
            <div class="col">
                <form action="/filter" method="post">
                    <div class="form-row mt-2">
                        <div class="col-md-3">
                            <input type="text" name="lastname" class="form-control mt-1" placeholder="Фамилия">
                        </div>
                        <div class="col-md-2">
                            <input type="text" name="firstname" class="form-control mt-1" placeholder="Имя">
                        </div>
                        <div class="col-md-3">
                            <input type="text" name="patronymic" class="form-control mt-1" placeholder="Отчество">
                        </div>
                        <div class="col-md-2">
                            <input type="text" name="itn" class="form-control mt-1" placeholder="ИНН">
                        </div>

                        <div class="col-md-1">
                            <input type="submit" class="btn btn-primary mt-1" value="Проверить">
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="row justify-content-center mt-2">
            <div class="col">
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
                        <#list organisations as org>
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
            </div>
        </div>
    </div>
</@c.page>