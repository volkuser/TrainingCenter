<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01"
                aria-expanded="false" aria-label="Navigation switcher">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
                <a class="navbar-brand" href="#">Тренинговый центр</a>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="/home">Начало</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/equipment_unit">Единицы оборудования</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/location">Месоположения</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/training_center">Тренинговые центры</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/employee">Сотрудники</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/unit">Единицы измерения</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/equipment_type">Типы оборудования</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/inventory_commission">Инвентаризационные комиссии</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/equipment">Оборудование</a>
                </li>
                <#--<li class="nav-item">
                    <a class="nav-link" href="/inspected_unit">Проверяемые единицы</a>
                </li>-->
            </ul>
            <div>
                <form action="/logout" method="post">
                    <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
                    <input class="btn btn-ark" type="submit" value="Выйти"/>
                </form>
            </div>
        </div>
    </div>
</nav>