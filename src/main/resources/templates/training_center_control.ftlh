<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление тренинговыми центрами</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Адрес</th>
                </tr>
                </thead>
                <tbody>
                <#list trainingCenters as trainingCenter>
                    <tr>
                        <td>${trainingCenter.id}</td>
                        <td>${trainingCenter.address}</td>
                        <td><a href="training_center/more/${trainingCenter.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>нет тренинговых центров</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Добавить новый тренинговый центр</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="surname_id">Адрес</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="name_id" type="text" name="address"
                                       class="form-control ${(addressError??)?string('is-invalid', '')} p-2">
                                <#if addressError??>
                                    <div class="invalid-feedback p-2">
                                        ${addressError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>