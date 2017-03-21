/**
 * Created by Mark on 15.03.2017.
 */
$(document).ready(function () {
    $(function () {
        var token = $("input[name='_csrf']").val();
        var header = "X-CSRF-TOKEN";
        $(document).ajaxSend(function (e, xhr) {
            xhr.setRequestHeader(header, token);
        });
    });
    var frm = $("#categorycheckboxes");
    frm.submit(function (e) {
        var selected = [];
        $('.producer:checked').each(function () {
            selected.push($(this).val());
        });
        $.ajax({
            url: "numberofresultsbycategories",
            type: frm.attr('method'),
            data: {"producer": selected},
            dataType: 'json',
            statusCode: {
                204: function () {

                    noresults();
                },
                200: function (numberOfPages) {
                    searchAjaxCategories(selected, 1);
                    drawPaginationCategories(selected, numberOfPages)
                }
            }
        });
        e.preventDefault();
    })
});

function drawPaginationCategories(selectedCategories, numberOfPages) {
    $("#pagination").empty();
    for (var pageNumber = 1; pageNumber <= numberOfPages; pageNumber++) {
        drawPaginationButtonCategories(selectedCategories, pageNumber);
    }
    $(".pagination-button:first").removeClass("btn-info").addClass("btn-success");
}

function drawPaginationButtonCategories(selectedCategories, pageNumber) {
    $("<button/>", {
        text: pageNumber,
        type: "button",
        class: "btn btn-info pagination-button",
        click: function () {
            searchAjaxCategories(selectedCategories, pageNumber);
            $(":button").addClass("btn-info").removeClass("btn-success");
            $(this).removeClass("btn-info").addClass("btn-success");
        }
    }).appendTo("#pagination")
}

function searchAjaxCategories(selectedCategories, pageNumber) {
    $.ajax({
        url: "bycategories",
        type: "POST",
        data: {
            producer: selectedCategories,
            pageNumber: pageNumber
        },
        dataType: 'json',
        success: function (data) {
            drawpage(data)
        }

    })
}