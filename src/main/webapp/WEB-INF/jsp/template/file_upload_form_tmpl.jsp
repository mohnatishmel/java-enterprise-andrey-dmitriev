<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal fade" id="uploadFileModal" tabindex="-1" role="dialog" aria-labelledby="uploadFileModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <%--            <div class="modal-header">--%>
            <%--                <h5 class="modal-title" id="uploadFileModalLabel">Upload File</h5>--%>
            <%--                <button type="button" class="close" data-dismiss="modal" aria-label="Close">--%>
            <%--                    <span aria-hidden="true">&times;</span>--%>
            <%--                </button>--%>
            <%--            </div>--%>
            <div class="modal-body">
                <div class="form-group">
                    <form id="uploadForm" method="POST" enctype="multipart/form-data">
                        <label for="uploadFileInput">Select a file to upload:</label>
                        <input type="file" class="form-control" id="uploadFileInput" name="file">
                    </form>
                    <input type="hidden" id="uploadFileTaskId">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="form-control btn btn-outline-secondary" data-dismiss="modal">Close</button>
                <button type="button" class="form-control btn btn-secondary" id="uploadFileBtn" data-dismiss="modal">Upload File</button>
            </div>
        </div>
    </div>
</div>