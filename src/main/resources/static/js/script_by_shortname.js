function submitForm(e) {
    e.preventDefault();

    var valid = true;
    var org = {};
    org.inn = $.trim($("#inn_org").val());

    if (org["inn"] === ""){
        $("#inn_org").addClass("is-invalid");
        valid = false;
    }

    if (valid) {
        $("#inn_org").removeClass("is-invalid");

        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/org_check/",
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
                        var options = {
                            year: 'numeric',
                            month: 'numeric',
                            day: 'numeric',
                        };
                        var dateCreate = data[i].timeCreate ? new Date(data[i].timeCreate) : "";
                        var dateReview = data[i].timeReview ? new Date(data[i].timeReview) : "";
                        var status = "";
                        var created = data[i].timeCreate ? dateCreate.toLocaleString("ru", options) : "";
                        var reviewed = "";
                        if (data[i].statusReview === 0){
                            status ="На рассмотрении";
                            reviewed = "-"
                        }
                        else{
                            status = "Решение отправлено на эл. почту заявителя";
                            reviewed = data[i].timeReview ? dateReview.toLocaleString("ru", options) : "";
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