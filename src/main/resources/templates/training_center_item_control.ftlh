<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение тренингововго центра #${selectedTrainingCenter.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for=name_id">Адрес</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="name_id" type="text" name="address" value="${selectedTrainingCenter.address}"
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
        <a type="button" class="btn btn-primary" href="/training_center">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/training_center/more/${selectedTrainingCenter.id}/delete">Удалить</button>
    </form>
</@c.page>