/**
 * Created by Mark on 14.03.2017.
 */
function drawpage(data) {
    $("#products").empty();
    $.each(data, function (key, value) {
        var name = document.createElement('h3');
        var price = document.createElement('h4');
        var link = document.createElement('a');
        $(name).text(value["name"]);
        $(price).text(value["price"]);
        $(link).attr({
            'href': 'product/' + value["id"],
            'role': "button"
        }).addClass('btn btn-info').text("Details");

        $("<div />", {
            class: "col-md-3",
            style: "min-height: 450px; border: 1px solid floralwhite"
        }).prepend(
            $("<img />", {
                class: "img-thumbnail img-responsive",
                style: "max-height: 280px",
                src: value["imageURL"]
            }), name, price, link
        )
            .appendTo("#products")
    });
}

function noresults() {
    $("#products").empty();
    $("#pagination").empty();
    $("<h2 />", {
        class: "text-center",
        text: "No results"
    })
        .appendTo("#products")
}


