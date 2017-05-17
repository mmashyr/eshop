/**
 * Created by Mark on 15.03.2017.
 */
$(document).ready(function () {
    populateProductsDiv();
    $('.showAllButton').on('click', function(event) {
        populateProductsDiv();
        $('#categorycheckboxes').find('input[type=checkbox]:checked').prop('checked', false);
        event.preventDefault();
    });
});

function populateProductsDiv(){
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function (e, xhr) {
        xhr.setRequestHeader(header, token);
    });
    $.ajax({
        url: "service/product/maximumpages",

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
}



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
    var url = "service/product/page/" + pageNumber;
    $.ajax({
        url: url,
        data: {
            pageNumber: pageNumber
        },
        dataType: 'json',
        success: function (data) {
            drawpage(data)
        }
    })
}
