<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение типа оборудования #${selectedEquipmentType.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for=name_id">Наименование</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="name_id" type="text" name="name" value="${selectedEquipmentType.name}"
                           class="form-control ${(nameError??)?string('is-invalid', '')} p-2">
                    <#if nameError??>
                        <div class="invalid-feedback p-2">
                            ${nameError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <a type="button" class="btn btn-primary" href="/equipment_type">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/equipment_type/more/${selectedEquipmentType.id}/delete">Удалить</button>
    </form>
</@c.page>