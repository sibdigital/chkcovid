function submitForm(e) {
    e.preventDefault();

    var valid = true;
    var person = {};
    person.firstname = $.trim($("#firstname").val());
    person.lastname = $.trim($("#lastname").val());
    person.patronymic = $.trim($("#patronymic").val());
    person.inn = $.trim($("#inn").val());
    let innLength = person.inn.length;

    if (person["inn"] === "" || (innLength !== 10 && innLength !== 12)){
        $("#inn").addClass("is-invalid");
        valid = false;
    }

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
            beforeSend: function () {
                $("#orgTable tbody").empty();
                $("#spinner").attr("style", "display:block");
                $("#noResults").attr("style", "display:none");
            },
            success: function (data) {
                $("#spinner").attr("style", "display:none");
                $("#noResults").attr("style", "display:none");

                if (data.length === 0) {
                    $("#noResults").attr("style", "display:block");
                } else {
                    for (var i = 0; i < data.length; i++) {
                        var html =
                            "<tr>" +
                            "<td class=\"text-center\">" + (data[i].lastname == null ? "" : data[i].lastname) + " " + (data[i].firstname == null ? "" : data[i].firstname) + " " + (data[i].patronymic == null ? "" : data[i].patronymic) + "</td>" +
                            "<td class=\"text-center\">" + (data[i].inn == null ? "" : data[i].inn) + "</td>" +
                            "<td class=\"text-center\">" + (data[i].shortName == null ? "" : data[i].shortName) + "</td>" +
                            "</tr>";
                        $("#orgTable").append(html);
                    }
                }
            },
            error: function (data) {
            }

        });
    }
}

$("#submit").on("mousedown", function (e) {
    e.preventDefault();
});

$("#submit").on("click", submitForm);