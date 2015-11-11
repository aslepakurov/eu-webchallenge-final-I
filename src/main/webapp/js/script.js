$(function () {
    console.log("Initialized...");
    $("#submitButton").on("click", function () {
        var pointA = $("#pointAInput").val();
        var pointB = $("#pointBInput").val();
        var estimation = parseInt($("#estInput").val());
        if (pointA == "" || pointB == "" || estimation == 0) {
            $("#submitPoint").addClass("text-danger");
            $("#submitPoint").text("No points present or estimate is empty or zero");
        } else {
            console.log("Request - " + pointA + " " + pointB + " " + estimation);
            var json = "{\"pointA\":\"" + pointA + "\", \"pointB\":\"" + pointB + "\", \"estimate\":" + estimation + "}"
            console.log(json)
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/rest/krapoint/path/submit",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (json) {
                $("#submitPoint").text(json.message);
            }).error(function (json) {
                $("#submitPoint3").text(json.message);
            })
        }
    });

    $("#submitButton2").on("click", function () {
        var pointA = $("#pointAInput2").val();
        var pointB = $("#pointBInput2").val();
        var compute = true;
        if (pointA == "" || pointB == "") {
            $("#submitPoint2").addClass("text-danger");
            $("#submitPoint2").text("No points present");
        } else {
            console.log("Request - " + pointA + " " + pointB);
            var json = "{\"pointA\":\"" + pointA + "\", \"pointB\":\"" + pointB + "\", \"calculate\":" + compute + "}";
            console.log(json)
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/rest/krapoint/path/submit",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (json) {
                $("#submitPoint2").text(json.message);
            }).error(function (json) {
                $("#submitPoint3").text(json.message);
            })
        }
    });

    $("#submitButton3").on("click", function () {
        var pointA = $("#pointAInput3").val();
        var pointB = $("#pointBInput3").val();
        var compute = true;
        if (pointA == "" || pointB == "") {
            $("#submitPoint3").addClass("text-danger");
            $("#submitPoint3").text("No points present");
        } else {
            console.log("Request - " + pointA + " " + pointB);
            var json = "{\"pointA\":\"" + pointA + "\", \"pointB\":\"" + pointB + "\"}";
            console.log(json);
            $.ajax({
                method: "POST",
                url: "http://localhost:8080/rest/krapoint/path/delete",
                data: json,
                contentType: "application/json",
                accept: "application/json",
                mimeType: "application/json",
                dataType: "json"
            }).success(function (json) {
                $("#submitPoint3").text(json.message);
            }).error(function (json) {
                $("#submitPoint3").text(json.message);
            })
        }

    });
});