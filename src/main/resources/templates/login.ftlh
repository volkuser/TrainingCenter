<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="https://www.thymeleaf.org"
      xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
<head>
    <title>Авторизация</title>

    <meta name="viewport" content="width=device-width">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<form action="/login" method="post" class="d-flex align-items-center flex-column mb-3">
    <div>
        <div class="p-2">
            <label for="email_id" class="form-label">Email</label>
            <input type="email" name="username"
                   class="form-control" id="email_id" placeholder="name@example.com">
        </div>
        <div class="p-2">
            <label for="password_id" class="form-label">Пароль</label>
            <input type="password" class="form-control" name="password"
                   id="password_id" placeholder="password">
        </div>

        <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
        <div class="d-flex flex-row-reverse align-items-center justify-content-between">
            <div class="p-2"><input class="btn btn-primary" type="submit" value="Sign In"/></div>
            <div class="p-2"><a href="/registration">Sign Up</a></div>
        </div>
    </div>
</form>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
</script>
</body>
</html>