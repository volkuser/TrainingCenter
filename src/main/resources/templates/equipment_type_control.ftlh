<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление типами оборудования</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Наименование</th>
                </tr>
                </thead>
                <tbody>
                <#list equipmentTypes as equipmentType>
                    <tr>
                        <td>${equipmentType.id}</td>
                        <td>${equipmentType.name}</td>
                        <td><a href="equipment_type/more/${equipmentType.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>нет типов оборудования</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Добавить новый тип оборудоания</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="surname_id">Наименование</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="name_id" type="text" name="name"
                                       class="form-control ${(nameError??)?string('is-invalid', '')} p-2">
                                <#if nameError??>
                                    <div class="invalid-feedback p-2">
                                        ${nameError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>