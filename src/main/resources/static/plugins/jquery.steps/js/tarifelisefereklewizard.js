$(document).ready(function () {

    $("#ssur").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Hareket Süresi Alanına Numerik Karakter Giriniz")
            this.value = this.value.replace(/[^0-9]+/, '')
        }
    });
    $("#sefno").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Sefer No Alanına Numerik Karakter Giriniz")
            this.value = this.value.replace(/[^0-9]+/, '')
        }
    });
    $("#atel").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Telefon Alanına Numerik Karakter Giriniz")
            this.value = this.value.replace(/[^0-9]+/, '')
        }
    });
    $("#yadi").keyup(function (e) {
        var regex = /^[a-zA-Z ğüşıöçĞÜŞİÖÇ]*$/;
        if (regex.test(this.value) !== true)
            success_noti_drop(titleyuk = "Dikkat!", message = "Yolcu Adı Alanına Alfabetik Karakter Giriniz");
        this.value = this.value.replace(/[^a-zA-Z ğüşıöçĞÜŞİÖÇ]+/, '');
    });
    $("#ysoy").keyup(function (e) {
        var regex = /^[a-zA-Z ğüşıöçĞÜŞİÖÇ]*$/;
        if (regex.test(this.value) !== true)
            success_noti_drop(titleyuk = "Dikkat!", message = "Yolcu Soyadı Alanına Alfabetik Karakter Giriniz");
        this.value = this.value.replace(/[^a-zA-Z ğüşıöçĞÜŞİÖÇ]+/, '');
    });
    $("#ytel").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Telefon Alanına Numerik Karakter Giriniz")
            this.value = this.value.replace(/[^0-9]+/, '')
        }
    });
    $("#biletucr").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "Ücret Alanına Numerik Karakter Giriniz")
            this.value = this.value.replace(/[^0-9]+/, '')
        }
    });
    $("#yolcutc").keyup(function (e) {
        var regex = /^[0-9]*$/;
        if (regex.test(this.value) !== true) {
            date_msg("Dikkat!", "TC/PS. No Alanına Numerik Karakter Giriniz")
            this.value = this.value.replace(/[^0-9]+/, '')
        }
    });
});

