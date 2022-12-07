<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление местоположением</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Номер</th>
                    <th scope="col">Тренинговый центр (адрес)</th>
                </tr>
                </thead>
                <tbody>
                <#list locations as location>
                    <tr>
                        <td>${location.id}</td>
                        <td>${location.number}</td>
                        <td>${location.trainingCenter.address}</td>
                        <td><a href="location/more/${location.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>нет местоположений</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Добавить новое местоположение</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="number_id">Номер</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="number_id" type="number" name="number"
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
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>