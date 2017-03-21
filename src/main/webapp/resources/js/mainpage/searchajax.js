$(document).ready(function () {
    var frm = $('#searchform');
    frm.submit(function (e) {
        $('#categorycheckboxes').find('input[type=checkbox]:checked').prop('checked', false);
        $.ajax({
            url: "numberofresultsbysearch",
            type: frm.attr('method'),
            data: frm.serialize(),
            dataType: 'json',
            statusCode: {
                204: function () {
                    noresults();
                },
                200: function (numberOfPages) {
                    drawPagination($('#searchName').val(), numberOfPages);
                }
            }
        });
        e.preventDefault();
    })
});

function searchAjax(name, pageNumber) {
    $.ajax({
        url: "searchajax",
        type: "POST",
        data: {
            "name": name,
            "pageNumber": pageNumber
        },
        dataType: 'json',
        success: function (data) {
            drawpage(data)
        }
    });
}

function drawPagination(searchName, numberOfPages) {
    $("#pagination").empty();
    searchAjax(searchName, 1);
    for (var pageNumber = 1; pageNumber <= numberOfPages; pageNumber++) {
        drawPaginationButton(searchName, pageNumber);
    }
    $(".pagination-button:first").removeClass("btn-info").addClass("btn-success");
}

function drawPaginationButton(searchName, pageNumber) {
    $("<button/>", {
        text: pageNumber,
        type: "button",
        class: "btn btn-info pagination-button",
        click: function () {
            searchAjax(searchName, pageNumber);
            $(":button").addClass("btn-info").removeClass("btn-success");
            $(this).removeClass("btn-info").addClass("btn-success");
        }
    }).appendTo("#pagination")
}