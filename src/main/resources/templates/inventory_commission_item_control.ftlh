<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Изменение инвентаризационной комиссии #${selectedInventoryCommission.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="surname_id">Номер</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="surname_id" type="text" name="number" value="${selectedInventoryCommission.number}"
                           class="form-control ${(numberError??)?string('is-invalid', '')} p-2">
                    <#if numberError??>
                        <div class="invalid-feedback p-2">
                            ${numberError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="eventDate_id">Event date</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="eventDate_id" type="date" name="eventDate"
                           value="${selectedInventoryCommission.eventDateAsString}"
                           class="form-control ${(eventDateError??)?string('is-invalid', '')} p-2">
                    <#if eventDateError??>
                        <div class="invalid-feedback p-2">
                            ${eventDateError}
                        </div>
                    </#if>
                </div>
            </div>
        </div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
        <a type="button" class="btn btn-primary" href="/inventory_commission">Назад</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Изменить</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/inventory_commission/more/${selectedInventoryCommission.id}/delete">Удалить</button>
    </form>
</@c.page>