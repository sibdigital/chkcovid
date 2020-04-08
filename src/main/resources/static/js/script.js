
function submitForm(e) {
    e.preventDefault();

    var valid = true;
    var person = {};
    person.firstname = $.trim($("#firstname").val());
    person.lastname = $.trim($("#lastname").val());
    person.patronymic = $.trim($("#patronymic").val());
    person.inn = $.trim($("#inn").val());

    for (var key in person) {
        if (person[key] === "" && key !== "patronymic") {
            $("#" + key).addClass("is-invalid");
            valid = false;
        }
    }

    if (valid) {
        for (var key in person) {
            $("#" + key).removeClass("is-invalid");
        }

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/",
            data: JSON.stringify(person),
            success: function (data) {
                console.log(data);
                $("#orgTable tbody").empty();

                if (data.length === 0) {
                    $("#noResults").attr("style", "display:block")
                } else {
                    $("#noResults").attr("style", "display:none")

                    for (var i = 0; i < data.length; i++) {
                        var html =
                            "<tr>" +
                            "<td class=\"text-center\">" + data[i].lastname + " " + data[i].firstname + " " + data[i].patronymic + "</td>" +
                            "<td class=\"text-center\">" + data[i].inn + "</td>" +
                            "<td class=\"text-center\">" + data[i].shortName + "</td>" +
                            "</tr>";
                        $("#orgTable").append(html);
                    }
                }
            },
            error: function (data) {
                console.log(data);
            }

        });
    }
}

$("#submit").on("mousedown", function (e) {
    e.preventDefault();
});

$("#submit").on("click", submitForm);