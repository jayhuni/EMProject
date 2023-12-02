<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/project/assets/css/stylesA.css" rel="stylesheet">
        <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            td {height: 50px;}
         </style>
         <script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
         <script>
             function showModal() {
                 $('#deleteModal').modal('show');
             }
         </script>
    </head>
    <body class="sb-nav-fixed">
        <%@ include file="../common/admin_top.jsp"%>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <%@ include file="../common/admin_aside.jsp"%>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">게시판</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">게시판 목록</li>
                            <li class="breadcrumb-item"><a class="text-decoration-none" href="/project/blog/write">글쓰기</a></li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3>
                                    <div class="row">
                                        <div class="col-3">
                                            <strong>${blog.title}</strong>
                                        </div>
                                        <div class="col-3"></div>
                                        <div class="col-6 d-flex justify-content-end">
                                            <span style="font-size: 0.6em;">
                                                <a class="text-decoration-none" href="/project/blog/list">
                                                    <i class="ms-5 fa-solid fa-list"></i> 목록
                                                </a>
                                                <a href="javascript:showModal()"></a>
                                                <button type="button" class="btn btn-outline-primary btn-sm ms-3" data-bs-toggle="modal" data-bs-target="#deleteModal">
                                                    삭제
                                                </button>
                                            </span>
                                        </div>
                                    </div>
                                </h3>
                                <hr>
                                <form action="/project/blog/update" method="post">
                                    <input type="hidden" name="bid" value="${blog.bid}">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td style="width: 10%;"><label class="col-form-label">필명</label></td>
                                            <td style="width: 90%;"><input class="form-control" type="text" name="penName" value="${blog.penName}"></td>
                                        </tr>
                                        <tr>
                                            <td><label class="col-form-label">제목</label></td>
                                            <td><input class="form-control" type="text" name="title" value="${blog.title}"></td>
                                        </tr>
                                        <tr>
                                            <td><label class="col-form-label">내용</label></td>
                                            <td><textarea class="form-control" rows="10" name="content">${blog.content}</textarea></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="text-align: center;">
                                                <input class="btn btn-primary" type="submit" value="제출">
                                                <input class="btn btn-secondary" type="reset" value="취소">
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../common/admin_bottom.jsp"%>
            </div>
        </div>
        <!-- The Modal -->
        <div class="modal" id="deleteModal">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">삭제 확인</h4>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">
                        정말로 삭제하시겠습니까?
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal" 
                                onclick="location.href='/project/blog/deleteConfirm/${blog.bid}'">삭제</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                    </div>

                </div>
            </div>
        </div>
        <script src="/project/assets/js/adminScripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    </body>
</html>
