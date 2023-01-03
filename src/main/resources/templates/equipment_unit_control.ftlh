<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление единицей оборудования</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Инвентарный номер</th>
                    <th scope="col">Оборудование</th>
                    <th scope="col">Местоположение</th>
                    <th scope="col">Наличие</th>
                </tr>
                </thead>
                <tbody>
                <#list equipmentUnits as equipmentUnit>
                    <tr>
                        <td>${equipmentUnit.id}</td>
                        <td>${equipmentUnit.inventoryNumber}</td>
                        <td>${equipmentUnit.equipment.model}</td>
                        <td>${equipmentUnit.location.number} | ${equipmentUnit.location.trainingCenter.address}</td>
                        <td>${equipmentUnit.availability.name}</td>
                        <td><a href="equipment_unit/more/${equipmentUnit.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>нет единиц оборудования</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Добавить новую единицу оборудования</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="inventoryNumber_id">Инвентарный номер</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="inventoryNumber_id" type="text" name="inventoryNumber"
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
                                    <option value="${location.id}">
                                        ${location.number} | ${location.trainingCenter.address}</option>
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
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>