<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление оборудованием</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Модель</th>
                    <th scope="col">Тип оборудования</th>
                    <th scope="col">Единица измерения</th>
                </tr>
                </thead>
                <tbody>
                <#list equipments as equipment>
                    <tr>
                        <td>${equipment.id}</td>
                        <td>${equipment.model}</td>
                        <td>${equipment.equipmentType.name}</td>
                        <td>${equipment.unit.name}</td>
                        <td><a href="equipment/more/${equipment.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>нет оборудования</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Добавить новое оборудование</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="number_id">Модель</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="number_id" type="text" name="model"
                                       class="form-control ${(modelError??)?string('is-invalid', '')} p-2">
                                <#if modelError??>
                                    <div class="invalid-feedback p-2">
                                        ${modelError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="number_id">Описание</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="number_id" type="text" name="description"
                                       class="form-control ${(descriptionError??)?string('is-invalid', '')} p-2">
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
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>