/**
 * Created by Mark on 15.03.2017.
 */
$(document).ready(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });
    $.ajax({
        url: "maximumpages",
        type: "POST",
        dataType: 'json',
        statusCode: {
            204: function () {
                noresults();
            },
            200: function (numberOfPages) {
                initializePage(1);
                drawPaginationMain(numberOfPages)
            }
        }
    });
});

function drawPaginationMain(numberOfPages) {
    $("#pagination").empty();
    for (var pageNumber = 1; pageNumber <= numberOfPages; pageNumber++) {
        drawPaginationButtonMain(pageNumber);
    }
    $(".pagination-button:first").removeClass("btn-info").addClass("btn-success");
}

function drawPaginationButtonMain(pageNumber) {
    $("<button/>", {
        text: pageNumber,
        type: "button",
        class: "btn btn-info pagination-button",
        click: function () {
            initializePage(pageNumber);
            $(":button").addClass("btn-info").removeClass("btn-success");
            $(this).removeClass("btn-info").addClass("btn-success");
        }
    }).appendTo("#pagination")
}

function initializePage(pageNumber) {
    $.ajax({
        url: "allproducts",
        type: "POST",
        data: {
            pageNumber: pageNumber
        },
        dataType: 'json',
        success: function (data) {
            drawpage(data)
        }
    })
}
