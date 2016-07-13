<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.Genre" %>
<h4>Жанры:</h4>
<ul>
    <jsp:useBean id="genreList" class="models.GenreList" scope="application"/>
    <%
        for (Genre genre : genreList.getGenreList()) {
    %>
    <li><a href="books.jsp?genre_id=<%=genre.getId()%>&name=<%=genre.getName()%>"><%=genre.getName()%></a></li>
    <%}%>
</ul>
