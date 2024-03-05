<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c" %>

<script>
    const status = "${response.status}";
    let result = status == "success" ? "성공했습니다." : "실패했습니다.";
    alert("${response.message}에 "+result);
    location.href = "${response.redirectUrl}";
</script>