<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%@include file="../includes/header.jsp"%>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Board Read Page</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">Board Read Page</div>
            <!-- /.panel-heading -->
            <div class="panel-body">

                <div class="form-group">
                    <label>Bno</label>
                    <input class="form-control" name="Bno" value="<c:out value="${board.bno}"/>" readonly="readonly">
                </div>
                <div class="form-group">
                    <label>Title</label>
                    <input class="form-control" name="title" value="<c:out value="${board.title}"/>" readonly="readonly">
                </div>
                <div class="form-group">
                    <label>Text area</label>
                    <textarea class="form-control" rows="3" name="content" readonly="readonly"><c:out value="${board.content}"/></textarea>
                </div>
                <div class="form-group">
                    <label>Writer</label>
                    <input class="form-control" name="writer" value="<c:out value="${board.writer}"/>" readonly="readonly">
                </div>
                <button data-oper="modify" class="btn btn-default">Modify</button>
                <button data-oper="list" class="btn btn-default">List</button>

                <form id="operForm" action="/board/modify" method="get">
                    <input type="hidden" id="bno" name="bno" value="<c:out value="${board.bno}"/>">
                    <input type="hidden" name="pageNum" value="<c:out value="${cri.pageNum}"/>">
                    <input type="hidden" name="amount" value="<c:out value="${cri.amount}"/>">
                    <input type='hidden' name='type' value='<c:out value="${pageMaker.cri.type}"/>'>
                    <input type='hidden' name='keyword' value='<c:out value="${pageMaker.cri.keyword}"/>'>
                </form>

            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
</div>
<!-- /.row -->
<%@include file="../includes/footer.jsp"%>

<script type="text/javascript" src="/resources/js/reply.js"/>

<script type="text/javascript">
    $(document).ready(function () {

    });
</script>

<script type="text/javascript">
    $(document).ready(function () {

        console.log("==============");
        console.log("JS TEST");
        var bnoValue = '<c:out value="${board.bno}"/>';

        /*//for replyService add test
        replyService.add(
            {reply:"JS Test", replyer:"tester", bno:bnoValue},
            function (result) {
                alert("RESULT : " + result);
            }
        );*/

        /*//reply List test
        replyService.getList({bno:bnoValue, page:1}, function (list) {
            for(var i =0, len = list.length||0; i<len; i++){
                console.log(list[i])
            }
        });*/

        /*//reply delete test
        replyService.remove(22, function (count) {
            console.log(count);

            if(count == "success"){
                alert("REMOVED");
            }
        }, function (err) {
                alert('ERROR.....');
        });*/

        /*//reply modify test
        replyService.update(
            {rno: 21, bno: bnoValue, reply: "Modifide Reply..."},
            function (result) {
                alert("수정완료");
            }
        );*/

        //특정 댓글 조회
        replyService.get(21, function (data) {
            console.log(data);
        })

        var operForm = $("#operForm");

        //modify면 bno값을 같이 전달
        $("button[data-oper='modify']").on("click", function (e) {
            operForm.attr("action", "/board/modify").submit();
        });

        //list면 bno값 삭제후 리스트로 이동
        $("button[data-oper='list']").on("click", function (e) {
            operForm.find("#bno").remove();
            operForm.attr("action", "/board/list").submit();
        });
    });

</script>