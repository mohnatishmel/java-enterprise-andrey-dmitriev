<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<script>

    var taskList;
    jQuery(function () {

        function sortTasksByDateUP() {
            taskList.sort((function (a, b) {
                if (a.deaLine > b.deaLine) {
                    return 1;
                }
                if (a.deaLine < b.deaLine) {
                    return -1;
                }
                return 0;
            }))
        }

        function sortTasksByDateDown() {
            taskList.sort((function (a, b) {
                if (a.deaLine > b.deaLine) {
                    return -1;
                }
                if (a.deaLine < b.deaLine) {
                    return 1;
                }
                return 0;
            }))
        }

        function fillTaskTable() {
            taskList.forEach(function (task, i) {
                $("<tr>").appendTo($("#taskTable"))
                    .append($("<td>").text(i + 1))
                    .append($("<td>").text(task.name))
                    .append($("<td>").text(task.description))
                    .append($("<td>").text(task.fixed))
                    .append($("<td>").text(new Date(task.deaLine.toString()).toDateString()));
            });
        }

        function fetchTasks() {

            return $.getJSON("/?command=LoadTaskList", function (result) {
                taskList = result;
            });
        }

        // var jqxhr = $.getJSON( "example.json", function() {
        //     console.log( "success" );
        // })
        //     .done(function() {
        //         console.log( "second success" );
        //     })
        //     .fail(function() {
        //         console.log( "error" );
        //     })
        //     .always(function() {
        //         console.log( "complete" );
        //     });


        $(document).ready(function () {
            fetchTasks().done(function () {
                fillTaskTable();
            })
                .fail(function () {
                    console.log("error");
                })
                .always(function () {
                    console.log("complete");
                });
            // fillTaskTable();

            // $.getJSON("/?command=LoadTaskList", function (result) {
            //     taskList = result;
            //     taskList.forEach(function (task, i) {
            //         $("<tr>").appendTo($("#taskTable"))
            //             .append($("<td>").text(i + 1))
            //             .append($("<td>").text(task.name))
            //             .append($("<td>").text(task.description))
            //             .append($("<td>").text(task.fixed))
            //             .append($("<td>").text(new Date(task.deaLine.toString()).toDateString()));
            //     });
            // });
        });

        // $(document).on("click", "#somebutton", function() {
        //     console.log(5)
        // });

    })
</script>

<div>

</div>

<table class="table table-striped" id="taskTable">
    <thead class="thead-dark">
    <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">Description</th>
        <th scope="col">Status</th>
        <th scope="col">DeadLine</th>
    </tr>
    </thead>

    <tbody>
    </tbody>
</table>

<%--<input type="submit" id="somebutton" class="btn btn-primary" value="Fetch">--%>

