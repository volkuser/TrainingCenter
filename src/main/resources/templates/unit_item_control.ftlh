<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение единицы измерения #${selectedUnit.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="code_id">Код ОКЕИ</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="code_id" type="text" value="${selectedUnit.OKEICode}" name="OKEICode"
                           class="form-control ${(OKEICodeError??)?string('is-invalid', '')} p-2">
                    <#if OKEICodeError??>
                        <div class="invalid-feedback p-2">
                            ${OKEICodeError}
                        </div>
                    </#if>
                </div>
            </div>
            <label class="form-label" for=name_id">Расшифровка</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="name_id" type="text" name="name" value="${selectedUnit.name}"
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
        <a type="button" class="btn btn-primary" href="/unit">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/unit/more/${selectedUnit.id}/delete">Удалить</button>
    </form>
</@c.page>