!function ($) {
    "use strict";

    var FormWizard = function () {
    };
    var yolcular = new Array();
    var formData = new FormData();
    //creates form with validation
    FormWizard.prototype.createValidatorForm = function ($form_container) {
        $form_container.validate({
            rules: {
                "uetdsSeferYolcuBilgileriInputDTO[adi]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[soyadi]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[cinsiyet]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[telefonNo]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletDuzenlemeYeri]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletDuzenlemeTarih]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[binisYerKodu]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[inisYerKodu]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletTuru]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletSeriNo]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[ebiletNumarasi]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[bagajno]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[tutar]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[koltukNo]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[uyrukUlke]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[tcKimlikPasaportNo]": {
                    required: true,
                },
                "uetdsSeferYolcuBilgileriInputDTO[email]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[aracId]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[unetGuzergahKodu]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[hareketTarih]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[hareketSaati]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[seyahatSuresi]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[personel1]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[personel2]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[firmaSeferNo]": {
                    required: true,
                },
                "uetdsTarifeliSeferBilgileriInputDTO[aracTelefonu]": {
                    required: true,
                },
            },
            messages: {
                "uetdsTarifeliSeferBilgileriInputDTO[aracId]": {
                    required: "Araç Seçiniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[adi]": {
                    required: "Yolcu Adı Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[soyadi]": {
                    required: "Yolcu Soyadı Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[cinsiyet]": {
                    required: "Yolcu Cinsiyeti Seçiniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[telefonNo]": {
                    required: "Yolcu Telefon No Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletDuzenlemeYeri]": {
                    required: "Bilet Düzenleme Yeri Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletDuzenlemeTarih]": {
                    required: "Bilet Düzenleme Tarihi Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[binisYerKodu]": {
                    required: "Yolcu Biniş Yeri Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[inisYerKodu]": {
                    required: "Yolcu İniş Yeri Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletTuru]": {
                    required: "Bilet Türü Seçiniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[biletSeriNo]": {
                    required: "Bilet Seri No Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[ebiletNumarasi]": {
                    required: "E-Bilet No Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[bagajno]": {
                    required: "Bagaj No Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[tutar]": {
                    required: "Bilet Turarı Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[koltukNo]": {
                    required: "Koltuk No Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[uyrukUlke]": {
                    required: "Yolcu Uyruğu Giriniz",
                },
                "uetdsSeferYolcuBilgileriInputDTO[tcKimlikPasaportNo]": {
                    required: "Yolcu Kimlik/Pasaport No Giriniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[unetGuzergahKodu]": {
                    required: "Güzergah Kodu Giriniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[hareketTarih]": {
                    required: "Hareket Tarihi Giriniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[hareketSaati]": {
                    required: "Hareket Saati Giriniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[seyahatSuresi]": {
                    required: "Hareket Süresi Giriniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[personel1]": {
                    required: "Personel Seçiniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[personel2]": {
                    required: "Personel Seçiniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[firmaSeferNo]": {
                    required: "Sefer No Giriniz",
                },
                "uetdsTarifeliSeferBilgileriInputDTO[aracTelefonu]": {
                    required: "Araç Telefon No Giriniz",
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
                if (currentIndex == 0 && newIndex == 1) {
                    $form_container.validate().settings.ignore = ":disabled,:hidden";
                    return $form_container.valid();
                }

                console.log("currenIndex: ", currentIndex, "NexIndex: ", newIndex);
                if (newIndex == 2) {
                    var ancestor = document.getElementById('lists'),
                        descendents = ancestor.getElementsByClassName('tral');
                    // gets all descendent of ancestor
                    var i, e, d;
                    var durum = true;
                    let total = descendents.length;
                    let basarili = 0;

                    let detay_ozet = "" +
                        " <table class=\"table table-striped- table-bordered table-hover table-checkable\" style=\"max-width:100%\">\n" +
                        // "                       id=\"kt_table_1\">\n" +
                        "                    <thead>\n" +
                        "                    <tr>\n" +
                        "                        <th>Yolcu No #</th>\n" +
                        "                        <th>Yolcu Adı Soyadı</th>\n" +
                        "                        <th>Cinsiyeti</th>\n" +
                        "                        <th>Telefon Numarası</th>\n" +
                        "                        <th>Koltuk No</th>\n" +
                        "                    </tr>\n" +
                        "                    </thead>" +
                        "                    <tbody>";
                    for (i = 1; i < descendents.length + 1; i++) {

                        let s = i;
                        let ad = $(document.querySelectorAll('.tral:nth-child(' + i + ')  input')[0]).val();
                        let soy = $(document.querySelectorAll('.tral:nth-child(' + i + ')  input')[1]).val();
                        let cinsiyet = $(document.querySelectorAll('.tral:nth-child(' + i + ') select')[0]['selectedOptions'][0]).text();
                        let telno = $(document.querySelectorAll('.tral:nth-child(' + i + ')  input')[2]).val();
                        let biletduzenyer = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[0]).val();
                        let biletduzentar = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[1]).val();
                        let binis = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[2]).val();
                        let inis = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[3]).val();
                        let biletTur = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[4]).val();
                        let biletseri = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[5]).val();
                        let tutar = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[6]).val();
                        let koltukno = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[7]).val();
                        let eBiletNo = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[9]).val();
                        let uyruk = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[10]).val();
                        let tcPassNo = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[11]).val();
                        // let HesKodu = $(document.querySelectorAll('.tral:nth-child(' + i + ') .modal input')[13]).val();
                        yolcular.push({
                            "ad": ad,
                            "soy": soy,
                            "cinsiyet": cinsiyet,
                            "telno": telno,
                        });
                        if (ad == "" || soy == "" || cinsiyet == "" || telno == "" || biletduzenyer == "" || biletduzentar == "" || binis == "" || inis == "" ||
                            biletTur == "" || biletseri == "" || tutar == "" || koltukno == "" || eBiletNo == "" || uyruk == "" || tcPassNo == "") {
                            //alert fırlatıyor
                            swal({
                                title: s + ". Yolcu Eksik Bilgi Girişi",
                                text: "Lütfen Detaylar Bölümündeki Zorunlu Alanları Doldurun.",
                                icon: "warning",
                                confirmButtonClass: "btn btn-secondary"
                            })
                            return false;
                            break;
                        }
                        detay_ozet +=
                            "                         <tr>\n" +
                            "                         <th>" + s + " #</th>\n" +
                            "                         <th>" + ad + " " + soy + "</th>\n" +
                            "                         <th>" + cinsiyet + "</th>\n" +
                            "                         <th>" + telno + "</th>\n" +
                            "                         <th>" + koltukno + "</th>\n" +
                            "                          </tr>";

                    }
                    detay_ozet += "</tbody>";
                    detay_ozet += "</table>";
                    $("#s_yukDetay").html(detay_ozet);

                    var ozet_table = "" +
                        "<table class='table table-striped- table-bordered table-hover table-checkable' style=\"max-width:100%\">\n" +
                        "  <tr>\n" +
                        "    <th style='width: 200px'>Görevliler:</th>\n" +
                        "    <td>\n" + $("#sefper option:selected").text() + " - " + $("#sefpery option:selected").text() + "</td>\n" +
                        "    <th style='width: 200px'>Araç:</th>\n" +
                        "    <td>\n" + $("#plak option:selected").text() + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <th style='width: 200px'>Hareket Tarihi:</th>\n" +
                        "    <td>\n" + $("#autoclose-datepicker1").val() + "</td>\n" +
                        "    <th style='width: 200px'>Hareket Saati:</th>\n" +
                        "    <td>\n" + $("#timepicker1").val() + "</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <th style='width: 200px'>Güzergah:</th>\n" +
                        "    <td>\n" + $("#sefguz").val() + "</td>\n" +
                        "    <th style='width: 200px'>Sefer No:</th>\n" +
                        "    <td>\n" + $("#sefno").val() + "</td>\n" +
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
                alert("Submitted!");

                var postData = $('#wizard-validation-form').serializeJSON();
                console.log(postData)

                // for (let pair of formData.entries()) {
                //     console.log(pair[0] + ': ' + pair[1]);
                // }
                $.ajax({
                    type: "POST",
                    url: "/tarifeli/add",
                    data: postData,
                    contentType: "application/json",
                    datatype: 'json',
                    cache: false,
                    timeout: 600000,

                    success: function (data) {
                        $('#loading-screen').fadeOut('slow');
                        if (data.result) {
                            success_noti_custom("Seferiniz Ulaştırma ve Altyapı Bakanlığı'na Başarılı Şekilde Bildirilmiştir.");
                            setTimeout(function () {
                                window.location.replace("/tarifeli/seferler");
                            }, 2000);
                        } else {
                            // var myJSON = JSON.stringify(data.sonucMesaji)
                            error_noti_yuk(data.sonucMesaji)
                            console.log(data.sonucMesaji + " Başarısız işlem");
                            setTimeout(function () {
                                window.location.replace("/tarifeli/seferler");
                            }, 2000);
                        }
                    },
                    error: function (e) {
                        console.log("ERROR : ", e);
                        $("#btn-login").prop("disabled", false);
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
