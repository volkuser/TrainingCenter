<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение местоположения #${selectedLocation.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for=name_id">Номер</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="name_id" type="number" name="number" value="${selectedLocation.number}"
                           class="form-control ${(numberError??)?string('is-invalid', '')} p-2">
                    <#if numberError??>
                        <div class="invalid-feedback p-2">
                            ${numberError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="mb-3">
            <label class="form-label">Тренинговый центр</label>
            <label>
                <select name="trainingCenter" class="form-control">
                    <#list trainingCenters as trainingCenter>
                        <option value="${trainingCenter.id}">${trainingCenter.address}</option>
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