<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Управление инентаризационными комиссиями</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Номер</th>
                    <th scope="col">Дата проведения</th>
                </tr>
                </thead>
                <tbody>
                <#list inventoryCommissions as inventoryCommission>
                    <tr>
                        <td>${inventoryCommission.id}</td>
                        <td>${inventoryCommission.number}</td>
                        <td>${inventoryCommission.eventDateAsString}</td>
                        <td><a href="inventory_commission/more/${inventoryCommission.id}">Подробнее</a></td>
                    </tr>
                <#else>
                    <td>инвентаризационных комиссий нет</td>
                </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <h5>Добавить новую инвентаризационную комиссию</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="number_id">Номер</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="number_id" type="text" name="number"
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
                    <button type="submit" class="btn btn-primary">Добавить</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>