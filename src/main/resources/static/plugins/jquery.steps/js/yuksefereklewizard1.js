$(document).ready(function () {

    $("#yukmiktar").keyup(function (e) {
        // Our regex
        // a-z => allow all lowercase alphabets
        // A-Z => allow all uppercase alphabets
        // 0-9 => allow all numbers
        // @ => allow @ symbol
        var regex = /^[0-9]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Miktar Alanına Numerik Karakter Giriniz")
            // You can replace the invalid characters by:
            this.value = this.value.replace(/[^0-9]+/, '')
        }
        ;
    });
    $("#gvergy").keyup(function (e) {
        // Our regex
        // a-z => allow all lowercase alphabets
        // A-Z => allow all uppercase alphabets
        // 0-9 => allow all numbers
        // @ => allow @ symbol
        var regex = /^[0-9]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Vergi No Alanına Numerik Karakter Giriniz")
            // You can replace the invalid characters by:
            this.value = this.value.replace(/[^0-9]+/, '')
        }
        ;
    });
    $("#avergy").keyup(function (e) {
        // Our regex
        // a-z => allow all lowercase alphabets
        // A-Z => allow all uppercase alphabets
        // 0-9 => allow all numbers
        // @ => allow @ symbol
        var regex = /^[0-9]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Vergi No Alanına Numerik Karakter Giriniz")
            // You can replace the invalid characters by:
            this.value = this.value.replace(/[^0-9]+/, '')
        }
        ;
    });
    $("#tucr").keyup(function (e) {
        // Our regex
        // a-z => allow all lowercase alphabets
        // A-Z => allow all uppercase alphabets
        // 0-9 => allow all numbers
        // @ => allow @ symbol
        var regex = /^[0-9]*$/;
        // This is will test the value against the regex
        // Will return True if regex satisfied
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Ücret Alanına Numerik Karakter Giriniz")
            this.value = this.value.replace(/[^0-9]+/, '')
        }
        ;
    });
});
var yukFinishButton1;
let submitFormData = [];
!function ($) {
    "use strict";

    var FormWizard = function () {
    };
    var yukler = new Array();
    //creates form with validation
    FormWizard.prototype.createValidatorForm = function ($form_container) {

        jQuery.validator.addMethod('yuklemeulke', function (value, element) {
            if ($('#yulkilk').val() === "TR") {

                if (element.name == "seferBilgileriInputDTO[baslangicIlMernisKodu]") {
                    if ($("#selectCity").val()) {
                        return true
                    }

                    return false;
                } else if (element.name == "seferBilgileriInputDTO[baslangicIlceMernisKodu]") {
                    if ($("#selectCity").val() && $('#selectDistrict').val()) {
                        return true
                    }
                    return false;
                }
            } else {
                return true;
            }
            ;
        });

        jQuery.validator.addMethod('bosaltmaulke', function (value, element) {
            if ($('#bulkilk').val() === "TR") {

                if (element.name == "seferBilgileriInputDTO[bitisIlMernisKodu]") {
                    if ($("#selectCityBos").val()) {
                        return true
                    }
                    return false;
                } else if (element.name == "seferBilgileriInputDTO[bitisIlceMernisKodu]") {
                    if ($("#selectCityBos").val() && $('#selectDistrictBos').val()) {
                        return true
                    }
                    return false;
                }
            } else {
                return true;
            }
            ;
        });


        $form_container.validate({
            rules: {

                "seferBilgileriInputDTO[persnel]": {
                    required: true,
                },
                "seferBilgileriInputDTO[baslangicUlkeKodu]": {
                    required: true,
                },
                "seferBilgileriInputDTO[baslangicIlMernisKodu]": {
                    yuklemeulke: true,
                },
                "seferBilgileriInputDTO[baslangicIlceMernisKodu]": {
                    yuklemeulke: true,
                },
                "seferBilgileriInputDTO[aracId]": {
                    required: true,
                },
                "seferBilgileriInputDTO[seferBaslangicTarihi]": {
                    required: true,
                },
                "seferBilgileriInputDTO[seferBaslangicSaati]": {
                    required: true,
                },
                "seferBilgileriInputDTO[bitisUlkeKodu]": {
                    required: true,
                },
                "seferBilgileriInputDTO[bitisIlMernisKodu]": {
                    bosaltmaulke: true,
                },
                "seferBilgileriInputDTO[bitisIlceMernisKodu]": {
                    bosaltmaulke: true,
                },
                "seferBilgileriInputDTO[seferBitisTarihi]": {
                    required: true,
                },
                "seferBilgileriInputDTO[seferBitisSaati]": {
                    required: true,
                },
                "yukBilgileriInputDTO[tasimaSekliId]": {
                    required: true,
                },
                "yukBilgileriInputDTO[yukMiktari]": {
                    required: true,
                },
                "yukBilgileriInputDTO[gonderenVergiNo]": {
                    required: true,
                }

            },
            messages: {

                "seferBilgileriInputDTO[persnel]": {
                    required: "Şoför Alanını Seçiniz",
                },
                "seferBilgileriInputDTO[baslangicUlkeKodu]": {
                    required: "Yükleme Ülkesini Seçiniz",
                },
                "seferBilgileriInputDTO[baslangicIlMernisKodu]": {
                    yuklemeulke: "Başlangıç Ülkesi Türkiye Olan Seferler İçin Zorunlu Alandır",
                },
                "seferBilgileriInputDTO[baslangicIlceMernisKodu]": {
                    yuklemeulke: "Başlangıç Ülkesi Türkiye Olan Seferler İçin Zorunlu Alandır",
                },
                "seferBilgileriInputDTO[aracId]": {
                    required: "Taşıma Aracını Seçiniz"
                },
                "seferBilgileriInputDTO[seferBaslangicTarihi]": {
                    required: "Yükleme Tarihi Alanını Doldurunuz",
                },
                "seferBilgileriInputDTO[seferBaslangicSaati]": {
                    required: "Yükleme saati Alanını Doldurunuz",
                },
                "seferBilgileriInputDTO[bitisUlkeKodu]": {
                    required: "Boşaltma Ülkesini Seçiniz",
                },
                "seferBilgileriInputDTO[bitisIlMernisKodu]": {
                    bosaltmaulke: "Başlangıç Ülkesi Türkiye olan Seferler İçin Zorunlu Alan",
                },
                "seferBilgileriInputDTO[bitisIlceMernisKodu]": {
                    bosaltmaulke: "Başlangıç Ülkesi Türkiye olan Seferler İçin Zorunlu Alan",
                },
                "seferBilgileriInputDTO[seferBitisTarihi]": {
                    required: "Boşaltma Tarihi Alanını Doldurunuz"
                },
                "seferBilgileriInputDTO[seferBitisSaati]": {
                    required: "Boşaltma Saati Alanını Doldurunuz",
                },
                "yukBilgileriInputDTO[gonderenVergiNo]": {
                    gvergy: "Gönderici Vergi Numarası Giriniz.",
                    required: "Gönderici Vergi Numarası Giriniz.",
                },
            }

        });
        $form_container.children("div").steps({
            headerTag: "h3",
            bodyTag: "section",
            transitionEffect: "slideLeft",
            labels: {
                cancel: "Cancel",
                current: "current step:",
                pagination: "Pagination",
                finish: "Gönder",
                next: "İleri",
                previous: "Geri",
                loading: "Loading ..."
            },

            onStepChanging: function (event, currentIndex, newIndex) {
                //sayfa geri tuşu validasyona takılmaz
                if (currentIndex > newIndex) {
                    return true;
                }
                //sayfa 1 den 2 ye gecerken ki alan
                if (currentIndex == 0 && newIndex == 1) {

                    var basTarih = moment($("#autoclose-datepicker1").val() + " " + $("#timepicker1").val(), "DD-MM-YYYY HH:mm");
                    var bitisTarih = moment($("#autoclose-datepicker2").val() + " " + $("#timepicker2").val(), "DD-MM-YYYY HH:mm");
                    var isLarger = basTarih >= bitisTarih;
                    if (isLarger) {
                        date_msg("Boşaltma Tarihi Yükleme Tarihinden Önce Olamaz.")
                        return false
                    }
                    $form_container.validate().settings.ignore = ":disabled,:hidden";
                    return $form_container.valid();
                }


                console.log("currenIndex: ", currentIndex, "NexIndex: ", newIndex);
                //repeater olan 2.sayfamız için yazılan validation
                if (newIndex == 2) {
                    unTabloGenel = 0;
                    var t;
                    for (t = 0; t < unSayac.length; t++) {
                        if (unSayac[t] === 1) {
                            unTabloGenel += 1;
                        }
                    }
                    var x = document.getElementById("s_yukDetay2");
                    if (unTabloGenel > 0) {
                        x.style.display = "block";
                    } else {
                        x.style.display = "none";
                    }
                    submitFormData = [];
                    $('[name="esyaYukler"]').each(function () {
                        var satirId = this.id.replace("satir", "");
                        let s = satirId;
                        let tasimaSekli = $('#tsek' + satirId + ' option:selected').val();
                        let yukMiktari = $('#yukmiktar' + satirId).val();
                        let yukBirimi = $('#ybir' + satirId + ' option:selected').val();
                        let yukCinsId = $('#ytur' + satirId).val();
                        let gonderenUnvan = $('#gunvy' + satirId).val();
                        let gonderenVergiNo = $('#gvergy' + satirId).val();
                        let aliciUnvan = $('#aunvy' + satirId).val();
                        let aliciVergiNo = $('#avergy' + satirId).val();
                        let yuklemeTarihi = $('#autoclose-datepicker3' + satirId).val();
                        let yuklemeSaati = $('#timepicker3' + satirId).val();
                        let bosaltmaTarihi = $('#autoclose-datepicker4' + satirId).val();
                        let bosaltmaSaati = $('#timepicker4' + satirId).val();
                        let tasimaBedeli = $('#tucr' + satirId).val();
                        let yuklemeUlkeKodu = $('#yulk' + satirId + ' option:selected').val();
                        let yuklemeIlMernisKodu = $('#yukil' + satirId + ' option:selected').val();
                        let yuklemeIlceMernisKodu = $('#yukilce' + satirId + ' option:selected').val();
                        let bosaltmaUlkeKodu = $('#bulk' + satirId + ' option:selected').val();
                        let bosaltmaIlMernisKodu = $('#bosil' + satirId + ' option:selected').val();
                        let bosaltmaIlceMernisKodu = $('#bosilce' + satirId + ' option:selected').val();
                        let unNo = $('#unNo' + satirId + ' option:selected').val();
                        let unNosinif = $('#sinif' + satirId).val();
                        let unNotasimaKat = $('#tasimaKat' + satirId).val();
                        let unNopg = $('#pg' + satirId).val();
                        let unNoambAgir = $('#ambAgir' + satirId).val();
                        let unNoambSay = $('#ambSay' + satirId).val();
                        let tasimaBedeliParaBirimi = $('#pbir' + satirId + ' option:selected').val();

                        submitFormData.push({
                            tasimaSekli,
                            yukMiktari,
                            yukBirimi,
                            yukCinsId,
                            gonderenUnvan,
                            gonderenVergiNo,
                            aliciUnvan,
                            aliciVergiNo,
                            yuklemeTarihi,
                            yuklemeSaati,
                            bosaltmaTarihi,
                            bosaltmaSaati,
                            yuklemeUlkeKodu,
                            yuklemeIlMernisKodu,
                            yuklemeIlceMernisKodu,
                            bosaltmaUlkeKodu,
                            bosaltmaIlMernisKodu,
                            bosaltmaIlceMernisKodu,
                            unNo,
                            unNosinif,
                            unNopg,
                            unNotasimaKat,
                            unNoambAgir,
                            unNoambSay,
                            tasimaBedeli,
                            tasimaBedeliParaBirimi
                        });
                        console.log(submitFormData);
                    });
                    // gets all descendent of ancestor
                    var i, e, d;
                    var durum = true;
                    let total = submitFormData.length;
                    let basarili = 0;

                    let detay_ozet = "" +
                        " <table class=\"table table-striped- table-bordered table-checkable text-wrap\" style=\"max-width:100%\">\n" +
                        "                    <thead>\n" +
                        "                    <tr>\n" +
                        "                        <th>Yuk No #</th>\n" +
                        "                        <th>Yük</th>\n" +
                        "                        <th>Gönderici</th>\n" +
                        "                        <th>aliciUnvan</th>\n" +
                        "                        <th>Yükleme Yeri</th>\n" +
                        //"                        <th>Yükleme Tarihi</th>\n" +
                        "                        <th>Boşaltma Yeri</th>\n" +
                        //"                        <th>Boşaltma Tarihi</th>\n" +
                        "                        <th>Yük Taşıma Ücreti</th>\n" +
                        "                    </tr>\n" +
                        "                    </thead>" +
                        "                    <tbody>";
                    let detay_ozet2;
                    if (unTabloGenel > 0) {
                        detay_ozet2 = "" +
                            "<br>" +
                            " <div class=\"col-md-12\">\n" +
                            "  <h2 class='text-center'>Tehlikeli Maddeler</h2>\n" +
                            " </div>" +
                            " <table class=\"table table-striped- table-bordered table-checkable text-wrap\" style=\"max-width:100%\">\n" +
                            "                    <thead>\n" +
                            "                    <tr>\n" +
                            "                        <th>Yuk No #</th>\n" +
                            "                        <th>Yük</th>\n" +
                            "                    </tr>\n" +
                            "                    </thead>" +
                            "                    <tbody>";
                    }
                    for (i = 0; i < submitFormData.length; i++) {

                        let s = i + 1;
                        let tasimaSekliId = $('#tsek' + i + ' option:selected').val();
                        let tasimaSekli1 = $('#tsek' + i + ' option:selected').text();
                        let yukMiktari = $('#yukmiktar' + i).val();
                        let yukBirimi = $('#ybir' + i + ' option:selected').val();
                        let yukTuru = $('#ytur' + i + ' option:selected').val();
                        let gonderenUnvan = $('#gunvy' + i).val();
                        let gonderenVergiNo = $('#gvergy' + i).val();
                        let aliciUnvan = $('#aunvy' + i).val();
                        let aliciVergiNo = $('#avergy' + i).val();
                        let yuklemeTarihi = $('#autoclose-datepicker3' + i).val();
                        let yuklemeSaati = $('#timepicker3' + i).val();
                        let bosaltmaTarihi = $('#autoclose-datepicker4' + i).val();
                        let bosaltmaSaati = $('#timepicker4' + i).val();
                        let tasimaBedeli = $('#tucr' + i).val();
                        let yuklemeUlkeKodu = $('#yulk' + i + ' option:selected').text();
                        let yuklemeIlMernisKodu = $('#yukil' + i + ' option:selected').text();
                        let yuklemeIlceMernisKodu = $('#yukilce' + i + ' option:selected').text();
                        let bosaltmaUlkeKodu = $('#bulk' + i + ' option:selected').text();
                        let bosaltmaIlMernisKodu = $('#bosil' + i + ' option:selected').text();
                        let bosaltmaIlceMernisKodu = $('#bosilce' + i + ' option:selected').text();
                        let yukBelgesi = $('#unNo' + i + ' option:selected').text();
                        let unNo = $('#unNo' + i + ' option:selected').val();
                        let unNosinif = $('#sinif' + i).val();
                        let unNotasimaKat = $('#tasimaKat' + i).val();
                        let unNopg = $('#pg' + i).val();
                        let unNoambAgir = $('#ambAgir' + i).val();
                        let unNoambSay = $('#ambSay' + i).val();
                        let tasimaBedeliParaBirimi = $('#pbir' + i + ' option:selected').val();

                        var isLarger = moment(yuklemeTarihi + " " + yuklemeSaati, "DD-MM-YYYY HH:mm") >= moment(bosaltmaTarihi + " " + bosaltmaSaati, "DD-MM-YYYY HH:mm");
                        var basTarih = moment($("#autoclose-datepicker1").val() + " " + $("#timepicker1").val(), "DD-MM-YYYY HH:mm");
                        var bitisTarih = moment($("#autoclose-datepicker2").val() + " " + $("#timepicker2").val(), "DD-MM-YYYY HH:mm");
                        var isLargerTotal = moment(yuklemeTarihi + " " + yuklemeSaati, "DD-MM-YYYY HH:mm") >= moment(basTarih, "DD-MM-YYYY HH:mm");
                        var isLargerTotal2 = moment(bosaltmaTarihi + " " + bosaltmaSaati, "DD-MM-YYYY HH:mm") <= moment(bitisTarih, "DD-MM-YYYY HH:mm");
                        if (tasimaSekliId == "" || yukMiktari == "" || yukBirimi == "" || yukTuru == "") {
                            //alert fırlatıyor
                            swal({
                                title: s + ". Yük Eksik Bilgi Girişi",
                                text: "Yükler Bölümündeki Zorunlu Alanları Doldurunuz.",
                                icon: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            })
                            return false;
                            break;
                        } else if (gonderenUnvan == "" || aliciUnvan == "" || yuklemeTarihi == "" || yuklemeSaati == "" || bosaltmaTarihi == "" || bosaltmaSaati == "") {
                            //alert fırlatıyor
                            swal({
                                title: s + ". Yük Eksik Bilgi Girişi",
                                text: "Lütfen Detaylar Bölümündeki Zorunlu Alanları Doldurun.",
                                icon: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            })
                            return false;
                            break;
                            //ulkesi tr olanlar il ilçe secmek zorunda
                        } else if (yuklemeUlkeKodu == "" || (jQuery.trim(yuklemeUlkeKodu) == "Türkiye" && (yuklemeIlMernisKodu == null || yuklemeIlMernisKodu == "") && (yuklemeIlceMernisKodu == null || yuklemeIlceMernisKodu == ""))
                            || (jQuery.trim(yuklemeUlkeKodu) == "Türkiye" && (yuklemeIlMernisKodu != null || yuklemeIlMernisKodu == "") && (yuklemeIlceMernisKodu == null || yuklemeIlceMernisKodu == "")) ||
                            bosaltmaUlkeKodu == "" || (jQuery.trim(bosaltmaUlkeKodu) == "Türkiye" && (bosaltmaIlMernisKodu == null || bosaltmaIlMernisKodu == "") && (bosaltmaIlceMernisKodu == null || bosaltmaIlceMernisKodu == ""))
                            || bosaltmaUlkeKodu == "" || (jQuery.trim(bosaltmaUlkeKodu) == "Türkiye" && (bosaltmaIlMernisKodu != null || bosaltmaIlMernisKodu == "") && (bosaltmaIlceMernisKodu == null || bosaltmaIlceMernisKodu == ""))
                        ) {
                            swal({
                                title: s + ". Yük Eksik Bilgi Girişi",
                                text: "Lütfen Detaylar Bölümündeki Yükleme - Boşaltma Yer Bilgilerini Seçiniz.",
                                icon: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            });
                            return false;
                            break;
                        } else if (isLarger === true) {

                            swal({
                                title: s + ". Yük",
                                text: "Yükleme Tarihi Boşaltma Tarihinden Önce Olmalıdır.",
                                type: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            });
                            return false;
                            break;
                        } else if (isLargerTotal === false) {

                            swal({
                                title: s + ". Yük",
                                text: "Yükleme Tarihi Sefer Başlangıç Tarihinden Sonra Olmalıdır.",
                                type: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            });
                            return false;
                            break;
                        } else if (isLargerTotal2 === false) {

                            swal({
                                title: s + ". Yük",
                                text: "Boşaltma Tarihi Sefer Bitiş Tarihinden Önce Olmalıdır.",
                                type: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            });
                            return false;
                            break;
                        }
                        //özet ekranlar burda dolduruldu..

                        if (yukTuru === "999") {
                            // submitFormData[i].yukCinsId  = "1";
                            if (unNo === "0" || unNoambSay === "" || unNoambAgir === "" || aliciVergiNo === "") {
                                swal({
                                    title: s + ". Yük Eksik Bilgi Girişi",
                                    text: "Lütfen Detaylar Bölümündeki Tehlikeli Madde Alanlarını Doldurunuz.",
                                    icon: "warning",
                                    confirmButtonClass: "btn btn-secondary"
                                })
                                return false;
                            }
                        }
                        if (aliciVergiNo === gonderenVergiNo){
                            swal({
                                title: s + ". Yük Hatalı Bilgi Girişi",
                                text: "Gönderici ve Alıcı Vergi Numarası Aynı Olamaz.",
                                icon: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            })
                            return false;
                        }else if (aliciUnvan === gonderenUnvan){
                            swal({
                                title: s + ". Yük Hatalı Bilgi Girişi",
                                text: "Gönderici ve Alıcı Firma Aynı Olamaz.",
                                icon: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            })
                            return false;
                        }

                        detay_ozet +=

                            "                         <tr>\n" +
                            "                         <th>" + s + " #</th>\n" +
                            "                         <th>" + tasimaSekli1 + " - " + yukMiktari + " " + yukBirimi + "</th>\n" +
                            "                         <th style='white-space:normal; text-overflow:ellipsis; max-width:500px;'>" + gonderenUnvan + " - " + gonderenVergiNo + "</th>\n" +
                            "                         <th style='white-space:pre-line; text-overflow:ellipsis; max-width:500px;'>" + aliciUnvan + " - " + aliciVergiNo + "</th>\n" +
                            "                         <th>(" + yuklemeUlkeKodu + ") - " + yuklemeIlMernisKodu + "</th>\n" +
                            //"                       <th>" + yuklemeTarihi + " " + yuklemeSaati + "</th>\n" +
                            "                         <th>(" + bosaltmaUlkeKodu + ") - " + bosaltmaIlMernisKodu + "</th>\n" +
                            //"                       <th>" + bosaltmaTarihi + " " + bosaltmaSaati + "</th>\n" +
                            "                         <th>" + tasimaBedeli + " " + tasimaBedeliParaBirimi + "</th>\n" +
                            "                         </tr>";
                        if (unTabloGenel > 0) {
                            detay_ozet2 +=
                                "                         <tr>\n" +
                                "                         <th>" + s + " #</th>\n" +
                                "                         <th>" + yukBelgesi + "</th>\n" +
                                "                         </tr>";
                        }
                    }
                    if (unTabloGenel > 0) {
                        detay_ozet2 += "</tbody>";
                        detay_ozet2 += "</table>";
                        $("#s_yukDetay2").html(detay_ozet2);
                    }
                    detay_ozet += "</tbody>";
                    detay_ozet += "</table>";
                    $("#s_yukDetay").html(detay_ozet);


                    var ozet_table = "" +
                        "<table class=\'table table-striped- table-bordered table-checkable text-wrap\' style=\"max-width:100%\">\n";
                    if (abcd < 4) {
                        ozet_table += "" +
                            "  <tr>\n" +
                            "    <td style='width: 200px'>Şoför:</td>\n" +
                            "    <td>\n" + $("#yukSofor option:selected").text() + "</td>\n";
                    } else {
                        ozet_table += "" +
                            "  <tr>\n" +
                            "    <td style='width: 200px'>Şoförler:</td>\n" +
                            "    <td>\n" + $("#yukSofor option:selected").text() + " - " + $("#yedekSofor option:selected").text() + "</td>\n";
                    }
                    if (efgh < 4) {
                        ozet_table += "" +
                            "    <td style='width: 200px'>Araç:</td>\n" +
                            "    <td>" + $("#tarac option:selected").text() + "</td>\n" +
                            "  </tr>\n";
                    } else {
                        ozet_table += "" +
                            "    <td style='width: 200px'>Araç/Dorse:</td>\n" +
                            "    <td>" + $("#tarac option:selected").text() + " - " + $("#dorse option:selected").text() + "</td>\n" +
                            "  </tr>\n";
                    }
                    ozet_table += "" +
                        "  <tr>\n" +
                        "    <td style='width: 200px'>Sefer Başlangıç:</td>\n" +
                        "    <td>" + $("#selectCity option:selected").text() + " / " + $("#selectDistrict option:selected").text() + "</td>\n" +
                        "    <td>(" + $("#yulkilk option:selected").text() + ")</td>\n" +
                        "    <td>" + $("#autoclose-datepicker1").val() + " " + $("#timepicker1").val() + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td style='width: 200px'>Sefer Bitiş:</td>\n" +
                        "    <td>" + $("#selectCityBos option:selected").text() + " / " + $("#selectDistrictBos option:selected").text() + "</td>\n" +
                        "    <td>(" + $("#bulkilk option:selected").text() + ")</td>\n" +
                        "    <td>" + $("#autoclose-datepicker2").val() + " " + $("#timepicker2").val() + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>Açıklama:</td>\n" +
                        "    <td style='white-space:pre-line; text-overflow:ellipsis; width:450px;'>" + $("#not").val() + "</td>\n" +
                        "  </tr>\n" +
                        "</table>";
                    $("#s_sefer").html(ozet_table);
                }
                //$form_container.validate().settings.ignore = ":disabled,:hidden";
                return true;
            }
            ,
            onFinishing: function (event, currentIndex) {
                //$form_container.validate().settings.ignore = ":disabled";
                //return $form_container.valid();
                return true;
            }
            ,
            onFinished: function (event, currentIndex) {
                var buttonList = [];
                var list = document.getElementById("tt");
                buttonList = list.getElementsByTagName("a");
                console.log(buttonList);
                for (let i = 0; i < buttonList.length; i++) {
                    console.log(buttonList[i].innerHTML)
                    if (buttonList[i].innerHTML === "Gönder") {
                        yukFinishButton1 = buttonList[i];
                    }
                }
                console.log(yukFinishButton1);
                yukFinishButton1.style.visibility = "hidden";
                //en son submit edilen kısım
                var postData = {
                    "seferBilgileriInputDTO": {
                        "persnel": $("#yukSofor").val(),
                        "personel2": $("#yedekSofor").val(),
                        "aracId": $("#tarac").val(),
                        "aracId2": $("#dorse").val(),
                        "baslangicUlkeKodu": $("#yulkilk").val(),
                        "baslangicIlMernisKodu": $("#selectCity").val(),
                        "baslangicIlceMernisKodu": $("#selectDistrict").val(),
                        "seferBaslangicTarihi": $("#autoclose-datepicker1").val(),
                        "seferBaslangicSaati": $("#timepicker1").val(),
                        "bitisUlkeKodu": $("#bulkilk").val(),
                        "bitisIlMernisKodu": $("#selectCityBos").val(),
                        "bitisIlceMernisKodu": $("#selectDistrictBos").val(),
                        "seferBitisTarihi": $("#autoclose-datepicker2").val(),
                        "seferBitisSaati": $("#timepicker2").val(),
                        "seferAciklama": $("#not").val(),
                    },
                    "yukBilgileriInputDTO": submitFormData,
                };

                if (unTabloGenel > 0) {
                    modalgoster2();
                } else {
                    $.ajax({
                        type: "POST",
                        url: "/yuk/bildirim",
                        data: JSON.stringify(postData),
                        contentType: "application/json",
                        datatype: 'json',
                        cache: false,
                        timeout: 600000,
                        success: function (data) {
                            $('#loading-screen').fadeOut('slow');
                            console.log(data);
                            if (data.sonucKodu === 0) {
                                success_noti_custom("Seferiniz Ulaştırma ve Altyapı Bakanlığı'na Başarılı Şekilde Bildirilmiştir.");
                                for (var i = 0; i < data.yukler.length; i++) {
                                    if (data.yukler[i].sonucKodu === 0) {
                                        error_noti_yuk(i + 1 + ". " + data.yukler[i].sonucMesaji)
                                    }
                                }
                                setTimeout(function () {
                                    window.location.replace("/yuk/yukler");
                                }, 2000);
                            } else {
                                // var myJSON = JSON.stringify(data.sonucMesaji)
                                error_noti_yuk(data.sonucMesaji)
                                console.log(data.sonucMesaji + " Başarısız işlem");
                                setTimeout(function () {
                                    window.location.replace("/yuk/yukler");
                                }, 2000);
                            }

                        },
                        error: function (e) {
                            console.log("ERROR : ", e);
                            yukFinishButton1.style.visibility = "visible";
                        }
                    });
                }
                $('#bildirYazdirma').click(function () {
                    $.ajax({
                        type: "POST",
                        url: "/yuk/bildirim",
                        data: JSON.stringify(postData),
                        contentType: "application/json",
                        datatype: 'json',
                        cache: false,
                        timeout: 600000,
                        success: function (data) {
                            $('#loading-screen').fadeOut('slow');
                            console.log(data);
                            if (data.sonucKodu === 0) {
                                success_noti_custom("Seferiniz Ulaştırma ve Altyapı Bakanlığı'na Başarılı Şekilde Bildirilmiştir.");
                                for (var i = 0; i < data.yukler.length; i++) {
                                    if (data.yukler[i].sonucKodu === 0) {
                                        error_noti_yuk(i + 1 + ". " + data.yukler[i].sonucMesaji)
                                    }
                                }
                                setTimeout(function () {
                                    window.location.replace("/yuk/yukler");
                                }, 2000);
                            } else {
                                // var myJSON = JSON.stringify(data.sonucMesaji)
                                error_noti_yuk(data.sonucMesaji)
                                console.log(data.sonucMesaji + " Başarısız işlem");
                                setTimeout(function () {
                                    window.location.replace("/yuk/yukler");
                                }, 2000);
                            }

                        },

                        error: function (e) {
                            console.log("ERROR : ", e);
                            yukFinishButton1.style.visibility = "visible";
                        }
                    });
                });
                $('#bildirYazdir').click(function () {
                    $.ajax({
                        type: "POST",
                        url: "/yuk/bildirim",
                        data: JSON.stringify(postData),
                        contentType: "application/json",
                        datatype: 'json',
                        cache: false,
                        timeout: 600000,
                        success: function (data) {
                            $('#loading-screen').fadeOut('slow');
                            console.log(data);
                            if (data.sonucKodu === 0) {
                                success_noti_custom("Seferiniz Ulaştırma ve Altyapı Bakanlığı'na Başarılı Şekilde Bildirilmiştir.");
                                for (var i = 0; i < data.yukler.length; i++) {
                                    if (data.yukler[i].sonucKodu === 0) {
                                        error_noti_yuk(i + 1 + ". " + data.yukler[i].sonucMesaji)
                                    }
                                }
                                setTimeout(function () {
                                    window.location.replace("/yuk/yukler");
                                }, 2000);
                            } else {
                                // var myJSON = JSON.stringify(data.sonucMesaji)
                                error_noti_yuk(data.sonucMesaji)
                                console.log(data.sonucMesaji + " Başarısız işlem");
                                setTimeout(function () {
                                    window.location.replace("/yuk/yukler");
                                }, 2000);
                            }

                        },
                        error: function (e) {
                            console.log("ERROR : ", e);
                            yukFinishButton1.style.visibility = "visible";
                        }
                    });
                });

                return false; // required to block normal submit since you used ajax

            }
        });

        return $form_container;
    },
        //creates vertical form
        FormWizard.prototype.init = function () {
            //initialzing various forms

            //form with validation
            this.createValidatorForm($("#wizard-validation-form"));
        },
        //init
        $.FormWizard = new FormWizard, $.FormWizard.Constructor = FormWizard
}(window.jQuery),

//initializing 
    function ($) {
        "use strict";
        $.FormWizard.init()
    }(window.jQuery);

