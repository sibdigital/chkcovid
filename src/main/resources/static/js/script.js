
function submitForm(e) {
    e.preventDefault();

    var organization = {};
    organization.firstname = $("#firstname").val();
    organization.lastname = $("#lastname").val();
    organization.patronymic = $("#patronymic").val();
    organization.inn = $("#inn").val();

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
                        "<td>" + data[i].lastname + " " + data[i].firstname + " " + data[i].patronymic + "</td>" +
                        "<td>" + data[i].inn + "</td>" +
                        "<td>" + data[i].organizationName + "</td>" +
                        "</tr>";
                    $("#orgTable").append(html);
                }
            }
        }
    });

}

$("#submit").on("mousedown", function (e) {
    e.preventDefault();
});
$("#submit").on("click", submitForm);