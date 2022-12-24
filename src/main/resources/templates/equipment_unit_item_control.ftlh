<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение единицы оборудования #${selectedEquipmentUnit.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="inventoryNumber_id">Инвентарный номер</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="inventoryNumber_id" type="text" name="inventoryNumber" value="${selectedEquipmentUnit.inventoryNumber}"
                           class="form-control ${(inventoryNumberError??)?string('is-invalid', '')} p-2">
                    <#if inventoryNumberError??>
                        <div class="invalid-feedback p-2">
                            ${inventoryNumberError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Местоположение</label>
            <label>
                <select name="location" class="form-control">
                    <#list locations as location>
                        <option value="${location.id}">${location.number}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Оборудование</label>
            <label>
                <select name="equipment" class="form-control">
                    <#list equipments as equipment>
                        <option value="${equipment.id}">${equipment.model}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Наличие</label>
            <label>
                <select name="availability" class="form-control">
                    <#list availabilities as availability>
                        <option value="${availability.id}">${availability.name}</option>
                    </#list>
                </select>
            </label>
        </div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
        <a type="button" class="btn btn-primary" href="/location">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/location/more/${selectedLocation.id}/delete">Удалить</button>
    </form>
</@c.page>