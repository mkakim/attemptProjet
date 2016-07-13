<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <div class="search_form">
        <form method="get" name="search_form" action="/pages/books.jsp">
            <input type="text" name="search_string" size="70">
            <input type="submit" class="search_button" value="Поиск">
            <select>
                <option>Название</option>
                <option>Автор</option>
            </select>
        </form>
    </div>
</div>
