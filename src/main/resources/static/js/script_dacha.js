function submitForm(e) {
    e.preventDefault();

    var valid = true;
    var dacha = {};
    dacha.firstname = $.trim($("#firstname").val());
    dacha.lastname = $.trim($("#lastname").val());
    dacha.patronymic = $.trim($("#patronymic").val());
    //dacha.district = $.trim($("#district").val());
    //dacha.address = $.trim($("#address").val());

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
            url: "check_dacha",
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
                        persons = data[i].docDachaPersons;
                        var validDate = new Date(data[i].validDate);
                        var options = {
                            year: 'numeric',
                            month: 'long',
                            day: 'numeric',
                        };
                        var req = "<tr>"
                                +"<td style='font-weight:bold;background-color:#dae8fc;color:#010066;text-align:center;vertical-align:middle'" +
                                "rowspan=\"2\">" + validDate.toLocaleString("ru", options)  + "</td>"
                                +"<td style='font-weight:bold;text-align:center;vertical-align:middle' colspan=\"2\"> Выехал: " + data[i].raion + ' '+ data[i].naspunkt + "</td>"
                            +"</tr>"
                            +"<tr>"
                            +"<td style='font-weight:bold;text-align:center;vertical-align:middle' colspan=\"2\">Следует: " + data[i].district + ' '+ data[i].address + "</td>"
                            +"</tr>";
                        pers = "";
                        for (var j = 0; j < persons.length; j++) {
                            var fio = (persons[j].lastname == null ? "" : persons[j].lastname) + " "
                                    + (persons[j].firstname == null ? "" : persons[j].firstname) + " "
                                    + (persons[j].patronymic == null ? "" : persons[j].patronymic);
                            pers += "<tr>\n" +
                                "    <td style='text-align:center;vertical-align:middle'>" + " " + (j + 1) + "</td>\n" +
                                "    <td style='text-align:center;vertical-align:middle' colspan=\"3\">" + fio + "</td>\n" +
                                "  </tr>";
                        }
                        var html =  req + pers;
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