function processRequest(url, data, targetId) {
    var promodomain = getDomainName('promoadmin');
    jQuery.ajax({
        type: "POST",
        url: promodomain + url,
        data: data,
        headers: {
            Accept: "application/json"
        },
        contentType: "application/json; charset=utf-8",
        success: function (result) {
            if (result != null) {
            	$("#" + targetId).empty().append(result);
            } else if (result.status == "FAILURE") {
                $('#errorMessage').text("error in emailID updating");
            }
        }
    });
}

function submitForm(url, formId, targetId) {
	var formData = {};
	if('NO-DATA' != formId){
		formData = JSON.stringify($("#" + formId).serializeObject());
	}
    processRequest(url, formData, targetId);
}

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

function getDomainName(requiredDomain) {
    return 'http://localhost:8080';
    /*$.getJSON("/properties/properties.json", function(data) {
		properties = data;
	});
	if(requiredDomain == 'promoadmin'){
		return properties.handlebarsdomain['loaclhost'];
	}else if(requiredDomain == 'handlebars'){
	} */
}
