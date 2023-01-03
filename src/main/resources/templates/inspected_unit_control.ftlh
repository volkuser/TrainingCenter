<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление проверяемыми единицами оборудования</h3>
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
                        <td><a href="inspected_unit/more/${equipmentUnit.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>нет проверяемых единиц оборудования</td>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</@c.page>