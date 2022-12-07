<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение сотрудника #${selectedEmployee.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="surname_id">Фамилия</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="surname_id" type="text" value="${selectedEmployee.surname}" name="surname"
                           class="form-control ${(surnameError??)?string('is-invalid', '')} p-2">
                    <#if surnameError??>
                        <div class="invalid-feedback p-2">
                            ${surnameError}
                        </div>
                    </#if>
                </div>
            </div>
            <label class="form-label" for=name_id">Имя</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="name_id" type="text" name="name" value="${selectedEmployee.name}"
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
        <a type="button" class="btn btn-primary" href="/employee">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/employee/more/${selectedEmployee.id}/delete">Удалить</button>
    </form>
</@c.page>