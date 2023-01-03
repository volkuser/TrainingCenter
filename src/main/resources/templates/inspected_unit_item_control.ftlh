<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение единицы оборудования #${selectedEquipmentUnit.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="inventoryNumber_id">Инвентарный номер</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <p id="inventoryNumber_id">${selectedEquipmentUnit.inventoryNumber}"</p>
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Местоположение</label>
            <label>
                <select name="location" class="form-control">
                    <#list locations as location>
                        <#if location.equals(selectedEquipmentUnit.location)>
                            <option value="${selectedEquipmentUnit.location.id}" selected>
                                ${selectedEquipmentUnit.location.number} | ${location.trainingCenter.address}</option>
                        <#else>
                            <option value="${location.id}">${location.number} | ${location.trainingCenter.address}</option>
                        </#if>
                    </#list>
                </select>
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Наличие</label>
            <label>
                <select name="availability" class="form-control">
                    <#list availabilities as availability>
                        <#if availability.equals(selectedEquipmentUnit.availability)>
                            <option value="${selectedEquipmentUnit.availability.id}" selected>
                                ${selectedEquipmentUnit.availability.name}</option>
                        <#else>
                            <option value="${availability.id}">${availability.name}</option>
                        </#if>
                    </#list>
                </select>
            </label>
        </div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
        <a type="button" class="btn btn-primary" href="/inspected_unit">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
    </form>
</@c.page>