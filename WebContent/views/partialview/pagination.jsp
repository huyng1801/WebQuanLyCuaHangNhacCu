<nav class="container">
    <div class="d-flex justify-content-end align-items-center">
        <ul class="pagination d-flex align-items-center">
            <%@ page import="java.util.stream.IntStream" %>
            <% int totalPages = (int) request.getAttribute("totalPages"); %>
            <% int currentPage = (int) request.getAttribute("currentPage"); %>

            <% if (totalPages > 1) { %>
                <% if (currentPage > 1) { %>
                    <li class="page-item"><a class="page-link" href="?page=1"><i class="fas fa-angle-double-left"></i></a></li>
                    <li class="page-item"><a class="page-link" href="?page=<%= currentPage - 1 %>"><i class="fas fa-angle-left"></i></a></li>
                <% } else { %>
                    <li class="page-item disabled"><span class="page-link"><i class="fas fa-angle-double-left"></i></span></li>
                    <li class="page-item disabled"><span class="page-link"><i class="fas fa-angle-left"></i></span></li>
                <% } %>

                <% int rangeStart = Math.max(1, currentPage - 2); %>
                <% int rangeEnd = Math.min(totalPages, currentPage + 2); %>

                <%
                    for (int pagecrt = rangeStart; pagecrt <= rangeEnd; pagecrt++) {
                %>
                    <li class="page-item <%= (pagecrt == currentPage) ? "active" : "" %>">
                        <a class="page-link" href="?page=<%= pagecrt %>"><%= pagecrt %></a>
                    </li>
                <% } %>

                <% if (currentPage < totalPages) { %>
                    <li class="page-item"><a class="page-link" href="?page=<%= currentPage + 1 %>"><i class="fas fa-angle-right"></i></a></li>
                    <li class="page-item"><a class="page-link" href="?page=<%= totalPages %>"><i class="fas fa-angle-double-right"></i></a></li>
                <% } else { %>
                    <li class="page-item disabled"><span class="page-link"><i class="fas fa-angle-right"></i></span></li>
                    <li class="page-item disabled"><span class="page-link"><i class="fas fa-angle-double-right"></i></span></li>
                <% } %>
            <% } %>
        </ul>
    </div>
</nav>
