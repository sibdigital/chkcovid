function submitForm(e) {
    e.preventDefault();

    var valid = true;
    var org = {};
    org.shortName = $.trim($("#shortname").val());
    org.inn = $.trim($("#inn_org").val());

    if (org["shortName"] === ""){
        $("#shortname").addClass("is-invalid");
        valid = false;
    }
    if (org["inn"] === ""){
        $("#inn_org").addClass("is-invalid");
        valid = false;
    }

    if (valid) {
        $("#shortname").removeClass("is-invalid");
        $("#inn_org").removeClass("is-invalid");

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/",
            data: JSON.stringify(org),
            beforeSend: function () {
                $("#orgShortnameTable tbody").empty();
                $("#spinnerShortname").attr("style", "display:block");
                $("#noResultsShortname").attr("style", "display:none");
            },
            success: function (data) {
                $("#spinnerShortname").attr("style", "display:none");
                $("#noResultsShortname").attr("style", "display:none");
                if (data.length === 0) {
                    $("#noResultsShortname").attr("style", "display:block")
                } else {
                    for (var i = 0; i < data.length; i++) {
                        var dateCreate = data[i].timeCreate === null ? "" : new Date(data[i].timeCreate);
                        var dateReview = data[i].timeReview === null ? "" : new Date(data[i].timeReview);
                        var status = "";
                        var created = (('0' + dateCreate.getDate()).slice(-2) + '.' + ('0' + (dateCreate.getMonth() + 1)).slice(-2) + '.' + dateCreate.getFullYear());
                        var reviewed = "";
                        if (data[i].statusReview === 0){
                            status ="Не обработано";
                            reviewed = "-"
                        }
                        else if (data[i].statusReview === 1){
                            status = "Принято";
                            reviewed =(('0' + dateReview.getDate()).slice(-2) + '.' + ('0' + (dateReview.getMonth() + 1)).slice(-2) + '.' + dateReview.getFullYear());
                        }
                        else{
                            status = "Отклонено по причине: " + (data[i].rejectComment === null ? "причина не указана" : data[i].rejectComment);
                            reviewed = (('0' + dateReview.getDate()).slice(-2) + '.' + ('0' + (dateReview.getMonth() + 1)).slice(-2) + '.' + dateReview.getFullYear());
                        }
                        var html =
                            "<tr>" +
                            "<td class=\"text-center\">" + (data[i].organization.shortName == null ? "" : data[i].organization.shortName) + "</td>" +
                            "<td class=\"text-center\">" + (data[i].organization.inn == null ? "" : data[i].organization.inn) + "</td>" +
                            "<td class=\"text-center\">" + (status) + "</td>" +
                            "<td class=\"text-center\">" + (created) + "</td>" +
                            "<td class=\"text-center\">" + (reviewed) + "</td>" +
                            "<td class=\"text-center\">" + (data[i].department.name==null ? "" : data[i].department.name) + "</td>" +
                            "</tr>";
                        $("#orgShortnameTable").append(html);
                    }
                }
            },
            error: function (data) {
            }

        });
    }
}

$("#submit_shortname").on("mousedown", function (e) {
    e.preventDefault();
});

$("#submit_shortname").on("click", submitForm);