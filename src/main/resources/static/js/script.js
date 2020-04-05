
function submitForm(e) {
    e.preventDefault();

    var organization = {};
    organization.firstname = $("#firstname").val();
    organization.lastname = $("#lastname").val();
    organization.patronymic = $("#patronymic").val();
    organization.inn = $("#inn").val();

    var valid = true;

    for (var key in organization) {
        if (organization[key] === "" && key !== "patronymic") {
            $("#" + key).addClass("is-invalid");
            valid = false;
        }
    }

    if (valid) {
        for (var key in organization) {
            $("#" + key).removeClass("is-invalid");
        }

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/",
            data: JSON.stringify(organization),
            success: function (data) {
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
                            "<td class=\"text-center\">" + data[i].organizationName + "</td>" +
                            "</tr>";
                        $("#orgTable").append(html);
                    }
                }
            }
        });
    }
}

$("#submit").on("mousedown", function (e) {
    e.preventDefault();
});

$("#submit").on("click", submitForm);