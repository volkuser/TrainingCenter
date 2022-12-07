<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление единицами измерения</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Код ОКЕИ</th>
                    <th scope="col">Рсшифровка</th>
                </tr>
                </thead>
                <tbody>
                <#list units as unit>
                    <tr>
                        <td>${unit.id}</td>
                        <td>${unit.OKEICode}</td>
                        <td>${unit.name}</td>
                        <td><a href="unit/more/${unit.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>единиц измерения нет</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Добавить новую единицу измерения</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="code_id">Код ОКЕИ</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="code_id" type="text" name="OKEICode"
                                       class="form-control ${(OKEICodeError??)?string('is-invalid', '')} p-2">
                                <#if OKEICodeError??>
                                    <div class="invalid-feedback p-2">
                                        ${OKEICodeError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for=name_id">Расшифровка</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="name_id" type="text" name="name"
                                       class="form-control ${(nameError??)?string('is-invalid', '')} p-2">
                                <#if nameError??>
                                    <div class="invalid-feedback p-2">
                                        ${nameError}
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