function submitForm(e) {
    e.preventDefault();

    var valid = true;
    var dacha = {};
    dacha.firstname = $.trim($("#firstname").val());
    dacha.lastname = $.trim($("#lastname").val());
    dacha.patronymic = $.trim($("#patronymic").val());
    dacha.district = $.trim($("#district").val());
    dacha.address = $.trim($("#address").val());

    for (var key in dacha) {
        if (dacha[key] === "" && key !== "patronymic") {
            $("#" + key).addClass("is-invalid");
            valid = false;
        }
    }

    if (valid) {
        for (var key in dacha) {
            $("#" + key).removeClass("is-invalid");
        }

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/dacha_check/",
            data: JSON.stringify(dacha),
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
                        dachaAddr = data[i].docDachaAddrs;
                        address = "<ul class=\"list-group\">";
                        for (var j = 0; j < dachaAddr.length; j++) {
                            address += "<li class=\"list-group-item\">" +
                                (dachaAddr[j].district == null ? "" : (dachaAddr[j].district + ", ")) +
                                (dachaAddr[j].address == null ? "" : dachaAddr[j].address) +
                                "</li>";
                        }
                        address += "</ul>";
                        var html =
                            "<tr>" +
                            "<td class=\"text-center\">" + (data[i].lastname == null ? "" : data[i].lastname) + " " + (data[i].firstname == null ? "" : data[i].firstname) + " " + (data[i].patronymic == null ? "" : data[i].patronymic) + "</td>" +
                            "<td class=\"text-center\">" + ((data[i].raion == null ? "" : (data[i].raion + ', ')) +(data[i].naspunkt == null ? "" : data[i].naspunkt)) + "</td>" +
                            "<td class=\"text-center\">" + (address) + "</td>" +
                            // "<td class=\"text-center\">" + (data[i].docDachaAddrs.address == null ? "" : data[i].docDachaAddrs.address) + "</td>" +
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