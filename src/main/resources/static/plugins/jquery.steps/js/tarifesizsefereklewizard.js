$(document).ready(function () {
    $("#atel").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Telefon Alanına Numerik Karakter Giriniz");
        this.value = this.value.replace(/[^0-9]+/, '');
    });
    $("#grupucr").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Grup Ücreti Alanına Numerik Karakter Giriniz");
        this.value = this.value.replace(/[^0-9]+/, '');
    });
    $("#grupucrEdt").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Grup Ücreti Alanına Numerik Karakter Giriniz");
        this.value = this.value.replace(/[^0-9]+/, '');
    });
    $("#yolcuadiEdt").keyup(function (e) {
        var regex = /^[a-zA-Z ğüşıöçĞÜŞİÖÇ]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Ad Alanına alfabetik Karakter Giriniz");
        this.value = this.value.replace(/[^a-zA-Z ğüşıöçĞÜŞİÖÇ]+/, '');
    });
    $("#yolcusoyadiEdt").keyup(function (e) {

        var regex = /^[a-zA-Z ğüşıöçĞÜŞİÖÇ]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Soyad Alanına alfabetik Karakter Giriniz");
        this.value = this.value.replace(/[^a-zA-Z ğüşıöçĞÜŞİÖÇ]+/, '');
    });
    $("#yolcuuyrukEdt").keyup(function (e) {
        var regex = /^[a-zA-Z ğüşıöçĞÜŞİÖÇ]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Uyruk Alanına alfabetik Karakter Giriniz");
        this.value = this.value.replace(/[^a-zA-Z ğüşıöçĞÜŞİÖÇ]+/, '');
    });
    $("#yolcutcEdt").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Tc/Pass. Alanına Numerik Karakter Giriniz");
        this.value = this.value.replace(/[^0-9]+/, '');
    });
    $("#yolcutelEdt").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true)
            date_msg("Telefon Alanına Numerik Karakter Giriniz");
        this.value = this.value.replace(/[^0-9]+/, '');
    });
});
var tarifesizFinishButton1;
!function ($) {
    "use strict";

    var FormWizard = function () {
    };
    var yukler = new Array();
    //creates form with validation
    FormWizard.prototype.createValidatorForm = function ($form_container) {

        jQuery.validator.addMethod('baslangicUlke', function (value, element) {
            if ($('#yulkilk').val() === "TR") {

                if (element.name == "baslangicIl") {
                    if ($("#baslangicIl").val()) {
                        return true
                    }

                    return false;
                } else if (element.name == "baslangicIlce") {
                    if ($("#baslangicIl").val() && $('#baslangicIlce').val()) {
                        return true
                    }
                    return false;
                }
            } else {
                return true;
            }
            ;
        });

        jQuery.validator.addMethod('bitisUlke', function (value, element) {
            if ($('#bulkilk').val() === "TR") {

                if (element.name == "bitisIl") {
                    if ($("#bitisIl").val()) {
                        return true
                    }
                    return false;
                } else if (element.name == "bitisIlce") {
                    if ($("#bitisIl").val() && $('#bitisIlce').val()) {
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
                "grupAdi": {
                    required: true,
                },
                "grupUcret": {
                    required: true,
                },
                "baslangicUlke": {
                    required: true,
                },
                "baslangicIl": {
                    required: true,
                },
                "baslangicIlce": {
                    required: true,
                },
                "bitisUlke": {
                    required: true,
                },
                "bitisIl": {
                    required: true,
                },
                "bitisIlce": {
                    required: true,
                },
                "grupAciklama": {
                    required: true,
                },
                "adi": {
                    required: true,
                },
                "soyadi": {
                    required: true,
                },
                "uyrukUlke": {
                    required: true,
                },
                "tcKimlikPasaportNo": {
                    required: true,
                },
                "cinsiyet": {
                    required: true,
                },
                "telefonNo": {
                    required: true,
                },
                "koltukNo": {
                    required: true,
                },
            },
            messages: {
                "grupAdi": {
                    required: "Grup adı alanını doldurunuz.",
                },
                "grupUcret": {
                    required: "Grup ücreti alanını doldurunuz.",
                },
                "baslangicUlke": {
                    required: "Başlangıç ülkesini seçiniz.",
                },
                "baslangicIl": {
                    required: "Başlangıç ilini seçiniz.",
                },
                "baslangicIlce": {
                    required: "Başlangıç ilçesini seçiniz.",
                },
                "bitisUlke": {
                    required: "Bitiş ülkesini seçiniz.",
                },
                "bitisIl": {
                    required: "Bitiş ilini seçiniz.",
                },
                "bitisIlce": {
                    required: "Bitiş ilçesini seçiniz.",
                },
                "grupAciklama": {
                    required: "Grup açıklaması alanını doldurunuz.",
                },
                "adi": {
                    required: "Yolcu adı alanını doldurunuz.",
                },
                "soyadi": {
                    required: "Yolcu soadı alanını doldurunuz.",
                },
                "uyrukUlke": {
                    required: "Yolcu uyruk alanını doldurunuz.",
                },
                "tcKimlikPasaportNo": {
                    required: "Yolcu TC/Pass no alanını doldurunuz.",
                },
                "cinsiyet": {
                    required: "Yolcu cinsiyeti seçiniz.",
                },
                "telefonNo": {
                    required: "Yolcu telefonu alanını doldurunuz.",
                },
                "koltukNo": {
                    required: "Yolcu koltuk no alanını doldurunuz.",
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

                    var basTarih = moment($("#autoclose-datepicker1").val() + " " + $("#timepicker3").val(), "DD-MM-YYYY HH:mm");
                    var bitisTarih = moment($("#autoclose-datepicker2").val() + " " + $("#timepicker4").val(), "DD-MM-YYYY HH:mm");
                    var isLarger = basTarih >= bitisTarih;
                    if (isLarger) {
                        date_msg("Hareket Bitiş Tarihi Başlangıç Tarihinden Önce Olamaz.")
                        return false
                    }
                    $form_container.validate().settings.ignore = ":disabled,:hidden";
                    return $form_container.valid();
                }
                console.log("currenIndex: ", currentIndex, "NexIndex: ", newIndex);
                //repeater olan 2.sayfamız için yazılan validation
                if (newIndex == 2) {
                    if (grupList.length > 0) {
                        if (yolcuList.length > 0) {
                            var i;

                            let detay_ozet = "" +
                                " <table class=\"table table-striped- table-bordered table-checkable text-wrap\" style=\"max-width:100%\">\n" +
                                "                    <thead>\n" +
                                "                    <tr>\n" +
                                "                        <th>Grup No #</th>\n" +
                                "                        <th>Grup Adı</th>\n" +
                                "                        <th>Grup Ücreti</th>\n" +
                                "                        <th>Yolcu Sayısı</th>\n" +
                                "                        <th>Grup Açıklaması</th>\n" +
                                "                    </tr>\n" +
                                "                    </thead>" +
                                "                    <tbody>";
                            var j
                            for (var j = 0; j < yolcuList.length; j++) {
                                let s = (j + 1);
                                let adi = yolcuList[j].adi;
                                let soyadi = yolcuList[j].soyadi;
                                let cinsiyet = yolcuList[j].cinsiyet;
                                let koltukNo = yolcuList[j].koltukNo;
                                let tcKimlikPasaportNo = yolcuList[j].tcKimlikPasaportNo;
                                let telefonNo = yolcuList[j].telefonNo;
                                let uyrukUlke = yolcuList[j].uyrukUlke;
                                let hesKodu = yolcuList[j].hesKodu;

                                if (adi == "" || soyadi == "" || tcKimlikPasaportNo == "" || uyrukUlke == "" || koltukNo == "") {
                                    swal({
                                        title: s + ". Yolcu Eksik Bilgi Girişi",
                                        text: "Lütfen Yolcu Bölümündeki Zorunlu Alanları Doldurun.",
                                        icon: "warning",
                                        confirmButtonClass: "btn btn-secondary"
                                    })
                                    return false;
                                    break;
                                }
                            }
                            var i;
                            for (i = 0; i < grupList.length; i++) {
                                let s = (i + 1);
                                let grupAdi = grupList[i].grupAdi;
                                let grupUcret = grupList[i].grupUcret;
                                let grupAciklama = grupList[i].grupAciklama;
                                let yolcuSayisi = 0;
                                for (var j = 0; j < yolcuList.length; j++) {
                                    if (yolcuList[j].yolcuGrupId === i) {
                                        yolcuSayisi += 1;
                                    }
                                }
                                let baslangicUlke = grupList[i].baslangicUlke;
                                let bitisUlke = grupList[i].bitisUlke;
                                let baslangicIl = grupList[i].baslangicIl;
                                let bitisIl = grupList[i].bitisIl;
                                let baslangicIlce = grupList[i].baslangicIlce;
                                let bitisIlce = grupList[i].bitisIlce;

                                if (grupAdi == "" || grupUcret == "" || grupAciklama == "" || baslangicUlke == "" || bitisIlce == "" ||
                                    bitisUlke == "" || baslangicIl == "" || bitisIl == "" || baslangicIlce == "") {
                                    //alert fırlatıyor
                                    swal({
                                        title: s + ". Grup Eksik Bilgi Girişi",
                                        text: "Lütfen Grup Bölümündeki Zorunlu Alanları Doldurun.",
                                        icon: "warning",
                                        confirmButtonClass: "btn btn-secondary"
                                    })
                                    return false;
                                    break;
                                }

                                //özet ekranlar burda dolduruldu..
                                detay_ozet +=

                                    "                         <tr>\n" +
                                    "                           <th>" + s + " #</th>\n" +
                                    "                           <th>" + grupAdi + "</th>\n" +
                                    "                           <th>" + grupUcret + "</th>\n" +
                                    "                           <th>" + yolcuSayisi + "</th>\n" +
                                    "                           <th style='white-space:normal; text-overflow:ellipsis; max-width:500px;'>" + grupAciklama + "</th>\n" +
                                    "                         </tr>";

                            }
                            detay_ozet += "</tbody>";
                            detay_ozet += "</table>";
                            $("#s_yukDetay").html(detay_ozet);

                            var ozet_table = "" +
                                "<table class=\'table table-striped- table-bordered table-checkable text-wrap\' style=\"max-width:100%\">\n" +
                                "  <tr>\n" +
                                "    <td style='width: 200px'>Şoför:</td>\n" +
                                "    <td>\n" + $("#sefper option:selected").text() + "</td>\n" +
                                "    <td style='width: 200px'>Yardımcı Per.:</td>\n" +
                                "    <td>\n" + $("#sefper2 option:selected").text() + "</td>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <td style='width: 200px'>Araç:</td>\n" +
                                "    <td>" + $("#plak option:selected").text() + "</td>\n" +
                                "    <td>Araç Tel.:</td>\n" +
                                "    <td style='white-space:pre-line; text-overflow:ellipsis; width:450px;'>" + $("#atel").val() + "</td>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <td style='width: 200px'>Sefer Başlangıç:</td>\n" +
                                // "    <td>" + $("#baslangicIl option:selected").text() + " / " + $("#baslangicIlce option:selected").text() + "</td>\n" +
                                // "    <td>(" + $("#yulkilk option:selected").text() + ")</td>\n" +
                                "    <td>" + $("#autoclose-datepicker1").val() + " " + $("#timepicker3").val() + "</td>\n" +
                                // "  </tr>\n" +
                                // "  <tr>\n" +
                                "    <td style='width: 200px'>Sefer Bitiş:</td>\n" +
                                // "    <td>" + $("#bitisIl option:selected").text() + " / " + $("#bitisIlce option:selected").text() + "</td>\n" +
                                // "    <td>(" + $("#bulkilk option:selected").text() + ")</td>\n" +
                                "    <td>" + $("#autoclose-datepicker2").val() + " " + $("#timepicker4").val() + "</td>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <td>Acente:</td>\n" +
                                "    <td style='white-space:pre-line; text-overflow:ellipsis; width:450px;'>" + $("#Acenta option:selected").text() + "</td>\n" +
                                "    <td style='width: 200px'>Sefer No:</td>\n" +
                                "    <td>\n" + $("#sefno").val() + "</td>\n" +
                                "  </tr>\n" +
                                "  <tr>\n" +
                                "    <td>Açıklama:</td>\n" +
                                "    <td style='white-space:pre-line; text-overflow:ellipsis; width:450px;'>" + $("#not").val() + "</td>\n" +
                                "  </tr>\n" +
                                "</table>";

                            $("#s_sefer").html(ozet_table);
                            console.log(yolcuList);
                            console.log(grupList);
                        } else {
                            swal({
                                title: "Eksik Bilgi Girişi",
                                text: "Lütfen Yolcu Ekleyiniz.",
                                icon: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            })
                            return false;
                        }
                    } else {
                        swal({
                            title: "Eksik Bilgi Girişi",
                            text: "Lütfen Grup Ekleyiniz.",
                            icon: "warning",
                            confirmButtonClass: "btn btn-secondary"
                        })
                        return false;
                    }
                    return true;
                }
            }
            ,
            onFinishing: function (event, currentIndex) {
                return true;
            }
            ,
            onFinished: function (event, currentIndex) {
                var buttonList = [];
                var list = document.getElementById("steps-uid-0");
                buttonList = list.getElementsByTagName("a");
                for (let i = 0; i < buttonList.length; i++) {
                    if (buttonList[i].innerHTML === "Tamamla") {
                        tarifesizFinishButton1 = buttonList[i];
                    }
                }
                tarifesizFinishButton1.style.visibility = "hidden";
                // en son submit edilen kısım
                var seferBilgileri = {
                    "aracPlaka": $("#plak").val(),
                    "personel": $("#sefper").val(),
                    "personel2": $("#sefper2").val(),
                    "hareketTarih": $("#autoclose-datepicker1").val(),
                    "seferBitisTarih": $("#autoclose-datepicker2").val(),
                    "hareketSaati": $("#timepicker3").val(),
                    "seferBitisSaati": $("#timepicker4").val(),
                    "firmaSeferNo": $("#sefno").val(),
                    "guzergah": $("#sefguzer").val(),
                    "acenta": $("#Acenta").val(),
                    "seferAciklama": $("#not").val(),
                    "aracTelefonu": $("#atel").val(),
                }
                var postData = {
                    "uetdsAriziSeferBilgileriInputDTO": seferBilgileri,
                    "uetdsAriziGrupBilgileriInputDTO": grupList,
                    "uetdsAriziSeferYolcuBilgileriInputDTO": yolcuList,
                };
                console.log(postData)

                $.ajax({
                    type: "POST",
                    url: "/tarifesiz/seferEkle",
                    data: JSON.stringify(postData),
                    contentType: "application/json",
                    datatype: 'json',
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
                        $('#loading-screen').fadeOut('slow');
                        if (data.sonucKodu === 0) {
                            for (var i = 0; i < data.personeller.length; i++) {
                                if (data.personeller[i].sonucKodu === 0) {
                                    error_noti_yuk(i + 1 + ". " + data.personeller[i].sonucMesaji);
                                    console.log(i + 1 + ". " + data.personeller[i].sonucMesaji);
                                }
                            }
                            for (var i = 0; i < data.gruplar.length; i++) {
                                if (data.gruplar[i].sonucKodu === 0) {
                                    error_noti_yuk(i + 1 + ". " + data.gruplar[i].sonucMesaji)
                                    console.log(i + 1 + ". " + data.gruplar[i].sonucMesaji);
                                }
                            }
                            for (var i = 0; i < data.yolcular.uetdsYolcuSonuc.length; i++) {
                                if (data.yolcular.uetdsYolcuSonuc[i].sonucKodu === 0) {
                                    error_noti_yuk(i + 1 + ". " + data.yolcular.uetdsYolcuSonuc[i].sonucMesaji);
                                    console.log(i + 1 + ". " + data.yolcular.uetdsYolcuSonuc[i].sonucMesaji);
                                }
                            }
                            error_noti_yuk(data.sonucMesaji);
                            console.log(data.sonucMesaji);
                            console.log("Başarısız işlem");
                            setTimeout(function () {
                                window.location.replace("/tarifesiz/seferler");
                            }, 3000);
                        } else {
                            success_noti_custom("Seferiniz Ulaştırma ve Altyapı Bakanlığı'na Başarılı Şekilde Bildirilmiştir.");
                            console.log(data.sonucMesaji);
                            console.log("Başarılı işlem");
                            setTimeout(function () {
                                window.location.replace("/tarifesiz/seferler");
                            }, 2000);
                        }
                    },
                    error: function (e) {
                        console.log("ERROR : ", e);
                        tarifesizFinishButton1.style.visibility = "visible";
                    }
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