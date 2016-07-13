<%@ page import="models.Book" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>books</title>
    <link href="/css/style-main.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
    <div class="header"><jsp:include page="../WEB-INF/jspf/header.jsp"/></div>
    <div class="sidebar"><jsp:include page="../WEB-INF/jspf/left_menu.jsp"/></div>

        <%
            request.setCharacterEncoding("UTF-8");
            long genreId = 0L;
            try {
                genreId = Long.valueOf(request.getParameter("genre_id"));


            }catch (Exception e) {e.printStackTrace();}
        %>

        <jsp:useBean id="bookList" class="models.BookList" scope="page"/>

    <div class="content">
            <h3 align="сenter">${param.name}</h3>
            <table сelpadding="25" style="font-size: 15px">
                <%

                    //for search button
//                    String search_string = request.getParameter("search_string");
//                    ArrayList<Book> allBooks = bookList.getAllBooks();
//
//                    for (Book book : allBooks) {
//
//                    }

                    ArrayList<Book> list = bookList.getBooksByGenre(genreId);

                    session.setAttribute("currentBooks", list);
                    for (Book book : list) {

                %>
                <div class="book_title">
                    <p style="color:#378de5 ;font-weight: bold; font-size: 18px;"> <%=book.getName()%></p>

                    <div class="book_image">
                        <img src="<%=request.getContextPath()%>/showImage?index=<%=list.indexOf(book)%>" width="190" height="252">
                    </div>
                    <div class="book_info">
                        <div class="book_font">
                            <br><strong>ISBN:</strong> <%=book.getIsbn()%>
                            <br><strong>Издательство:</strong> <%=book.getPublisher() %>
                            <br><strong>Количество страниц:</strong> <%=book.getPageCount() %>
                            <br><strong>Год издания:</strong> <%=book.getPublishDate() %>
                            <br><strong>Автор:</strong> <%=book.getAuthor() %>
                            <p style="margin:10px;"> <a href="#">Читать</a></p>
                        </div>


                    <%--end book_info--%>
                    </div>


                    <%--end book_title--%>
                </div>

                <%}%>

            </table>

        <%--end content--%>
    </div>

    <jsp:include page="../WEB-INF/jspf/footer.jsp"/>
</body>
</html>
