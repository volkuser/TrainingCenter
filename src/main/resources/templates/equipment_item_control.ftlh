<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение оборудования #${selectedEquipment.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for=name_id">Модель</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="name_id" type="number" name="text" value="${selectedEquipment.model}"
                           class="form-control ${(numberError??)?string('is-invalid', '')} p-2">
                    <#if modelError??>
                        <div class="invalid-feedback p-2">
                            ${modelError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for=name_id">Описание</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="name_id" type="number" name="text" value="${selectedEquipment.description}"
                           class="form-control ${(numberError??)?string('is-invalid', '')} p-2">
                    <#if descriptionError??>
                        <div class="invalid-feedback p-2">
                            ${descriptionError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Единица измерения</label>
            <label>
                <select name="unit" class="form-control">
                    <#list units as unit>
                        <option value="${unit.id}">${unit.name}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Тип оборудования</label>
            <label>
                <select name="equipmentType" class="form-control">
                    <#list equipmentTypes as equipmentType>
                        <option value="${equipmentType.id}">${equipmentType.name}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
        <a type="button" class="btn btn-primary" href="/equipment">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/equipment/more/${selectedEquipment.id}/delete">Удалить</button>
    </form>
</@c.page>