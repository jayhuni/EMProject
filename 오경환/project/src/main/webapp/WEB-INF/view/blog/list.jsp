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
            th { text-align: center; width: 2%; background-color: dimgray; height: 50px; }
            td { height: 50px; text-align: center; }
        </style>
        <script>
            function search(){
                let field = document.getElementById('field').value;
                let query = document.getElementById('query').value;
                // console.log("search()", field, query);
                location.href = '/project/blog/list?f=' + field + '&q=' + query;
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
                        <h1 class="mt-4">회원목록</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">회원목록</li>
                        </ol>
                        
                        <div class="row">
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-area me-1"></i>
                                        월별 회원 가입수
                                    </div>
                                    <div class="card-body"><canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                            <div class="col-xl-6">
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-bar me-1"></i>
                                        월별 회원 가입수
                                    </div>
                                    <div class="card-body"><canvas id="myBarChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                회원 목록
                            </div>
                            <div class="card-body">
                                <table class="table table-sm table-borderless">
                                    <tr>
                                        <td style="width: 52%; text-align: left;">
                                            <h3>
                                                <strong>회원 목록</strong>
                                                <span style="font-size: 0.6em;">
                                                    <a href="/project/blog/write">
                                                        <i class="ms-5 fa-regular fa-file-lines"></i> 글쓰기
                                                    </a>
                                                </span>
                                            </h3>						
                                        </td>
                                        <td style="width: 15%;">
                                            <select class="form-select" id="field">
                                                <option value="title" ${field eq 'title' ? 'selected' : ''}>제목</option>
                                                <option value="content" ${field eq 'content' ? 'selected' : ''}>본문</option>
                                                <option value="penName" ${field eq 'penName' ? 'selected' : ''}>필명</option>
                                            </select>
                                        </td>
                                        <td style="width: 25%;">
                                            <input class="form-control" placeholder="검색할 내용" id="query" value="${query}"
                                                    onkeyup="if(window.event.keyCode==13) search()"> <%-- Key Up시 13 엔터키 --%>
                                        </td>
                                        <td style="width: 8%;">
                                            <button class="btn btn-outline-primary" onclick="search()">검색</button>
                                        </td>
                                    </tr>
                                </table>
                                <hr>
                                <table class="table table-striped">
                                    <tr class="table-secondary">
                                        <th style="width: 8%">ID</th>
                                        <th style="width: 14%">필명</th>
                                        <th style="width: 50%">제목</th>
                                        <th style="width: 20%">작성시간</th>
                                        <th style="width: 10%">조회수</th>
                                    </tr>
                                <c:forEach var="blog" items="${blogList}">
                                    <tr>					
                                        <td>${blog.bid}</td>
                                        <td>${blog.penName}</td>
                                        <td><a href="/project/blog/detail/${blog.bid}"> ${blog.title} </a></td>
                                        <td>${fn:replace(fn:substring(blog.modTime, 2, 16), 'T', ' ')}</td>
                                        <td>${blog.viewCount}</td>
                                    </tr>
                                </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <%@ include file="../common/admin_bottom.jsp"%>
                <div class="modal" id="updateModal1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">사용자 수정</h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <form action="/project/user/update" method="post">
                                    <table class="table table-borderless">
                                        <tr>
                                            <td style="width:35%"><label class="col-form-label">사용자 ID</label></td>
                                            <!-- disabled : 바뀌지 않게, 서버로 가지 않음 -->
                                            <td style="width:65%"><input type="text" id="uid" class="form-control" disabled></td>
                                        </tr>
                                        <tr>
                                            <td><label class="col-form-label">패스워드</label></td>
                                            <td><input type="password" name="pwd" class="form-control"></td>
                                        </tr>
                                        <tr>
                                            <td><label class="col-form-label">패스워드 확인</label></td>
                                            <td><input type="password" name="pwd2" class="form-control"></td>
                                        </tr>
                                        <tr>
                                            <td><label class="col-form-label">이름</label></td>
                                            <td><input type="text" name="uname" id="uname" class="form-control"></td>
                                        </tr>
                                        <tr>
                                            <td><label class="col-form-label">이메일</label></td>
                                            <td><input type="text" name="email" id="email" class="form-control"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="text-align: center;">
                                                <input class="btn btn-primary" type="submit" value="수정">
                                                <input class="btn btn-secondary ms-1" type="reset" value="취소">
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal" id="deleteModal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">사용자 삭제</h4>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <div class="modal-body">
                                <strong>삭제하시겠습니까?</strong>
                                <div class="text-center mt-5">
                                <!-- 4) delUid -->
                                    <button class="btn btn-danger" onclick="location.href='/project/user/delete/'+$('#delUid').val()">삭제</button>
                                    <button class="btn btn-secondary ms-1" data-bs-dismiss="modal">취소</button>
                                </div>
                            </div>
                        </div>
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
