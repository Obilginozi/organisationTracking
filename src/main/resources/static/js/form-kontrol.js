var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    var ulke = document.getElementById(ulke);
};

$(document).ready(function () {
    $("#adi").keyup(function(e) {
        var regex = /^[a-zA-Z ğüşıöçĞÜŞİÖÇ]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true)
        //alert if not true
            alert("ad Alanına alfabetik Karakter Giriniz");

        // You can replace the invalid characters by:
        this.value = this.value.replace(/[^a-zA-Z ğüşıöçĞÜŞİÖÇ]+/, '');
    });
    $("#soyAdi").keyup(function(e) {

        var regex = /^[a-zA-Z ğüşıöçĞÜŞİÖÇ]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true)
        //alert if not true
            alert("Soyad Alanına alfabetik Karakter Giriniz");

        // You can replace the invalid characters by:
        this.value = this.value.replace(/[^a-zA-Z ğüşıöçĞÜŞİÖÇ]+/, '');
    });
    $("#tcPasaport").keyup(function(e) {
        // Our regex
        // a-z => allow all lowercase alphabets
        // A-Z => allow all uppercase alphabets
        // 0-9 => allow all numbers
        // @ => allow @ symbol
        var regex = /^[0-9]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true)
        //alert if not true
            alert("TCKN Alanına Numerik Karakter Giriniz");
        // You can replace the invalid characters by:
        this.value = this.value.replace(/[^0-9]+/, '');
    });
    $("#telefon").keyup(function(e) {
        // Our regex
        // a-z => allow all lowercase alphabets
        // A-Z => allow all uppercase alphabets
        // 0-9 => allow all numbers
        // @ => allow @ symbol
        var regex = /^[0-9]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true)
        //alert if not true
            alert("TCKN Alanına Numerik Karakter Giriniz");
        // You can replace the invalid characters by:
        this.value = this.value.replace(/[^0-9]+/, '');
    });


    $("#selectCity").change(function() {
        var cityId = this.value;
        console.log(cityId)
        $.ajax({
            url: "/getdistrictByCityId/"+cityId,
            type: "get",
            success: function (response) {

                $('#loading-screen').fadeOut('slow');
                $('#selectDistrict').find('option').remove().end().append('<option disabled selected> ----- İlçe Seçiniz ----- </option>').val('');

                var i;
                for (i = 0; i < response.length; ++i) {
                    $('#selectDistrict').append(new Option(response[i].district, response[i].id));
                }

                $("#selectDistrict").val($("#selectDistrict option:first").val());

            },
            error: function(jqXHR, textStatus, errorThrown) {

            }
        });
    });



    jQuery.validator.addMethod('ad_rule', function (value, element) {
        if (/^[a-zA-ZğüşıöçĞÜŞİÖÇ,]+(\s{0,1}[a-zA-Z-ğüşıöçĞÜŞİÖÇ, ])*$/.test(value)) {
            return true;
        } else {
            return false;
        };
    });
    jQuery.validator.addMethod('numerik', function (value, element) {
        if (/^[0-9]*$/.test(value)) {
            return true;
        } else {
            return false;
        };
    });
    $("#FormSave").validate({
        ignore: ":hidden",
        rules: {
            ad: {
                required: true,
                minlength: 2,
                ad_rule:true
            },
            soyad: {
                required: true,
                minlength: 2,
                ad_rule:true
            },
            tc: {
                required: true,
                minlength: 11,
                maxlength: 11,
                numerik:true,
            },
            email:{
                minlength: 3,
                maxlength: 30,


            },
            telefon: {
                required: true,
                minlength: 10,
                maxlength: 10,
                numerik:true
            },
            adres:"required",
            cinsiyet: "required",
            pTur:"required",
            selectCity:"required",
            selectDistrict:"required",
            dogumT:"required",
            uyruk:"required",
        },
        messages: {
            ad: {
                required: "Ad Alanını Doldurunuz",
                minlength: "En Az 2 Karakter",
                ad_rule:"Sadece Alfabetik Karakter Giriniz"
            },
            soyad: {
                required: "Soyad Alanını Doldurunuz",
                minlength: "En Az 2 Karakter",
                ad_rule:"Sadece Alfabetik Karakter Giriniz"
            },
            tc: {
                required: "Tc Alanını Doldurunuz",
                minlength: "11 Hane Gerekli",
                maxLength: "11 Hane gerekli",
                numerik: "Sadece Numara Giriniz"
            },
            telefon:{
                required: "Telefon Alanını Doldurunuz",
                minlength: "Telefon Numarası En Az 10 Haneli Girilmelidir",
                maxlength: "Telefon Numarası 10 Haneyi Geçemez",
                numerik: "Sadece Numara Giriniz"
            },
            pTur: "Personel Turu Seçiniz",
            uyruk: "Uyruk Seçiniz",
            cinsiyet:"Cinsiyet Seçiniz",
            adres:"Adres Alanını Doldurunuz",
            email: "Lütfen Geçerli Bir Mail Adresi Giriniz",
            selectCity:"İl Alanını Doldurunuz",
            selectDistrict: "İlçe Alanını Doldurunuz",
            dogumT: "Doğum Tarihi Alanını Doldurunuz"
        },
        //
        submitHandler: function (form) {
            if (personyas > 18){
            $('#loading-screen').fadeIn();
            var id = getUrlParameter('id');
            if(id==null){
                var formData = {
                    "name": $("#adi").val(),
                    "surname" : $("#soyAdi").val(),
                    "tcPass":$("#tcKimlikPasaportNo").val(),
                    "email":$("#email").val(),
                    "hesCode":$("#hesCode").val(),
                    "phone":$("#telefon").val(),
                    "address":$("#adres").val(),
                    "sex":$("#cinsiyet").val(),
                    "bt":$("#autoclose-datepicker").val(),
                    "postalCode":$("#posta").val(),
                    "status":$('input[name="durum"]:checked').val(),
                    "staffType":{
                        "id":$("#persontur").val(),
                        "staffType":$('#persontur option:selected:enabled').html()
                    },
                    "country":{
                        "id":$("#ulke").val(),
                        "country":$('#ulke option:selected').html()
                    },
                    "district":
                        {
                            "id": $("#selectDistrict").val(),
                            "district": $('#selectDistrict option:selected').html(),
                            "city": {
                                "id": $("#selectCity").val(),
                                "city": $('#selectCity option:selected').html()
                            }
                        }
                }

            }
           else {
                var formData = {
                    "id":id,
                    "name": $("#adi").val(),
                    "surname" : $("#soyAdi").val(),
                    "tcPass":$("#tcKimlikPasaportNo").val(),
                    "email":$("#email").val(),
                    "hesCode":$("#hesKodu").val(),
                    "phone":$("#telefon").val(),
                    "address":$("#adres").val(),
                    "sex":$("#cinsiyet").val(),
                    "bt":$("#autoclose-datepicker2").val(),
                    "postalCode":$("#posta").val(),
                    "status":$('input[name="durum"]:checked').val(),
                    "staffType":{
                        "id":$("#persontur").val(),
                        "staffType":$('#persontur option:selected:enabled').html()
                    },
                    "country":{
                        "id":$("#ulke").val(),
                        "country":$(" #ulke option:selected").html()
                    },
                    "district":
                        {
                            "id": $("#selectDistrict").val(),

                            "district": $('#selectDistrict option:selected').html(),
                            "city": {
                                "id": $("#selectCity").val(),
                                "city": $('#selectCity option:selected').html()
                            }
                        }
                }
            }
            console.log(formData);
           $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/staff/staffekle",
                data: JSON.stringify(formData),
                dataType: 'json',
                cache: false,
                timeout: 60000,

                success: function (data) {
                    $('#loading-screen').fadeOut('slow');
                    console.log(data);
                    if(data.result){
                        success_noti_custom(data.message);
                        setTimeout(function() {
                            window.location.replace("/staff/staffs");
                        }, 2000);
                    }
                    else{
                        error_noti_yuk(data.message);
                        console.log(data.message+" HATA")
                    }
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                    $("#btn-login").prop("disabled", false);
                }
            });
            return false;
            }else {
                inf_msg(msg= "Personel Yaşı 18'den Büyük olmalıdır.");
            }
        }
    });

});